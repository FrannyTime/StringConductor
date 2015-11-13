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
    public static TreeMap<String, DALPhraseObject> phraseTable = new TreeMap<>();

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
                    hashEntry.trim();
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
//            DALPhraseObject po = new DALPhraseObject(phrase,phraseLength);
            phraseTable.put(phrase, new DALPhraseObject(phrase, phraseLength));
        }
        //if the value already exists in the tree
            phraseTable.get(phrase).incrementFrequency();
    }//method


    public ArrayList<String> getPhraseFreqResults(String phrase){
        return new ArrayList<String>();
    }//method

    public static ArrayList<String> getWordCountResults(Integer numberOfWordsFilter){
        return filterApplier(numberOfWordsFilter);
    }//method

    /**
     * This method returns the number of words in an array of Strings
     * Startings with
     * @param numberOfWordsFilter is the number of words in phrase
     * @return
     */
    private static ArrayList<String> filterApplier(Integer numberOfWordsFilter){
        //return values in a collection
        Collection<DALPhraseObject> po = phraseTable.values();
        //save the values in an ArrayList<String>
        ArrayList<DALPhraseObject> PhraseObjectArrayList = new ArrayList<>();
        PhraseObjectArrayList.addAll(po);

        //remove all Phrase objects that do not match numberOfWordsFilter
        //if PhraseObjects match numberOfWordsFilter save them
        ArrayList<DALPhraseObject> filteredPhraseObjects = new ArrayList<>();
        System.out.println("\n"+"All objects");

        for(DALPhraseObject tempPhraseObject: PhraseObjectArrayList){
            if(tempPhraseObject.getNumberOfWords()==numberOfWordsFilter){
                //saveFiltered values
                filteredPhraseObjects.add(tempPhraseObject);
                }//if
        }//for

        //Sort the ArrayList by frequency
        filteredPhraseObjects.sort(new DALPhraseFrequencyComparator());

//        Translate the ArrayList<PhraseObjects> into ArrayList<Strings>
        ArrayList<String> filteredPhraseStrings = new ArrayList<>();
        String s ="Phrase Length"+"\t \t"+"Frequency"+"\t \t" + "Phrase"+"\n";
        filteredPhraseStrings.add(s);

        for(DALPhraseObject tempPhraseObject: PhraseObjectArrayList){
            s = tempPhraseObject.getNumberOfWords() + "\t \t"+
                tempPhraseObject.getFrequency() + "\t \t" +
                tempPhraseObject.getPhrase() +"\n";
            filteredPhraseStrings.add(s);
            }//for

            //return the sorted ArrayList of Strings
        return filteredPhraseStrings;
    }//class

}//class
