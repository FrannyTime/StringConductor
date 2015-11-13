import java.util.ArrayList;

/**
 * Created by PeteCurtis on 11/10/15.
 */
public class DALTestFrequencyComparator {

    public static void main(String[] args) {

        //comparator testing
        DALPhraseObject po1 = new DALPhraseObject("Code is Fun", 3);
        po1.incrementFrequency();
        DALPhraseObject po2 = new DALPhraseObject("Code is Fun but hard", 5);
        DALPhraseObject po3 = new DALPhraseObject("Code is sometimes dumb", 4);

        ArrayList<DALPhraseObject> poa = new ArrayList<DALPhraseObject>();
//        for (int i=0;i<2;i++)
//        {
            poa.add(po1);
            poa.add(po2);
            poa.add(po3);
//        }
//        poa.add(new DALPhraseObject("Code is Fun but hard", 5));

        poa.sort(new DALPhraseComparator());
        for(DALPhraseObject po: poa){
            System.out.print(po.getNumberOfWords());
            System.out.print(po.getPhrase());
            System.out.println(po.getFrequency());
        }
        System.out.println("");

        poa.sort(new DALPhraseFrequencyComparator());
        for(DALPhraseObject po: poa){
            System.out.print(po.getNumberOfWords());
            System.out.print(po.getPhrase());
            System.out.println(po.getFrequency());
        }
        System.out.println("");

        poa.sort(new DALPhraseWordCountComparator());
        for(DALPhraseObject po: poa){
            System.out.print(po.getNumberOfWords());
            System.out.print(po.getPhrase());
            System.out.println(po.getFrequency());
        }
        System.out.println("");


    }
}
