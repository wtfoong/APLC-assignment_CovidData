
package aplcassignment;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rainy
 */
public class CovidData {
    public static List<Country> readCSVFile(String filePath) throws IOException, CsvException{
        FileInputStream file = new FileInputStream(filePath);
        DateTimeFormatter format =DateTimeFormatter.ofPattern("M/d/[yyyy][yy]");
        InputStreamReader inputStream = new InputStreamReader(file, StandardCharsets.UTF_8);
        CSVReader reader = new CSVReader(inputStream);

        List<String[]> data = reader.readAll();
        List<String>headers= new ArrayList<>(getheaders(data));
        List<Country> countryList = new ArrayList<>();
        
        headers.subList(0, 4).clear();
        data.remove(0);
        
        
        for (String[] d : data) {
            
            Country c = new Country();
            try {
                c.setStateName(d[0]);
                c.setCountryName(d[1]);
                c.setLatitude(Float.valueOf(d[2]));
                c.setLongitude(Float.valueOf(d[3]));
            } catch (NumberFormatException e) {
                
            }
            
            List<String>filtered = new ArrayList<>(Arrays.asList(d));
            filtered.subList(0, 4).clear();
            
            for (int i = 0; i < filtered.size(); i++) {
                int rowData = Integer.valueOf(filtered.get(i));
                CountryData cd = new CountryData(LocalDate.parse(headers.get(i),format), rowData);
                if (i > 0) {
                    int previousRowData = Integer.valueOf(filtered.get(i-1));
                    int newRowData = rowData - previousRowData;
                    if (previousRowData > 0) {
                        // The data reduce in the next day (Due to wrong dataset)
                        if (newRowData < 0) {
                            // Remain the wrong data to avoid wrong counting in sum.
                            cd.setNumber(newRowData);
                        } else {
                            cd.setNumber(newRowData);
                        }
                    }
                }
                c.getDataset().add(cd);
            }
           
            countryList.add(c);
        } 
        return countryList;
   }
    
    
   
    public static List<String> getheaders(List<String[]> data) {
        return Arrays.asList(data.get(0));
    }
    
    public static List<Country> provideC19GlobalConfirmedCaseData() throws IOException, CsvException{
        String filepath = "Coviddata/time_series_covid19_confirmed_global.csv";
        return readCSVFile(filepath);
    }
    
    public static List<Country> provideC19GlobalDeathData() throws IOException, CsvException{
        String filepath = "Coviddata/time_series_covid19_deaths_global.csv";
        return readCSVFile(filepath);
    }
    
    public static List<Country> provideC19GlobalRecoveredData() throws IOException, CsvException{
        String filepath = "Coviddata/time_series_covid19_recovered_global.csv";
        return readCSVFile(filepath);
    }
    
   
//    public static void main(String[] args) {
//        String date = "2/22/20";
//        DateTimeFormatter format =DateTimeFormatter.ofPattern("M/d/yy");
//        LocalDate lt=LocalDate.parse(date,format);
//    }
    
}
