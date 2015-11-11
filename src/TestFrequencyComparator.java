import java.util.ArrayList;

/**
 * Created by PeteCurtis on 11/10/15.
 */
public class TestFrequencyComparator {

    public static void main(String[] args) {

        //comparator testing
        PhraseObject po1 = new PhraseObject("Code is Fun", 3);
        po1.incrementFrequency();
        PhraseObject po2 = new PhraseObject("Code is Fun but hard", 5);
        PhraseObject po3 = new PhraseObject("Code is sometimes dumb", 4);

        ArrayList<PhraseObject> poa = new ArrayList<PhraseObject>();
//        for (int i=0;i<2;i++)
//        {
            poa.add(po1);
            poa.add(po2);
            poa.add(po3);
//        }
//        poa.add(new PhraseObject("Code is Fun but hard", 5));

        poa.sort(new PhraseComparator());
        for(PhraseObject po: poa){
            System.out.print(po.getNumberOfWords());
            System.out.print(po.getPhrase());
            System.out.println(po.getFrequency());
        }
        System.out.println("");

        poa.sort(new PhraseFrequencyComparator());
        for(PhraseObject po: poa){
            System.out.print(po.getNumberOfWords());
            System.out.print(po.getPhrase());
            System.out.println(po.getFrequency());
        }
        System.out.println("");

        poa.sort(new PhraseWordCountComparator());
        for(PhraseObject po: poa){
            System.out.print(po.getNumberOfWords());
            System.out.print(po.getPhrase());
            System.out.println(po.getFrequency());
        }
        System.out.println("");


    }
}
