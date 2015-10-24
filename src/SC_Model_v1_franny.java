

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;
import java.lang.StringBuilder;



public class SC_Model_v1_franny
{
    private static final String PHRASE_DELIMITER = " ";
    public static final Integer MAX_PHRASE_LENGTH = 10;
    public static final Integer MIN_PHRASE_LENGTH = 2;
    public static HashMap<String, Integer> phraseTable = new HashMap<String, Integer>();
    public static final String FILE_LOCATION = "location";



    private static void processSentenceArray(String[] sentenceArray)
    {
        int maxPhraseLength = MAX_PHRASE_LENGTH;

        if (sentenceArray.length < maxPhraseLength)
        {
            maxPhraseLength = sentenceArray.length;
        }

        int phraseLength;

        for (phraseLength = MIN_PHRASE_LENGTH; phraseLength < maxPhraseLength; phraseLength++)
        {
            chopSentenceArray(sentenceArray, phraseLength);
        }
    }

    private static void chopSentenceArray(String[] sentenceArray, Integer phraseLength)
    {
        int phraseStartPosition = 0;

        while (phraseStartPosition <= sentenceArray.length )
        {
            try
            {
                if (sentenceArray[phraseStartPosition + (phraseLength-1)] != null)
                {
                    int k = phraseStartPosition;
                    String hashEntry = new String();
                    StringBuilder stringBuilder = new StringBuilder();

                    for (k = phraseStartPosition; k < phraseStartPosition + (phraseLength - 1); k++)
                    {
                        stringBuilder.append(sentenceArray[k] + PHRASE_DELIMITER);
                    }

                    hashEntry = stringBuilder.toString();
                    addToPhraseTable(hashEntry);
                }
                else
                {
                    continue;
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



    public static void main (String[] args) throws IOException, FileNotFoundException
    {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);

        String document = readFile(FILE_LOCATION);
        iterator.setText(document);
        int start = iterator.first();

        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
        {
            String sentence = document.substring(start, end);
            String[] sentenceArray = sentence.split("\\s+");
            processSentenceArray(sentenceArray);
        }
    }
}



