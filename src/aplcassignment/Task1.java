/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplcassignment;

import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        List<Country> c = getUniqueCountries();
//        for (Country c : getUniqueCountries()) {
//            String sum = String.valueOf(sumofCases(getRecordsWithSameCountryName(c.getCountryName()),c.getCountryName()));
//            temp=new String[2];
//            temp[0]=c.getCountryName();
//            temp[1]=sum;
//            ans.add(temp);
//        }
        
        for (int i = 0; i < c.size(); i++) {
            String sum = String.valueOf(sumofCases(getRecordsWithSameCountryName(c.get(i).getCountryName()),c.get(i).getCountryName()));
            temp=new String[2];
            temp[0]=c.get(i).getCountryName();
            temp[1]=sum;
            ans.add(temp);
        }
        //System.out.println(ans);
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
    
    
    
    
    
    
    
    public static void main(String[] args) {
        try {
            //q1AllCountryWithConfirm();
            for(String[] test :q1AllCountryWithConfirm() ){
                System.out.println(test[0]);
            }
            
            for (int i = 0; i < 10; i++) {
                System.out.println(q1AllCountryWithConfirm().get(i)[0]);
            }

        } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
}
