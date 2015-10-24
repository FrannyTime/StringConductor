

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;



public class SC_Model_v1_franny
{
    private static final String PHRASE_DELIMITER = " ";
    public static final Integer MAX_PHRASE_LENGTH = 10;
    public static final Integer MIN_PHRASE_LENGTH = 2;
    public HashMap<String, Integer> phraseTable = new HashMap<String, Integer>();

    BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
    String document = fileReader(location);
    iterator.setText(document);
    int start = iterator.first();

    for (int end = iterator.next(); end != BreakIterator.DONE;
    start = end, end = iterator.next())
    {
        String sentence = document.substring(start, end);
        String[] sentenceArray = sentence.split("\\s+");
        processSentenceArray(sentenceArray);

    }

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
                    for (k = phraseStartPosition; k < phraseStartPosition + (phraseLength - 1); k++)
                    {
                        hashentry += sentenceArray[k] + PHRASE_DELIMITER;
                    }

                    addToPhraseTable(hashEntry);
                    phraseTable.put(hashEntry, number);
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

        String filePath = "/Users/Natera/Documents/CS/SC_text.txt";
        String myString = readFile(filePath);
        System.out.println(myString);

        DocumentParser myParser = new DocumentParser();
        myParser.documentParser(myString);
    }
}



