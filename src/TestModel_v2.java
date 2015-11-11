import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Natera on 11/10/15.
 */
public class TestModel_v2
{
    public static final Integer MAX_PHRASE_LENGTH = 11;
    public static final Integer MIN_PHRASE_LENGTH = 4;
    private static final String PHRASE_DELIMITER = " ";

    public static String theDocument = "";

    public static final String FILE_LOCATION = "/Users/Natera/Documents/CS/SC_text_new.txt";

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
        SC_Model_v2_franny model = new SC_Model_v2_franny();

        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);

        String document = readFile(FILE_LOCATION);
        iterator.setText(document);
        int start = iterator.first();

        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
        {

            String sentence = document.substring(start, end);
            System.out.println(sentence);
            String[] sentenceArray = sentence.split("\\s+");
            model.processSentenceArray(sentenceArray, MAX_PHRASE_LENGTH, MIN_PHRASE_LENGTH, PHRASE_DELIMITER);
        }

        StringBuilder stringBuilder = new StringBuilder();


        for (Map.Entry<String, Integer> entry : model.frequencyTable.entrySet())
        {
//            if (entry.getKey() )
//            System.out.println(entry);
            String s = entry.getValue() + "\t" + entry.getKey();
            System.out.println(s);
           // stringBuilder.append(s);
        }
       // view.runApp(args);
    }
}
