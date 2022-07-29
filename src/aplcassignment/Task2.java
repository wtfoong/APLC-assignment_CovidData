/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplcassignment;

import aplcassignment.dataClass.Country;
import controller.tableData;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jpl7.Query;

/**
 *
 * @author rainy
 */
public class Task2 {
    
    private static final String PL_FILE = "APLCAssignmentTask2KnowledgebaseFile.pl";
    
    // generate knowledgebase file with facts and rules
    public static void createplFile() throws Exception {
        List<Country> cList = CovidData.provideC19GlobalConfirmedCaseData();
        List<String[]> allConfirmedCasesList = tableData.AllCountryConfirmCasesTableData(cList);
        List<String> rulesList = getPrologRules();
        try ( 
            //generate pl file
            PrintWriter plFile = new PrintWriter(PL_FILE)) {
            plFile.println("% Facts");
            
            allConfirmedCasesList.stream().forEach(temp->plFile.println("confirmed_cases(" + temp[0] + ", " + temp[1] + ")."));
            
            rulesList.stream().forEach(plFile::println);
        }
    }
    
    private static  List<String> getPrologRules() throws Exception{
        List<String> content = Files.readAllLines(Paths.get("prologRules.txt"));
        return content;
    }
    
    
    // connect to pl file
    private static boolean connectToPLFile(String plFile) {
        String s = "consult('" + plFile + "')";
        Query q1 = new Query(s);
       
        return q1.hasSolution();
    }
    
    
    
    public static void main(String[] args) {
        try {
            createplFile();
            
        } catch (Exception ex) {
            Logger.getLogger(Task2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
