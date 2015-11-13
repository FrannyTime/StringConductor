import java.util.Comparator;

/**
 * Created by PeteCurtis on 11/10/15.
 * This comparators is used to sort by the largest frequency first for DALPhraseObjects
 */
public class DALPhraseFrequencyComparator implements Comparator<DALPhraseObject> {

    @Override
    public int compare(DALPhraseObject po1, DALPhraseObject po2) {
        if (po1.getFrequency() > po2.getFrequency()) {
            return -1;
        }
        if (po1.getFrequency() < po2.getFrequency()) {
            return 1;
        }
        //if they are the same phrase
        return 0;

    }//method
}//class
