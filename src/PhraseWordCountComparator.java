import java.util.Comparator;

/**
 * Created by PeteCurtis on 11/10/15.
 */
public class PhraseWordCountComparator implements Comparator<PhraseObject> {
    @Override
    public int compare(PhraseObject po1, PhraseObject po2) {
        if (po1.getNumberOfWords() < po2.getNumberOfWords()) {
            return -1;
        }
        if (po1.getNumberOfWords() > po2.getNumberOfWords()) {
            return 1;
        }
        //if they are the same phrase
        return 0;

    }//method
}//class
