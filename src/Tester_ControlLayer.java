import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

/**
 * Created by Natera on 11/21/15.
 */
public class Tester_ControlLayer
{
    private static String passMessage = "Test Passed:\t\t";
//    public static String readFile(String location) throws FileNotFoundException
//
//    public static String getOriginalDocument()
//
//    public static String processArrayList(ArrayList<String> list)
//
//    public static String applyFilters(String phrase, Integer wordCount, boolean wantsPhrase, boolean wantsWordCount)
//
//    public static String getFilteredResultsByWordCount(Integer wordCount)
//
//    public static String getFilteredResultsBySearchPhrase(String phrase)
//
//    public static String getImportResults(String inputFileLocation) throws IOException
//
//    public static void printFrequencyTable()
@Test

    public void testReadFile() throws FileNotFoundException
    {
        String testMethodName = "testReadFile()";
        ControlLayer controller = new ControlLayer();

        String expectedString = "This is a test doc.\n";
        String testDocLocation = "/Users/Natera/Documents/StringConductor/src/TestDoc";
        String testDoc = controller.readFile(testDocLocation);
        assertEquals(expectedString, testDoc);
        System.out.println(passMessage + testMethodName);
    }

}
