
package aplcassignment;

import static aplcassignment.Task1.getAllWeeksOrMonth;
import static aplcassignment.Task1.getRecordsWithSameCountryName;
import static aplcassignment.Task1.getUniqueCountries;
import static aplcassignment.Task1.sumofCases;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static aplcassignment.Task1.getWeeklyOrMonthlyConfirmedCasesforCountry;
import static aplcassignment.Task1.weeklyEndDate;
import static aplcassignment.Task1.weeklyStartDate;

/**
 *
 * @author rainy
 */
public class tableData {
    
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
     
      public static String[][] weeklyNMonthlyCasesForCountriesTabledata(List<Country> cList,SimpleDateFormat dateFormat){
        try {
            List<Country> countries = getUniqueCountries(cList);
            List<String> weeks = getAllWeeksOrMonth(cList,dateFormat);
            String[][] data = new String[countries.size()][weeks.size()+2];
            int i =0;
            
            for (Country c : countries) {
                data[i][0]=String.valueOf(i+1)+".";
                data[i][1]= c.getCountryName();
                int w = 2;
                
                for (String week : weeks) {
                    int weeklycases = Task1.getWeeklyOrMonthlyConfirmedCasesforCountry(getRecordsWithSameCountryName(cList, c.getCountryName()), week,dateFormat);
                    data[i][w]=String.valueOf(weeklycases);
                    w+=1;
                }
                //System.out.println(Arrays.toString(data[i]));
                i+=1;
            }
            
            return data;
            
        } catch (IOException | CsvException ex) {
            Logger.getLogger(Task1.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
      
         //For header of the table     
    public static String[] getAllWeeksStartNEndDateHeader(List<Country> dataset, SimpleDateFormat format) {
        SimpleDateFormat formatter =new SimpleDateFormat("dd MMM yyyy");
        //SimpleDateFormat weekNYearDateFormat = new SimpleDateFormat("ww,Y",Locale.UK);
        String[] header = new String[getAllWeeksOrMonth(dataset,format).size()+2];
        header[0]="No";
        header[1]= "Country";
        int i=2;
        
        for (String allWeek : getAllWeeksOrMonth(dataset,format)) {            
            String temp = weeklyStartDate(dataset, allWeek, format, formatter)+" - "+weeklyEndDate(dataset, allWeek, format, formatter);
            header[i] = temp;
            i+=1;
        }
        
        return header;
    }
    
     public static String[] getAllMonthHeader(List<Country> dataset, SimpleDateFormat format) {
        SimpleDateFormat monthNYearDateFormat = new SimpleDateFormat("MMM-yyyy");
        String[] header = new String[getAllWeeksOrMonth(dataset,format).size()+2];
        header[0]="No";
        header[1]= "Country";
        int i=2;
        
        for (String allWeek : getAllWeeksOrMonth(dataset,format)) {
            header[i] = allWeek;
            i+=1;
        }
        
        return header;
        
     }
    
}
