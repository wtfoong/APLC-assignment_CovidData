
package aplcassignment;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.String;
import java.util.function.Function;

/**
 *
 * @author rainy
 */
public class CovidData {
    public static List readCSVFile(String filePath) throws IOException, CsvException{
        FileInputStream file = new FileInputStream(filePath);
        InputStreamReader inputStream = new InputStreamReader(file, StandardCharsets.UTF_8);
        CSVReader reader = new CSVReader(inputStream);

        List<String[]> data = reader.readAll();
        
        return data;
   }
    
    
   
    public static List<String> getheaders(List<String[]> data) throws IOException, CsvException{
        return Arrays.asList(data.get(0));
    }
    
    public static List<String[]> provideC19GlobalConfirmedCaseData() throws IOException, CsvException{
        String filepath = "Coviddata/time_series_covid19_confirmed_global.csv";
        return readCSVFile(filepath);
    }
    
    public static List<String[]> provideC19GlobalDeathData() throws IOException, CsvException{
        String filepath = "Coviddata/time_series_covid19_deaths_global.csv";
        return readCSVFile(filepath);
    }
    
    public static List<String[]> provideC19GlobalRecoveredData() throws IOException, CsvException{
        String filepath = "Coviddata/time_series_covid19_recovered_global.csv";
        return readCSVFile(filepath);
    }
    
    
}
