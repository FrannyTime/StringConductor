import apple.laf.JRSUIUtils;
import javafx.util.Pair;
import java.util.*;
import java.lang.StringBuilder;

/**
 * Created by PeteCurtis on 11/10/15.
 */
/**
 * investigate why you cannot set a MAX_PHRASE_LENGTH
 */

public class SC_Model_v2_PC {

//    public static TreeMap<String, Pair<Integer,Integer>> phraseTable = new TreeMap<>();
    public static TreeMap<String, PhraseObject> phraseTable = new TreeMap<>();

    public static void processSentenceArray(String[] sentenceArray, int MAX_PHRASE_LENGTH,
                                            int MIN_PHRASE_LENGTH, String PHRASE_DELIMITER)
    {
        int maxPhraseLength = MAX_PHRASE_LENGTH;

        if (sentenceArray.length < maxPhraseLength)
        {
            maxPhraseLength = sentenceArray.length;
        }

        int phraseLength;

        for (phraseLength = MIN_PHRASE_LENGTH; phraseLength <= maxPhraseLength; phraseLength++)
        {
            chopSentenceArray(sentenceArray, phraseLength, PHRASE_DELIMITER);
        }
    }

    private static String addTheWords(String[] sentenceArray, StringBuilder stringBuilder, String PHRASE_DELIMITER,
                                      int phraseStartPosition, int phraseLength, int k)
    {
        String hashEntry = new String();

        for (; k < phraseStartPosition + (phraseLength); k++)
        {
            stringBuilder.append(sentenceArray[k] + PHRASE_DELIMITER);
        }

        hashEntry = stringBuilder.toString();
        return hashEntry;
    }

    private static void chopSentenceArray(String[] sentenceArray, int phraseLength, String PHRASE_DELIMITER)
    {
        int phraseStartPosition = 0;

        while (phraseStartPosition <= (sentenceArray.length - phraseLength))
        {
            try
            {
                if (sentenceArray[phraseStartPosition + (phraseLength - 1)] != null)
                {
                    int k = phraseStartPosition;
                    StringBuilder stringBuilder = new StringBuilder();

                    String hashEntry = addTheWords(sentenceArray, stringBuilder, PHRASE_DELIMITER,
                            phraseStartPosition, phraseLength, k);

                    addToPhraseTable(hashEntry, phraseLength);
                }
                else
                {
                    break;
                }

            }
            catch (ArrayIndexOutOfBoundsException aiobe)
            {
                System.out.println(aiobe);
            }

            phraseStartPosition++;
        }
    }//method

    private static void addToPhraseTable(String phrase, int phraseLength)
    {
        if (!phraseTable.containsKey(phrase))
        {
//            PhraseObject po = new PhraseObject(phrase,phraseLength);
            phraseTable.put(phrase, new PhraseObject(phrase, phraseLength));
        }
        //if the value already exists in the tree
            phraseTable.get(phrase).incrementFrequency();
    }//method


}//class
