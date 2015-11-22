/**
 * Controller class for String Conductor. Controller fulfills requests from View class for importing files and applying
 * filters by retrieving and formatting the appropriate data from the DAL class.
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 *
 */
public class ControlLayer
{

    public static final Integer MAX_PHRASE_LENGTH = 11;
    public static final Integer MIN_PHRASE_LENGTH = 2;
    private static final String PHRASE_DELIMITER = " ";
    public static String theDocument = "";
    private static DALMerged_v2 model = new DALMerged_v2();

    public static final String FILE_LOCATION = "/Users/Natera/Documents/CS/SC_text.txt";

    /**
     *
     * @param location
     * @return
     * @throws FileNotFoundException
     */
    public static String readFile(String location) throws FileNotFoundException
    {
        java.io.File docFile = new java.io.File(location);
        try
        {
            Scanner input = new Scanner(docFile);

            while (input.hasNext())
            {
                //TESTING
                theDocument += input.nextLine()+"\n";
            }
            input.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("FileNotFound");
        }

        return theDocument;
    }

    /**
     * Returns the unedited version of the text to the View
     * @return the original document.
     */

    public static String getOriginalDocument()
    {
        return theDocument;
    }

    public static String processArrayList(ArrayList<String> list)
    {
        String returnString = new String();
        StringBuilder stringBuilder = new StringBuilder();

        for (String item : list)
        {
            stringBuilder.append(item);
        }

        returnString = stringBuilder.toString();

        return returnString;
    }

    public static String applyFilters(String phrase, Integer wordCount, boolean wantsPhrase, boolean wantsWordCount)
    {
        String returnString = new String();

        if (wantsPhrase && wantsWordCount)
        {
            System.out.println("Cannot perform both filters at once.");
        }

        if (!wantsPhrase && !wantsWordCount)
        {
            System.out.println("Both filters were false.");
        }

        if (wantsPhrase && !wantsWordCount)
        {
            returnString = getFilteredResultsBySearchPhrase(phrase);
            System.out.println(returnString);
        }

        if (wantsWordCount && !wantsPhrase)
        {
            ArrayList<String> list = new ArrayList<>();
            list = model.getWordCountResults(wordCount);
            StringBuilder stringBuilder = new StringBuilder();

            for (String item : list)
            {
                stringBuilder.append(item+ "\n");
            }

            returnString = stringBuilder.toString();
            System.out.println(returnString);
        }

        return returnString;

    }

    public static String getFilteredResultsByWordCount(Integer wordCount)
    {
        String returnString = new String();
        ArrayList<String> list = new ArrayList();
        list = model.getWordCountResults(wordCount);
        StringBuilder stringBuilder = new StringBuilder();

        for (String item : list)
        {
            stringBuilder.append(item+ "\n");
        }

        returnString = stringBuilder.toString();

        return returnString;
    }


    public static String getFilteredResultsBySearchPhrase(String phrase)
    {
        String returnString = new String();
        phrase = phrase.trim();
        returnString = model.getPhraseFreqResults(phrase);
        return returnString;
    }


    public static String getImportResults(String inputFileLocation) throws IOException
    {
        String returnString = new String();

        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);

        String document = readFile(inputFileLocation);

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
            String s = entry.getValue() + "\t" + entry.getKey() + "\n";
            stringBuilder.append(s);
        }

        printFrequencyTable();

        returnString = stringBuilder.toString();

        return returnString;
    }

    public static void printFrequencyTable()
    {
        for (Map.Entry<String, Integer> entry : model.frequencyTable.entrySet())
        {
            System.out.println(entry.getValue() + "\t" + entry.getKey());
        }
    }

}
//

