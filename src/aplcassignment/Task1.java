/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplcassignment;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.text.ParseException;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;


/**
 *
 * @author rainy
 */
public class Task1 {
    private static final BiPredicate<String, String> sameCountryName = (inputName, ComparedName) -> inputName.equalsIgnoreCase(ComparedName);
    
    
    
   
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
    public static int sumofCases(List<Country> cList,String countryName){
        return cList.stream().map(c -> c.getDataset())
                .mapToInt(dataset -> dataset.stream().mapToInt(numbers -> numbers.getData()).sum())
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

    public static String weeklyStartDate(List<Country> cList, String weekNYear, SimpleDateFormat dateFormat, SimpleDateFormat displayWeekFormat) {
        Country resultList = cList.get(0);
        return resultList.getDataset().stream()
                .filter(p -> dateFormat.format(p.getDate()).equals(weekNYear))
                .sorted(Comparator.comparing(p -> p.getDate()))
                .map(p -> displayWeekFormat.format(p.getDate())).findFirst().orElse(null);
    }

    public static String weeklyEndDate(List<Country> cList, String weekNYear, SimpleDateFormat dateFormat, SimpleDateFormat displayWeekFormat) {
        
        return  cList.get(0).getDataset().stream()
                .filter(p -> dateFormat.format(p.getDate()).equals(weekNYear))
                .sorted(Comparator.comparing(p -> p.getDate()))
                .map(p -> displayWeekFormat.format(p.getDate())).reduce((a, b) -> b).orElse(null);
    }
}
