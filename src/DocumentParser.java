/**
 * Created by PeteCurtis on 10/22/15.
 */
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;

/**
 * Created by PeteCurtis on 10/15/15.
 *This takes the entire document as one string delimited by periods and parses it into the hash table.
 */
public class DocumentParser {
    //GLOBAL VARIABLES Here or somewhere higher?
    private static final String PHRASE_DELIMITER = " ";
    public static final Integer MAX_PHRASE_LENGTH = 10;
    public static final Integer MIN_PHRASE_LENGTH = 2;
    public HashMap<String, Integer> phraseTable = new HashMap<String, Integer>();

    //attributes
    Hashtable<String, Double> phraseTable = new Hashtable();

    /** Document Parser
     *
     * @param document
     * This method takes the entire document as a String and seperates each string.
     */
    public void documentParser(String document) {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(document);
        int start = iterator.first();
        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next()) {

            String mySentence = document.substring(start, end);
            //pass each sentence to sentenceParser Method
            //sentenceParser(sentence);

            System.out.println(document.substring(start, end));
        } //for loop
    } //method

    /** arraifySentence puts the words from a sentance into an array.
     * @param sentance is a String of words that we want to input into an array.
     * @return The arrayList of words in the sentance
     */
    public String[] arrayifySentence(String sentance){
        String[] sentanceArray = null;


        //stuff
        return sentanceArray;
    }

    //processSentenceArray

    /**
     *
     * @param sentenceArray
     */
    private static void processSentenceArray(String[] sentenceArray)
    {
        int maxPhraseLength = MAX_PHRASE_LENGTH;

        if (sentenceArray.length < maxPhraseLength)
        {
            maxPhraseLength = sentenceArray.length;
        }

        Integer phraseLength = MIN_PHRASE_LENGTH;

        for (phraseLength; phraseLength < maxPhraseLength; phraseLength++)
        {
            chopSentenceArray(sentenceArray, phraseLength)
        }
    }

    //PhraseMaker
    private void sentenceParser(String sentence)
    {
        //set delimiter to whitespace
        //loop through each phrase length
        //loop through the sentence

        //store the phrase in the HashTable(with frequency

        int MAX_PHRASE_LENGTH = 10;

        public ArrayList<String> ArayifySentance(String sentance)
        {
            //put the sentance into an arrayList & return that arraylist
        }

    public void ProcessSentaceArray(ArrayList<String> sentanceArray ) {

        //see if sentance is shorter than MAX_PHRASE_LENGTH
        //if shorter use sentance length instaed
        int phraseLength = MAX_PHRASE_LENGTH;
        if (<)
        for ()
    }//method

    //find all phrases of length X & input that phrase into a HashMap(phraseTable)

    private void ChopItUp(int phraseLength, ArrayList<String> sentenceArray){
        int phraseStartPosition = 0;

        while(j <= sentenceArray.length())
    }


    }

}//class

