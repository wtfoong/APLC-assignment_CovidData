
package aplcassignment;

import aplcassignment.dataClass.Country;
import static aplcassignment.Task1.getAllWeeksOrMonth;
import static aplcassignment.Task1.getRecordsWithSameCountryName;
import static aplcassignment.Task1.getUniqueCountries;
import static aplcassignment.Task1.sumofCases;
import com.opencsv.exceptions.CsvException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            String sum = String.valueOf(sumofCases(getRecordsWithSameCountryName(cList,c.getCountryName())));
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
     
     public static String[] deathNRecoverHeader(){
          String[] header = {"No","Country", "Lowest Death", "Highest Death", "Lowest Recovered", "Highest Recovered"};
          return header;
     }
     
     public static String[][] deathNRecoverTableData(List<Country> deathList, List<Country> recoveredList){
         try {
             List<Country> countries = getUniqueCountries(deathList);
             String[][] tableData = new String[countries.size()][6];
             int i =0;
             
             for (Country country : countries) {
                 tableData[i][0]=String.valueOf(i+1)+".";
                 tableData[i][1] = country.getCountryName();
                 tableData[i][2] = String.valueOf( Task1.getCountryLowestData(deathList, country.getCountryName()));
                 tableData[i][3] = String.valueOf( Task1.getCountryHighestData(deathList, country.getCountryName(),Task1.getMax));
                 tableData[i][4] = String.valueOf( Task1.getCountryLowestData(recoveredList, country.getCountryName()));
                 tableData[i][5] = String.valueOf( Task1.getCountryHighestData(recoveredList, country.getCountryName(),Task1.getMax));
                 
                 i+=1;
             }
             return tableData;
         } catch (IOException | CsvException ex) {
             Logger.getLogger(tableData.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
     }
     
    public static String[] saerchTableHeader (){
          String[] header = {"Statistics","Cases"};
          return header;     
    }
     
    public static String[][] saerchTableData (List<Country> confirmCases, List<Country> deathList, List<Country> recoveredList, String countryName){
         try {
             String[][] table1Data = new String[9][2];
             
             table1Data[0][0] = "Total Confirmed Cases";
             table1Data[1][0] = "Total Death Cases";
             table1Data[2][0] = "Total Recovered Cases";
             table1Data[3][0] = "Lowest Confirmed Cases/day";
             table1Data[4][0] = "Highest Confirmed Cases/day";
             table1Data[5][0] = "Lowest Death Cases/day";
             table1Data[6][0] = "Highest Death Cases/day";
             table1Data[7][0] = "Lowest Recovered Cases/day";
             table1Data[8][0] = "Highest Recovered Cases/day";
             
             table1Data[0][1] = String.valueOf(Task1.sumofCases(getRecordsWithSameCountryName(confirmCases,countryName)));
             table1Data[1][1] = String.valueOf(Task1.sumofCases(getRecordsWithSameCountryName(deathList,countryName)));
             table1Data[2][1] = String.valueOf(Task1.sumofCases(getRecordsWithSameCountryName(recoveredList,countryName)));
             table1Data[3][1] = String.valueOf(Task1.getCountryLowestData(confirmCases, countryName));
             table1Data[4][1] = String.valueOf(Task1.getCountryHighestData(confirmCases, countryName,Task1.getMax));
             table1Data[5][1] = String.valueOf(Task1.getCountryLowestData(deathList, countryName));
             table1Data[6][1] = String.valueOf(Task1.getCountryHighestData(deathList, countryName,Task1.getMax));
             table1Data[7][1] = String.valueOf(Task1.getCountryLowestData(recoveredList, countryName));
             table1Data[8][1] = String.valueOf(Task1.getCountryHighestData(recoveredList, countryName,Task1.getMax));
             
             
             return table1Data;
         } catch (IOException | CsvException ex) {
             Logger.getLogger(tableData.class.getName()).log(Level.SEVERE, null, ex);
         }
          
         return null;
    }
}
