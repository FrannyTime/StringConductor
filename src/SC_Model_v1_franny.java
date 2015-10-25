

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;
import java.lang.StringBuilder;



public class SC_Model_v1_franny
{
    private static final String PHRASE_DELIMITER1 = " ";
    private static final String PHRASE_DELIMITER2 = "\n";
    public static final Integer MAX_PHRASE_LENGTH = 10;
    public static final Integer MIN_PHRASE_LENGTH = 2;
    public static TreeMap<String, Integer> phraseTable = new TreeMap<>();
    //public static final String FILE_LOCATION = "/Users/Natera/Documents/CS/SC_text.txt";
    public static final String FILE_LOCATION = "/Users/danieltam/Downloads/SC_text";




    public SC_Model_v1_franny() {}

    public static String readFile(String location) throws FileNotFoundException
    {
        String theDocument = "";

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
        } //catch block

        return theDocument;
    }

    public void processSentenceArray(String[] sentenceArray)
    {
        int maxPhraseLength = MAX_PHRASE_LENGTH;

        if (sentenceArray.length < maxPhraseLength)
        {
            maxPhraseLength = sentenceArray.length;
        }

        int phraseLength;

        for (phraseLength = MIN_PHRASE_LENGTH; phraseLength <= maxPhraseLength; phraseLength++)
        {
          //  System.out.println(phraseLength);
            chopSentenceArray(sentenceArray, phraseLength);
        }
    }

    private static void chopSentenceArray(String[] sentenceArray, Integer phraseLength)
    {
        int phraseStartPosition = 0;
        int successCounter = 1;

        while (phraseStartPosition <= (sentenceArray.length - phraseLength))
        {
            try
            {
                if (sentenceArray[phraseStartPosition + (phraseLength - 1)] != null)
                {
                    int k = phraseStartPosition;
                    String hashEntry = new String();
                    StringBuilder stringBuilder = new StringBuilder();

                    for (; k < phraseStartPosition + (phraseLength); k++)
                    {
                        stringBuilder.append(sentenceArray[k] + PHRASE_DELIMITER1);
                    }

                    hashEntry = stringBuilder.toString();
                    addToPhraseTable(hashEntry);
//                    System.out.println(hashEntry + "\t" + phraseLength);
//                    System.out.println("success" + "\t" + successCounter);
//                    successCounter++;
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

//    public static void main (String[] args) throws IOException
//    {
//        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
//
//        String document = readFile(FILE_LOCATION);
//        iterator.setText(document);
//        int start = iterator.first();
//
//        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
//        {
//            String sentence = document.substring(start, end);
//            String[] sentenceArray = sentence.split("\\s+");
//            this.processSentenceArray(sentenceArray);
//        }
//
//        for (Map.Entry<String, Integer> entry : phraseTable.entrySet())
//        {
//            System.out.println(entry);
//        }
//    }
}




