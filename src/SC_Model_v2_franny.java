import java.util.*;
import java.lang.StringBuilder;
//hi
public class SC_Model_v2_franny
{
// bur
    public static TreeMap<String, Integer> frequencyTable = new TreeMap<>();

    public SC_Model_v2_franny() {}

    public static String getPhraseFreqResults(String phrase)
    {
        String returnString = new String();
        Integer count;
        String test = new String();
        count = frequencyTable.get(phrase);
        test = String.valueOf(count);
        returnString = phrase + "\t" + test;
        return returnString;
    }

    public static void processSentenceArray(String[] sentenceArray, int MAX_PHRASE_LENGTH,
                                             int MIN_PHRASE_LENGTH, String PHRASE_DELIMITER)
    {
        int maxPhraseLength = MAX_PHRASE_LENGTH;

        if (sentenceArray.length < maxPhraseLength)
        {
            maxPhraseLength = sentenceArray.length;
        }

        int phraseLength;

        String s = new String();
        s = sentenceArray[sentenceArray.length - 1];
        s = s.substring(0, s.length() - 1);
        sentenceArray[sentenceArray.length - 1] = s;

        for (phraseLength = MIN_PHRASE_LENGTH; phraseLength <= maxPhraseLength; phraseLength++)
        {
            chopSentenceArray(sentenceArray, phraseLength, PHRASE_DELIMITER);
        }
    }

    public static String removeLastChar(String str)
    {
        return str.substring(0,str.length()-1);
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

                    hashEntry = removeLastChar(hashEntry);

                    addToFrequencyTable(hashEntry);
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

    private static void addToFrequencyTable(String hashEntry)
    {
        if (frequencyTable.containsKey(hashEntry))
        {
            frequencyTable.put(hashEntry, frequencyTable.get(hashEntry) + 1);
        }
        else
        {
            frequencyTable.put(hashEntry, 1);
        }
    }
}




