/**
 * Created by Natera on 10/25/15.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;
import java.lang.StringBuilder;

public class SC_Controller_v1_franny
{

    public static final Integer MAX_PHRASE_LENGTH = 11;
    public static final Integer MIN_PHRASE_LENGTH = 2;
    private static final String PHRASE_DELIMITER = " ";
    public static String theDocument = "";
    private static SC_View view = new SC_View();
    private static SC_Model_v2_franny model = new SC_Model_v2_franny();


    public static final String FILE_LOCATION = "/Users/Natera/Documents/CS/SC_text.txt";

    public static String readFile(String location) throws FileNotFoundException
    {
        java.io.File docFile = new java.io.File(location);
        try
        {
            Scanner input = new Scanner(docFile);

            while (input.hasNext())
            {
                theDocument += input.nextLine();
            }
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("FileNotFound");
        }

        return theDocument;
    }

    public static void main (String[] args) throws IOException
    {
        SC_View view = new SC_View();
        SC_Model_v2_franny model = new SC_Model_v2_franny();

        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);

        String document = readFile(FILE_LOCATION);
        iterator.setText(document);
        int start = iterator.first();

        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
        {
            String sentence = document.substring(start, end);
            String[] sentenceArray = sentence.split("\\s+");
            model.processSentenceArray(sentenceArray, MAX_PHRASE_LENGTH, MIN_PHRASE_LENGTH, PHRASE_DELIMITER);
        }

        StringBuilder stringBuilder = new StringBuilder();


        for (Map.Entry<String, Integer> entry : model.frequencyTable.entrySet())
        {
            String s = entry.getValue() + "\t" + entry.getKey();
            stringBuilder.append(s);
        }
        view.runApp(args);
    }

//    public static ArrayList<String> getWordCountFreq(Integer wordCount)
//    {
//        String returnString = new String();
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (String entry : )
//
//        returnString = pete.getWordCountResults(wordCount);
//
//        return returnString;
//    }
//
//    public

    public static String getImportResults(String phrase) throws IOException
    {
        String returnString = new String();
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Integer> entry : model.frequencyTable.entrySet())
        {
            System.out.println(entry);
            String s = entry.getValue() + "\t" + entry.getKey() + "\n";
            stringBuilder.append(s);
        }

        returnString = stringBuilder.toString();

        return returnString;
    }

}
//
//SC_Model_v2_franny model = new SC_Model_v2_franny();
//String returnString = new String();
//
//BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
//
//String document = readFile(inputFileLocation);
//iterator.setText(document);
//        int start = iterator.first();
//
//        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
//        {
//        String sentence = document.substring(start, end);
//        String[] sentenceArray = sentence.split("\\s+");
//        model.processSentenceArray(sentenceArray, MAX_PHRASE_LENGTH, MIN_PHRASE_LENGTH, PHRASE_DELIMITER);
//        }
//
