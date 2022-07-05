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
    
    
    //1. Display the total confirmed Covid-19 cases according to country
    public static List<String[]> AllCountryConfirmCasesTableData(List<Country> cList) throws IOException, CsvException{
        String[] temp;
        List ans = new ArrayList();
        for (Country c : getUniqueCountries(cList)) {
            String sum = String.valueOf(sumofCases(getRecordsWithSameCountryName(cList,c.getCountryName()),c.getCountryName()));
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
    
    public static List<Country> getUniqueCountries(List<Country> cList) throws IOException, CsvException{
        return cList.stream()
                .filter(distinctByKey(Country::getCountryName))
                .collect(Collectors.toList());
    }
    
    public static List<Country> getRecordsWithSameCountryName(List<Country> cList, String countryName) throws IOException, CsvException{
        
        return cList.stream().filter(t->sameCountryName.test(t.getCountryName(), countryName))
                .collect(Collectors.toList());
    }
    
    public static int sumofCases(List<Country> cList,String countryName){
        return cList.stream().map(c -> c.getDataset())
                .mapToInt(dataset -> dataset.stream().mapToInt(numbers -> numbers.getData()).sum())
                .sum();

    }
    
    
    //2. Compute the sum of confirmed cases by week and month for each country
    //Weekly confirmed cases and monthly confirm case 
    public static String[][] weeklyNMonthlyCasesForCountriesTabledata(List<Country> cList,DateTimeFormatter dateFormat){
        try {
            List<Country> countries = getUniqueCountries(cList);
            List<String> weeks = getAllWeeksOrMonth(dateFormat);
            String[][] data = new String[countries.size()][weeks.size()+2];
            int i =0;
            
            for (Country c : countries) {
                data[i][0]=String.valueOf(i+1)+".";
                data[i][1]= c.getCountryName();
                int w = 2;
                
                for (String week : weeks) {
                    int weeklycases = getWeeklyOrMonthlyConfirmedCasesbyCountry(cList, c.getCountryName(), week,dateFormat);
                    data[i][w]=String.valueOf(weeklycases);
                    w+=1;
                }
                i+=1;
            }
            
            return data;
            
        } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
    
    public static List<String> getAllWeeksOrMonth(DateTimeFormatter  dateFormat){
        try {
            return CovidData.provideC19GlobalConfirmedCaseData().stream()
                    .map(c->c.getDataset())
                    .flatMap(d->d.stream())
                    .sorted(Comparator.comparing(d->d.getDate()))
                    .map(week->dateFormat.format(week.getDate()))
                    
                    .filter(distinctByKey(week->week))
                    .collect(Collectors.toList());
        } catch (IOException | CsvException ex) { 
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static int getWeeklyOrMonthlyConfirmedCasesbyCountry(List<Country> cList,String countryName, String weekOrMonthYear, DateTimeFormatter dateFormat){ 
        try {
            return getRecordsWithSameCountryName(cList,countryName).stream()
                    .map(c->c.getDataset())
                    .flatMap(d->d.stream())                    
                    .filter(d->dateFormat.format(d.getDate()).equals(weekOrMonthYear))
                    .mapToInt(date->date.getData())
                    .sum();
        } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     //For header of the table     
    public static String[] getAllWeeksStartNEndDateHeader() {
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter weekNYearDateFormat = DateTimeFormatter.ofPattern("ww,Y",Locale.UK);
        String[] header = new String[getAllWeeksOrMonth(weekNYearDateFormat).size()+2];
        header[0]="No";
        header[1]= "Country";
        int i=2;
        
        for (String allWeek : getAllWeeksOrMonth(weekNYearDateFormat)) {
            LocalDate startDate = LocalDate.now()
                    .with(WeekFields.ISO.weekBasedYear(), Integer.valueOf(allWeek.split(",")[1])) // year
                    .with(WeekFields.ISO.weekOfWeekBasedYear(), Integer.valueOf(allWeek.split(",")[0])) // week of year
                    .with(WeekFields.ISO.dayOfWeek(), DayOfWeek.MONDAY.getValue()); // day of week
            LocalDate endDate = startDate.plusDays(6);
            String temp = formatter.format(startDate)+" - "+formatter.format(endDate);
            header[i] = temp;
            i+=1;
        }
        
        return header;
    }
    
     public static String[] getAllMonthHeader() {
        DateTimeFormatter monthNYearDateFormat = DateTimeFormatter.ofPattern("MMMM,Y",Locale.UK);
        String[] header = new String[getAllWeeksOrMonth(monthNYearDateFormat).size()+2];
        header[0]="No";
        header[1]= "Country";
        int i=2;
        
        for (String allWeek : getAllWeeksOrMonth(monthNYearDateFormat)) {
            header[i] = allWeek;
            i+=1;
        }
        
        return header;
        
     }
    
    
    
    
    public static void main(String[] args) {
        try {
            SimpleDateFormat monthNYear = new SimpleDateFormat("MM Y");
            DateTimeFormatter weekNYearDateFormat = DateTimeFormatter.ofPattern("ww,Y",Locale.UK);
            DateTimeFormatter monthNYearDateFormat = DateTimeFormatter.ofPattern("MMMM,Y",Locale.UK);
            DateTimeFormatter format =DateTimeFormatter.ofPattern("M/d/yy",Locale.UK);
            LocalDate date = LocalDate.parse("12/28/20",format);
            List<Country> clist = CovidData.provideC19GlobalConfirmedCaseData();
            System.out.println(Arrays.toString(getAllMonthHeader()));
            WeekFields weekFields = WeekFields.of(Locale.ENGLISH);
            
            System.out.println(weekNYearDateFormat.format(date));
            System.out.println(getAllWeeksOrMonth(monthNYearDateFormat)); 
            System.out.println(getWeeklyOrMonthlyConfirmedCasesbyCountry(clist,"Afghanistan","February,2020",monthNYearDateFormat)); 
        } catch (IOException | CsvException  ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
