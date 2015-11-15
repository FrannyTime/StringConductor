/**
 * Controller class for String Conductor. Controller fulfills requests from View class for importing files and applying
 * filters by retrieving and formatting the appropriate data from the DAL class.
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;
import java.lang.StringBuilder;


public class SC_Controller_v1_franny
{
//attributes
    public static final Integer MAX_PHRASE_LENGTH = 11;
    public static final Integer MIN_PHRASE_LENGTH = 2;
    private static final String PHRASE_DELIMITER = " ";
    public static String theDocument = "";
    private static DALMerged_v2 model = new DALMerged_v2();
    private static final String IMPORT_MESSAGE = "Import Successful" + "\n" +"Apply Filters for more value!";

    //TESTING
    public static final String FILE_LOCATION = "/Users/Natera/Documents/CS/SC_text.txt";

    /**
     * Francisco
     *Reads the file at "location" into the String "theDocument"
     * Takes FileNotFoundExceptions into account & throws it back to the View if needed
     *
     * @param location
     * @return theDocument, that was built
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
     * Pete
     * Returns the unedited version of the text to the View
     * @return the original document.
     */
    public static String getOriginalDocument()
    {
        return theDocument;
    }

    /**
     * Francisco
     * Takes an arrayList of Strings and returns them and appends them.
     * @param list
     * @return a single string of the arrayList
     */
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

    /**
     * Francisco
     *
     * This calls confirms & passes the correct parameters to filter data from the DAL layers
     * @param phrase
     * @param wordCount
     * @param wantsPhrase
     * @param wantsWordCount
     * @return The String of the values filtered by wordCount OR by the phrase that exists
     */
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

        if (wantsPhrase && !wantsWordCount && !phrase.equals(null))
        {
            returnString = getFilteredResultsBySearchPhrase(phrase);
        }

        if (wantsWordCount && !wantsPhrase && wordCount != null)
        {
            returnString = getFilteredResultsByWordCount(wordCount);
        }

        return returnString;

    }

    /**
     * This passes the view wordCount to the DAL layer and builds and returns a single string to the View
     * @param wordCount
     * @return string of all the word counts, frequencies, and phrases in sorted order
     */
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

    /**
     * Passes the phrase parameter to the DAL and returns a single string to the View
     * @param phrase
     * @return string of all the word counts, frequencies, and phrases in sorted order
     */
    public static String getFilteredResultsBySearchPhrase(String phrase)
    {
        String returnString = new String();
        phrase = phrase.trim();
        returnString = model.getPhraseFreqResults(phrase);
        return returnString;
    }

    /**
     * Reads the passed file Location, calls processing sentanceArray(), and returns the raw tree values to the View Layer
     * @param inputFileLocation
     * @return
     * @throws IOException
     */
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
            //tests the output to the console
 //       printFrequencyTable();

        returnString = stringBuilder.toString();

//        return returnString;
        return IMPORT_MESSAGE;
    }

    /**
     * Francisco
     * Testing method to print the table to the console
     * Assumes table is not private
     */
    public static void printFrequencyTable()
    {
        for (Map.Entry<String, Integer> entry : model.frequencyTable.entrySet())
        {
            System.out.println(entry.getValue() + "\t" + entry.getKey());
        }
    }

}
//

