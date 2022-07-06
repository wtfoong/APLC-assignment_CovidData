/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplcassignment;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.Comparator;


/**
 *
 * @author rainy
 */
public class Task1 {
    private static final BiPredicate<String, String> sameCountryName = (inputName, ComparedName) -> inputName.equalsIgnoreCase(ComparedName);
    private static final BiPredicate<String, String> searchCountryName = (searchInput, ComparedName) -> searchInput.toLowerCase().contains(ComparedName.toLowerCase());
    
    
    
   
    /**
     * Source: https://stackoverflow.com/a/27872852
     * @param <T>
     * @param keyExtractor
     * @return 
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
    
    public static List<Country> getUniqueCountries(List<Country> cList) throws IOException, CsvException{
        return cList.stream()
                .filter(distinctByKey(Country::getCountryName))
                .collect(Collectors.toList());
    }
    
    public static List<Country> getRecordsWithSameCountryName(List<Country> cList, String countryName) throws IOException, CsvException{
        
        return cList.stream().filter(t->sameCountryName.test(t.getCountryName(), countryName))
                .collect(Collectors.toList());
    }
    
    
    
    //1. Display the total confirmed Covid-19 cases according to country
    public static int sumofCases(List<Country> cList){
        return cList.stream().map(c -> c.getDataset())
                .mapToInt(dataset -> dataset.stream().mapToInt(numbers -> numbers.getData()).reduce(0, (x, y) -> x + y))
                .sum();
    }
    
    
    //2. Compute the sum of confirmed cases by week and month for each country
    //Weekly confirmed cases and monthly confirm case 

    public static List<String> getAllWeeksOrMonth(List<Country> cList, SimpleDateFormat dateFormat) {
        
        return cList.stream()
                    .map(c->c.getDataset())
                    .flatMap(d->d.stream())
                    .map(week->dateFormat.format(week.getDate()))
                    .filter(distinctByKey(week->week))
                    .collect(Collectors.toList());
    }
    
    public static Integer getWeeklyOrMonthlyConfirmedCasesforCountry(List<Country> cList, String dateToCompareTo, SimpleDateFormat dateFormat) {
        return cList.stream().map(c -> c.getDataset())
                .mapToInt(data -> data.stream().filter(date
                -> dateFormat.format(date.getDate()).equals(dateToCompareTo)).mapToInt(numbers -> numbers.getData()).sum())
                .sum();
    }

    public static String weeklyStartDate(List<Country> cList, String weekNYear, SimpleDateFormat weekNumber, SimpleDateFormat displayWeekFormat) {
        Country resultList = cList.get(0);
        return resultList.getDataset().stream()
                .filter(p -> weekNumber.format(p.getDate()).equals(weekNYear))
                .sorted(Comparator.comparing(p -> p.getDate()))
                .map(p -> displayWeekFormat.format(p.getDate())).findFirst().orElse(null);
    }

    public static String weeklyEndDate(List<Country> cList, String weekNYear, SimpleDateFormat dateFormat, SimpleDateFormat displayWeekFormat) {
        
        return  cList.get(0).getDataset().stream()
                .filter(p -> dateFormat.format(p.getDate()).equals(weekNYear))
                .sorted(Comparator.comparing(p -> p.getDate()))
                .map(p -> displayWeekFormat.format(p.getDate())).reduce((a, b) -> b).orElse(null);
    }
    
    //3. Find the highest/lowest death and recovered Covid-19 cases as per country 
    //using HOF and recursive lambda
    public static int getCountryHighestData(List<Country> cList, String countryName, BiFunction<int[], Integer, Integer> func){
        
        try {
            List<Country> countryDataList = getRecordsWithSameCountryName(cList,countryName);
            
          int[] dataArray = countryDataList.stream()
                    .map(c->c.getDataset())
                    .flatMap(Collection::stream)
                    .collect(Collectors.groupingBy(CountryData::getDate)).entrySet().stream()
                    .mapToInt(dnumber->dnumber.getValue().stream().mapToInt(d->d.getData()).sum())
                    .toArray();
            
            return func.apply(dataArray, dataArray.length);
                    } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    //using getMin tail recursion
    public static int getCountryLowestData(List<Country> cList, String countryName){
        
        try {
            List<Country> countryDataList = getRecordsWithSameCountryName(cList,countryName);
            
            int[] dataArray = countryDataList.stream()
                    .map(c->c.getDataset())
                    .flatMap(Collection::stream)
                    .collect(Collectors.groupingBy(CountryData::getDate)).entrySet().stream()
                    .mapToInt(dnumber->dnumber.getValue().stream().mapToInt(d->d.getData()).sum())
                    .filter(d->d>=0)
                    .toArray();
            
            return getMin(dataArray,dataArray.length,getlower);
                    } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    //currying
    private static final Function<Integer,Function<Integer,Integer>> getlower = a->b ->((a < b) ? a : b);
    private static final Function<Integer,Function<Integer,Integer>> getlarger = a->b->((a > b) ? a : b);
 
    
    //tail recursion with HOF(Function as argument)
    public static int getMin(int[] intArray, int count, Function<Integer,Function<Integer,Integer>> func){
        if (count==1) {
            return intArray[0];
        }
        return func.apply(intArray[count-1]).apply(getMin(intArray,count-1, func));
        
    }
    
    //Recursive lambda
    public static final BiFunction<int[], Integer, Integer> getMax = (intArray,count)->count==1
             ?intArray[0]
             :getlarger.apply(intArray[count-1]).apply(Task1.getMax.apply(intArray,count-1));
   
    
    
    //Q4 Search/locate the country for Covid-19 cases covering confirmed, death and recovered cases. 
    public static List<Country> searchCountry(List<Country> cList, String countryName) {
        return cList.stream()
                .filter(distinctByKey(p -> p.getCountryName()))
                .filter(p -> searchCountryName.test(p.getCountryName(), countryName))
                .collect(Collectors.toList());
    }
}