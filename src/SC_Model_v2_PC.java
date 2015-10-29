/**
 * Created by PeteCurtis on 10/25/15.
 */
import java.util.*;
import java.lang.StringBuilder;

public class SC_Model_v2_PC
{

    public static TreeMap<String, Integer> phraseTable = new TreeMap<>();
    public static TreeMap<Integer, String[]> FrequencyTable = new TreeMap<>();


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
        String hashEntry = new String;

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

                    addToPhraseTable(hashEntry);
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
    }

    private static void addToPhraseTable(String hashEntry)
    {
        if (phraseTable.containsKey(hashEntry))
        {
            phraseTable.put(hashEntry, phraseTable.get(hashEntry) + 1);
        }
        else
        {
            phraseTable.put(hashEntry, 1);
        }
    }

    //This access the HashTable & adds all the completed values to the Frequency Tree.
    private static void phraseTableToFrequencyTree()
    {
    //make a tree copy
        //add a node(call add to frequency)
        //pop value off of phraseTable
    }

    //this adds a single Map Entry to the frequnecyTree
    private static void addToFrequencyTree(String tableEntry)
    {

    }
}




