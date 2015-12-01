import java.util.*;
import java.lang.StringBuilder;

public class DALMerged_v2
{
    public static TreeMap<String, Integer> frequencyTable = new TreeMap<>();
    /**
     * PhraseObject DataStructure to store phrases
     */
    public static TreeMap<String, DALPhraseObject> phraseTable = new TreeMap<>();


    /**
     *Default Constructor for DAL Merged
     */
    public DALMerged_v2() {}

    /**
     *This cycles through the sentance array and passes every maximum sized sentance to chop sentance array.
     * The end result is you will have cycled through a sentance n times.
     * n=numberof words in sentance % MAX_PHASE_LENGTH +1.
     *
     * @param sentenceArray String[] with one word in each container
     * @param MAX_PHRASE_LENGTH the maximum defined phrase length constant
     * @param MIN_PHRASE_LENGTH the minimum defined phrase length constant
     * @param PHRASE_DELIMITER the value that seperates each word.
     */
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


    /**
     *This first loops over the sentance in the sentance array and for each sentance it loops returns all the phrase of
     * length phraseLength.
     * @param sentenceArray this is the specific number of words to loop over
     * @param phraseLength the number of words in the phrase we are currently cutting for
     *                     Example: 5 words in this phrase, next iteration 6 words in the phrase
     * @param PHRASE_DELIMITER This is the character that seperates each phrase.
     */
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

                    String hashEntry = addTheWords(sentenceArray, PHRASE_DELIMITER,
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

    /**
     *addTheWords takes a String[] and returns a single string of all of the words in each section.
     * @param sentenceArray contains all of the words in the sentance, one in each arrayslot.
     * @param PHRASE_DELIMITER
     * @param phraseStartPosition
     * @param phraseLength
     * @param k
     * @return The built string of the words contained in String[]
     */
    private static String addTheWords(String[] sentenceArray, String PHRASE_DELIMITER,
                                      int phraseStartPosition, int phraseLength, int k)
    {
        String hashEntry = new String();
        StringBuilder stringBuilder = new StringBuilder();


        for (; k < phraseStartPosition + (phraseLength); k++)
        {
            stringBuilder.append(sentenceArray[k] + PHRASE_DELIMITER);
        }

        hashEntry = stringBuilder.toString();
        return hashEntry;
    }


    /**
     *This removes the last character of the String and returns the edited string
     * @param str
     * @return
     */
    public static String removeLastChar(String str)
    {
        return str.substring(0,str.length()-1);
    }


    /**
     *This adds the value to the frequency table
     * @param hashEntry
     */
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
     *Adds the phrases to DALPhraseObjects in the phraseTable
     * @param phrase The Phrase to add store
     * @param phraseLength The number of words in the phrase
     */
    private static void addToPhraseTable(String phrase, int phraseLength)
    {
        //remove whitespace from the phrase
        phrase = phrase.trim();

        //if the phrase is not in the phraseTable
        if (!phraseTable.containsKey(phrase))
        {
            phraseTable.put(phrase, new DALPhraseObject(phrase, phraseLength));
        }

        //if the phrase already exists in the phraseTable
        phraseTable.get(phrase).incrementFrequency();
    }//method

    /**
     * Pete
     * Public accessor meothod for filterApplier
     *
     * @param numberOfWordsFilter
     * @return the ArrayList<String> returned from filterApplier
     */
    public static ArrayList<String> getWordCountResults(Integer numberOfWordsFilter)
    {
        return filterApplier(numberOfWordsFilter);
    }//method


    /**
     * Pete
     * Filters the PhraseObjects and returns an ArrayList<String> of only those Phrases
     * Format: numberofWords \t\t Frequency \t \t Phrase
     *
     * @param numberOfWordsFilter is the number of words in phrase
     * @return an ArrayList<String> with the phrases of the right phrase length.
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
        String s ="Words"+"\t"+"Freq"+"\t \t" + "Phrase";
        filteredPhraseStrings.add(s);

        for(DALPhraseObject tempPhraseObject: filteredPhraseObjects){
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
     * @param phrase the exact string being searched for
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
