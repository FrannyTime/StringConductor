///**
// * Created by PeteCurtis on 10/22/15.
// */
//import java.io.FileNotFoundException;
//import java.nio.file.Path;
//import java.text.BreakIterator;
//import java.util.*;
//
///**
// * Created by PeteCurtis on 10/15/15.
// *This takes the entire document as one string delimited by periods and parses it into the hash table.
// */
//public class DocumentParser {
//
//    /** readFile reads a file from String location
//     * @param location location of the document passed as a String
//     * @return the entire document as a single String.
//     */
//    public static String readFile(String  location) throws FileNotFoundException
//    {
//        String theDocument = "";
//
//        java.io.File docFile = new java.io.File(location);
//        try
//        {
//            Scanner input = new Scanner(docFile);
//            while (input.hasNext()) {
//                theDocument += input.nextLine();
//            }
//            input.close();
//        }
//        catch(FileNotFoundException e)
//        {
//            System.out.println("FileNotFound");
//        } //catch block
//
//        return theDocument;
//    }
//
//    /** Document Parser
//     *
//     * @param document
//     * This method takes the entire document as a String and seperates each string.
//     */
//    public void documentParser(String document) {
//        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
//        iterator.setText(document);
//        int start = iterator.first();
//        for (int end = iterator.next();
//             end != BreakIterator.DONE;
//             start = end, end = iterator.next()) {
//
//            String mySentence = document.substring(start, end);
//            //pass each sentence to sentenceParser Method
//            //sentenceParser(sentence);
//
//            System.out.println(document.substring(start, end));
//        } //for loop
//    } //method
//
//    /** arraifySentence puts the words from a sentance into an array.
//     * @param sentance is a String of words that we want to input into an array.
//     * @return The arrayList of words in the sentance
//     */
//    public String[] arrayifySentence(String sentance){
//        String[] sentanceArray = null;
//
//        return sentanceArray;
//    }
//
//    /** processSentenceArray
//     * Loops over the sentence passing all of the phrases up to the number of words in the sentance or the MAX_PHRASE_LENGTH,
//     * whichever is smaller.
//     * @param sentenceArray
//     */
//    private static void processSentenceArray(String[] sentenceArray)
//    {
//        int maxPhraseLength = MAX_PHRASE_LENGTH;
//
//        if (sentenceArray.length < maxPhraseLength)
//        {
//            maxPhraseLength = sentenceArray.length;
//        }
//
//        Integer phraseLength = MIN_PHRASE_LENGTH;
//
//        for (int i = phraseLength; i < maxPhraseLength; i++)
//        {
//            chopSentenceArray(sentenceArray, i);
//        }
//    }
//
//    /**
//     * chopSentanceArray pushes
//     * @param sentenceArray The single sentance that is being added to the hashmap
//     * @param phraseLength The length of the phrases currently being generated
//     */
//    private static void chopSentenceArray(String[] sentenceArray, Integer phraseLength)
//    {
//        int phraseStartPosition = 0;
//
//        while (phraseStartPosition <= sentenceArray.length )
//        {
//            try
//            {
//                if (sentenceArray[phraseStartPosition + (phraseLength-1)] != null)
//                {
//                    int k = phraseStartPosition;
//                    String hashEntry = new String();
//                    for (k = startPosition; k < startPosition + (phraseLength - 1); k++)
//                    {
//                        hashentry += sentenceArray[k] + delimiter;
//                    }
//
//                    addToPhraseTable(hashEntry);
//                    phraseTable.put(hashEntry, number);
//                }
//                else
//                {
//                    continue;
//                }
//            }
//            catch (ArrayIndexOutOfBoundsException aiobe)
//            {
//                System.out.println(aiobe);
//            }
//
//            phraseStartPosition++;
//        }
//    }
//}//class
//
