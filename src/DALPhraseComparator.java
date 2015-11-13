import java.util.Comparator;

/**
 * Created by PeteCurtis on 11/10/15.
 */
public class DALPhraseComparator implements Comparator<DALPhraseObject> {

    @Override
    public int compare(DALPhraseObject po1, DALPhraseObject po2) {

        //if phrases have the same frequency return the smaller based on phrase length
        //if the phrase in 1 is smaller than phrase 2
        if (po1.getPhrase().compareTo(po2.getPhrase()) < 0) {
            return -1;
        }
        //string 1 phrase is larger than string 2 phrase
        if (po1.getPhrase().compareTo(po2.getPhrase()) > 0) {
            return 1;
        }
        //if they are the same phrase
        return 0;
    }
}
