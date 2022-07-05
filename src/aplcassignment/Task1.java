/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplcassignment;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.time.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author rainy
 */
public class Task1 {
    private static final BiPredicate<String, String> sameCountryName = (inputName, ComparedName) -> inputName.equalsIgnoreCase(ComparedName);
    
    
    //1. Display the total confirmed Covid-19 cases according to country
    public static List<String[]> q1AllCountryWithConfirm() throws IOException, CsvException{
        String[] temp;
        List ans = new ArrayList();
        for (Country c : getUniqueCountries()) {
            String sum = String.valueOf(sumofCases(getRecordsWithSameCountryName(c.getCountryName()),c.getCountryName()));
            temp=new String[2];
            temp[0]=c.getCountryName();
            temp[1]=sum;
            ans.add(temp);
        }
        return ans;
    }
    
    
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
    
    public static List<Country> getUniqueCountries() throws IOException, CsvException{
        return CovidData.provideC19GlobalConfirmedCaseData().stream()
                .filter(distinctByKey(Country::getCountryName))
                .collect(Collectors.toList());
    }
    
    public static List<Country> getRecordsWithSameCountryName( String countryName) throws IOException, CsvException{
        
        return CovidData.provideC19GlobalConfirmedCaseData().stream().filter(t->sameCountryName.test(t.getCountryName(), countryName))
                .collect(Collectors.toList());
    }
    
    public static int sumofCases(List<Country> cList,String countryName){
        return cList.stream().map(c -> c.getDataset())
                .mapToInt(dataset -> dataset.stream().mapToInt(numbers -> numbers.getData()).sum())
                .sum();

    }
    
    
    //2. Compute the sum of confirmed cases by week and month for each country
    //Weekly confirmed cases
    public static String[][] weeklyCasesForCountries(){
        try {
            List<Country> countries = getUniqueCountries();
            List<String> weeks = getAllWeeks();
            String[][] data = new String[countries.size()][weeks.size()+2];
            int i =0;
            
            for (Country c : countries) {
                data[i][0]=String.valueOf(i+1)+".";
                data[i][1]= c.getCountryName();
               //System.out.println( data[i][1]);
                int w = 2;
                
                for (String week : weeks) {
                    int weeklycases = getWeeklyConfirmedCasesbyCountry( c.getCountryName(), week);
                    data[i][w]=String.valueOf(weeklycases);
                    w+=1;
                     //System.out.println(data[i][w]);
                }
                System.out.println(Arrays.toString(data[i]));
                i+=1;
               
            }
            
            return data;
            
        } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return null;
    }
    
    
    //Monthly confirmed cases
    
    
    
    
    public static List<String> getAllWeeks(){
        try {
            SimpleDateFormat weekNYear = new SimpleDateFormat("ww,Y");
            return CovidData.provideC19GlobalConfirmedCaseData().stream()
                    .map(c->c.getDataset())
                    .flatMap(d->d.stream())
                    .map(week->weekNYear.format(week.getDate()))
                    .filter(distinctByKey(week->week))
                    .collect(Collectors.toList());
        } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static int getWeeklyConfirmedCasesbyCountry(String countryName, String weekYear){ 
        try {
            SimpleDateFormat weekNYear = new SimpleDateFormat("ww,Y");
            return getRecordsWithSameCountryName(countryName).stream()
                    .map(c->c.getDataset())
                    .flatMap(d->d.stream())
                    
                    .filter(d->weekNYear.format(d.getDate()).equals(weekYear))
                    .mapToInt(date->date.getData())
                    .sum();
        } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    
    
    
    
    
    
    
    
    public static String[] getAllWeeksStartNEndDateHeader() {
        //For header of the table
        List<String> header = new ArrayList();
        header.add("No");
        header.add("Country");
        
        for (String allWeek : getAllWeeks()) {
            DateTime startDate = new DateTime()
                .withWeekyear(Integer.valueOf(allWeek.split(",")[1]))
                .withWeekOfWeekyear(Integer.valueOf(allWeek.split(",")[0]))
                .withDayOfWeek(1);
           
            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yy");
            
            DateTime endDate = new DateTime()
                .withWeekyear(Integer.valueOf(allWeek.split(",")[1]))
                .withWeekOfWeekyear(Integer.valueOf(allWeek.split(",")[0]))
                .withDayOfWeek(7);
           
            String temp = dateTimeFormatter.print(startDate)+" - "+dateTimeFormatter.print(endDate);
            header.add(temp);
        }
        
        return (String[]) header.toArray();
    }
    
    
    
    
    
    public static void main(String[] args) {
//        try {
//            System.out.println(getAllWeeks());
//            System.out.println(getWeeklyConfirmedCasesbyCountry("China","04,2020"));
//            int i =0;
//            
//            for (Country c : getUniqueCountries()) {
//                
//                System.out.println(c.getCountryName());
//                int w = 2;
//                i+=1;
//                for (String week : getAllWeeks()) {
//                    int weeklycases = getWeeklyConfirmedCasesbyCountry( c.getCountryName(), week);
//                    System.out.println(weeklycases);
//                }
//            }
//        } catch (IOException | CsvException ex) {
//            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
//        }

        System.out.println(weeklyCasesForCountries());
    }

    
}
