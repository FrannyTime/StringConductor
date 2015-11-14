import java.util.*;
import java.lang.StringBuilder;

public class DALMerged_v2
{
    public static TreeMap<String, Integer> frequencyTable = new TreeMap<>();
    /**
     * PhraseObject DataStructure to store phrases
     */
    public static TreeMap<String, DALPhraseObject> phraseTable = new TreeMap<>();


    // default constructor
    public DALMerged_v2() {}



    // called by Controller on behalf of view
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

                    addToFrequencyTable(hashEntry);
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




    public static String removeLastChar(String str)
    {
        return str.substring(0,str.length()-1);
    }



    private static void addToFrequencyTable(String hashEntry)
    {
        hashEntry = removeLastChar(hashEntry);

        if (frequencyTable.containsKey(hashEntry))
        {
            frequencyTable.put(hashEntry, frequencyTable.get(hashEntry) + 1);
        }
        else
        {
            frequencyTable.put(hashEntry, 1);
        }
    }



    /**
     * Pete
     * @param phrase The Phrase to add store
     * @param phraseLength The number of words in the phrase
     */
    private static void addToPhraseTable(String phrase, int phraseLength)
    {
        phrase = phrase.trim();
        if (!phraseTable.containsKey(phrase))
        {
            phraseTable.put(phrase, new DALPhraseObject(phrase, phraseLength));
        }
        //if the value already exists in the tree
        phraseTable.get(phrase).incrementFrequency();
    }//method


    public static ArrayList<String> getWordCountResults(Integer numberOfWordsFilter)
    {
        return filterApplier(numberOfWordsFilter);
    }//method



    /**
     * Pete
     * This method returns an arry with the correct phrases of the right phrase length.
     * @param numberOfWordsFilter is the number of words in phrase
     * @return
     */
    private static ArrayList<String> filterApplier(Integer numberOfWordsFilter)
    {

        //return values in a collection
        Collection<DALPhraseObject> po = phraseTable.values();

        //save the values in an ArrayList<String>
        ArrayList<DALPhraseObject> PhraseObjectArrayList = new ArrayList<>();
        PhraseObjectArrayList.addAll(po);

        //filtered array List
        ArrayList<DALPhraseObject> filteredPhraseObjects = new ArrayList<>();
        //Only save PhraseObjects if they match numberOfWordsFilter.
        for(DALPhraseObject tempPhraseObject: PhraseObjectArrayList){
            if(tempPhraseObject.getNumberOfWords() == numberOfWordsFilter){
                //saveFiltered values
                filteredPhraseObjects.add(tempPhraseObject);
            }//if
        }//for

        //Sort the ArrayList by frequency
        filteredPhraseObjects.sort(new DALPhraseFrequencyComparator());

        //Translate the ArrayList<PhraseObjects> into ArrayList<Strings>
        ArrayList<String> filteredPhraseStrings = new ArrayList<>();
        String s ="Phrase Length"+"\t \t"+"Frequency"+"\t \t" + "Phrase"+"\n";
        filteredPhraseStrings.add(s);

        for(DALPhraseObject tempPhraseObject: filteredPhraseStrings){
            s = tempPhraseObject.getNumberOfWords() + "\t \t"+
                    tempPhraseObject.getFrequency() + "\t \t" +
                    tempPhraseObject.getPhrase();
            filteredPhraseStrings.add(s);
        }//for

        //return the sorted ArrayList of Strings
        return filteredPhraseStrings;
    }//method


    /**
     * Franny
     * Returns the specific String if it is found in the sorted document
     * @param phrase
     * @return The specific string and it's frequency
     */
    public static String getPhraseFreqResults(String phrase)
    {
        String returnString = new String();

        try
        {
            if (frequencyTable.containsKey(phrase))
            {
                Integer count;
                String test = new String();
                count = frequencyTable.get(phrase);
                test = String.valueOf(count);
                returnString = phrase + "\t " + test;
            }
            else
            {
                returnString = phrase + "\t " + "0";
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("null error");
        }

        return returnString;
    }

}
