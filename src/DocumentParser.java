/**
 * Created by PeteCurtis on 10/22/15.
 */
import java.text.BreakIterator;
import java.util.Hashtable;
import java.util.Locale;

/**
 * Created by PeteCurtis on 10/15/15.
 *This takes the entire document as one string delimited by periods and parses it into the hash table.
 */
public class DocumentParser {

    //attributes
    Hashtable<String, Double> phraseTable = new Hashtable();

    public void documentParser(String document) {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(document);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {

            String sentance = document.substring(start, end);
            //pass each sentance to sentanceParserMethod
            sentanceParser(sentance);

            //System.out.println(document.substring(start, end));
        } //for loop
    } //method

    private void sentanceParser(String sentance)
    {
        //set delimiter to whitespace
        //loop through each phrase length
        //loop through the sentance

        //store the phrase in the HashTable(with frequency)

    }

}//class

