package aplcassignment;

import aplcassignment.dataClass.CountryData;
import aplcassignment.dataClass.Country;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rainy
 */
public class CovidData {
    public static List<Country> readCSVFile(String filePath){
        FileInputStream file = null;
        try {
            file = new FileInputStream(filePath);
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
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
                    try {
                        int rowData = Integer.valueOf(filtered.get(i));
                        CountryData cd = new CountryData(format.parse(headers.get(i)), rowData);
                        if (rowData > 0&&i>0) {
                            int previousRowData = Integer.valueOf(filtered.get(i-1));
                            int newRowData = rowData - previousRowData;
                            if (previousRowData > 0) {
                                // The data reduce in the next day (Due to wrong dataset)
                                if (newRowData < 0) {
                                    // Remain the wrong data to get corrct sum 
                                    cd.setNumber(newRowData);
                                } else {
                                    cd.setNumber(newRowData);
                                }
                            }
                        }
                        c.getDataset().add(cd);
                    } catch (ParseException ex) {
                        java.util.logging.Logger.getLogger(CovidData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                    }
                }
                
                countryList.add(c);
            }   return countryList;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CovidData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CovidData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvException ex) {
            Logger.getLogger(CovidData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(CovidData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.err.println("Something went wrong! No file is read!");
        return null;
   }
    
    
   
    public static List<String> getheaders(List<String[]> data) {
        return Arrays.asList(data.get(0));
    }
    
    public static List<Country> provideC19GlobalConfirmedCaseData() {
        String filepath = "Coviddata/time_series_covid19_confirmed_global.csv";
        return readCSVFile(filepath);
    }
    
    public static List<Country> provideC19GlobalDeathData() {
        String filepath = "Coviddata/time_series_covid19_deaths_global.csv";
        return readCSVFile(filepath);
    }
    
    public static List<Country> provideC19GlobalRecoveredData() {
        String filepath = "Coviddata/time_series_covid19_recovered_global.csv";
        return readCSVFile(filepath);
    }
    
   
    
}