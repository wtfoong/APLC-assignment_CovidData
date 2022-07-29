/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplcassignment;

import aplcassignment.dataClass.Country;
import controller.tableData;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author rainy
 */
public class Task2 {
    
    private static final String PL_FILE = "prologData/APLCAssignmentTask2KnowledgebaseFile.pl";
    
    //Listing: 2.1
    // generate knowledgebase file with facts and rules
    public static void createplFile() throws Exception {
        List<Country> cList = CovidData.provideC19GlobalConfirmedCaseData();
        List<String[]> allConfirmedCasesList = tableData.AllCountryConfirmCasesTableData(cList);
        List<String> rulesList = getPrologRules();
        try ( 
            //generate pl file
            PrintWriter plFile = new PrintWriter(PL_FILE)) {
            plFile.println("% Facts");
            
            allConfirmedCasesList.stream().forEach(temp->plFile.println("confirmed_cases(" + temp[0].toLowerCase().replace(" ", "_").replaceAll("[^A-Za-z0-9_]", "") + ", " + temp[1] + ")."));
            
            rulesList.stream().forEach(plFile::println);
        }
    }
    
    //Listing: 2.2
    private static  List<String> getPrologRules() throws Exception{
        List<String> content = Files.readAllLines(Paths.get("prologData/prologRules.txt"));
        return content;
    }
    
    //Listing: 2.3
    // connect to pl file
    private static boolean connectToPLFile(String plFile) {
        String s = "consult('" + plFile + "')";
        Query q1 = new Query(s);
       
        return q1.hasSolution();
    }

    //Listing: 2.4
    // ascending query for confirmed cases
    public static List<String> ascendingConfirmedCases(){
        List<String> output = new ArrayList<>();
        if(connectToPLFile(PL_FILE)){
            output.add("--------------- [ Confirmed Covid-19 Cases in Ascending Order ] ---------------");
            String q = "ascending(Result)";
            Query query = new Query(q);
            Map<String,Term>results = query.oneSolution();
            Arrays.asList(results.get("Result").listToTermArray()).stream().forEach(countrycase->output.add(countrycase.toString().replaceAll("\\[|\\]|\"","").replaceAll("[_,]", " ")));
            output.add("--------------- [ End ] ---------------");
        }
        return output;
    }
    
    //Listing: 2.5
    //descending query for confirmed cases
    public static List<String> descendingConfirmedCases(){
        List<String> output = new ArrayList<>();
        if(connectToPLFile(PL_FILE)){
            output.add("--------------- [ Confirmed Covid-19 Cases in Descending Order ] ---------------");
            String q = "descending(Result)";
            Query query = new Query(q);
            Map<String,Term>results = query.oneSolution();
            Arrays.asList(results.get("Result").listToTermArray()).stream().forEach(countrycase->output.add(countrycase.toString().replaceAll("\\[|\\]|\"","").replaceAll("[_,]", " ")));
            output.add("--------------- [ End ] ---------------");
        }
        return output;
    }
            
}
