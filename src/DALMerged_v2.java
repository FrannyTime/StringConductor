import java.util.*;
import java.lang.StringBuilder;

public class DALMerged_v2 {

    /**
     * PhraseObject DataStructure to store phrases
     */
    public static TreeMap<String, DALPhraseObject> phraseTable = new TreeMap<>();



    /**
     * Pete
     * @param phrase The Phrase to add store
     * @param phraseLength The number of words in the phrase
     */
    private static void addToPhraseTable(String phrase, int phraseLength)
    {
        if (!phraseTable.containsKey(phrase))
        {
            phraseTable.put(phrase, new DALPhraseObject(phrase, phraseLength));
        }
        //if the value already exists in the tree
        phraseTable.get(phrase).incrementFrequency();
    }//method

    public static ArrayList<String> getWordCountResults(Integer numberOfWordsFilter){
        return filterApplier(numberOfWordsFilter);
    }//method



    /**
     * This method returns an arry with the correct phrases of the right phrase length.
     * @param numberOfWordsFilter is the number of words in phrase
     * @return
     */
    private static ArrayList<String> filterApplier(Integer numberOfWordsFilter){

        //return values in a collection
        Collection<DALPhraseObject> po = phraseTable.values();

        //save the values in an ArrayList<String>
        ArrayList<DALPhraseObject> PhraseObjectArrayList = new ArrayList<>();
        PhraseObjectArrayList.addAll(po);

        ArrayList<DALPhraseObject> filteredPhraseObjects = new ArrayList<>();
        //Only save PhraseObjects if they match numberOfWordsFilter.
        for(DALPhraseObject tempPhraseObject: PhraseObjectArrayList){
            if(tempPhraseObject.getNumberOfWords()==numberOfWordsFilter){
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

        for(DALPhraseObject tempPhraseObject: PhraseObjectArrayList){
            s = tempPhraseObject.getNumberOfWords() + "\t \t"+
                    tempPhraseObject.getFrequency() + "\t \t" +
                    tempPhraseObject.getPhrase() +"\n";
            filteredPhraseStrings.add(s);
        }//for

        //return the sorted ArrayList of Strings
        return filteredPhraseStrings;
    }//method
}
