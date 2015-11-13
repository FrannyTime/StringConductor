import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.*;

/**
 * Created by PeteCurtis on 11/10/15.
 */
public class TestPhraseObjectTreeComparator {

    public static final Integer MAX_PHRASE_LENGTH = 11;
    public static final Integer MIN_PHRASE_LENGTH = 2;
    private static final String PHRASE_DELIMITER = " ";

    public static String theDocument = "";

    public static final String FILE_LOCATION = "/Users/PeteCurtis/Desktop/stuff.txt";

        public static String readFile(String location) throws FileNotFoundException
        {
            java.io.File docFile = new java.io.File(location);
            try
            {
                Scanner input = new Scanner(docFile);

                while (input.hasNext())
                {
                    theDocument += input.nextLine();
                }
                input.close();
            }
            catch(FileNotFoundException e)
            {
                System.out.println("FileNotFound");
            }

            return theDocument;
        }

        public static void main (String[] args) throws IOException
        {
           // SC_View view = new SC_View();
            SC_Model_v2_PC model = new SC_Model_v2_PC();

            BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);

            String document = readFile(FILE_LOCATION);
            iterator.setText(document);
            int start = iterator.first();

            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
            {
                String sentence = document.substring(start, end);
                String[] sentenceArray = sentence.split("\\s+");
                model.processSentenceArray(sentenceArray, MAX_PHRASE_LENGTH, MIN_PHRASE_LENGTH, PHRASE_DELIMITER);
            }

            Collection<PhraseObject> po = model.phraseTable.values();
//            PhraseObject[] poa = new PhraseObject[2000];
//            poa = po.toArray(new PhraseObject[0]);
            ArrayList<PhraseObject> phraseObjectArrayList = new ArrayList<>();
            phraseObjectArrayList.addAll(po);

            //sort and print by phrase length
//            phraseObjectArrayList.sort(new PhraseComparator());
//            for(PhraseObject poo: phraseObjectArrayList){
//                System.out.print(poo.getNumberOfWords());
//                System.out.print(poo.getPhrase());
//                System.out.println(poo.getFrequency());
//            }
//            System.out.println("");

            //sort and print by frequency
            String s = null;
            phraseObjectArrayList.sort(new PhraseWordCountComparator());

            System.out.print("Frequency"+"\t  " + "NumberOfWords"+"\t" +"Phrase"+"\n");
            for(PhraseObject poo: phraseObjectArrayList){
//                System.out.print(poo.getNumberOfWords());
//                System.out.print(poo.getPhrase());
//                System.out.println(poo.getFrequency());
                s = poo.getFrequency()+"\t \t \t \t" + poo.getNumberOfWords() +"\t \t \t \t \t" + poo.getPhrase() ;
                if(s!=null && poo.getNumberOfWords() ==9)
                {System.out.println(s);}

            }
            System.out.println("");

            //sort and print by word count comparator
//            phraseObjectArrayList.sort(new PhraseWordCountComparator());
//            for(PhraseObject poo: phraseObjectArrayList){
//                System.out.print(poo.getNumberOfWords());
//                System.out.print(poo.getPhrase());
//                System.out.println(poo.getFrequency());
//            }
//            System.out.println("");


//            StringBuilder stringBuilder = new StringBuilder();

//            for (Map.Entry<String, Integer> entry : model.phraseTable.entrySet())
//            {
//                System.out.println(entry);
//                String s = entry.getValue() + "\t" + entry.getKey();
//                stringBuilder.append(s);
//            }
//            view.runApp(args);
        }

//        public String getDataStructure(String inputFileLocation) throws IOException
//        {
//            SC_Model_v2_franny model = new SC_Model_v2_franny();
//            String returnString = new String();
//
//            BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
//
//            String document = readFile(inputFileLocation);
//            iterator.setText(document);
//            int start = iterator.first();
//
//            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
//            {
//                String sentence = document.substring(start, end);
//                String[] sentenceArray = sentence.split("\\s+");
//                model.processSentenceArray(sentenceArray, MAX_PHRASE_LENGTH, MIN_PHRASE_LENGTH, PHRASE_DELIMITER);
//            }
//
//            StringBuilder stringBuilder = new StringBuilder();
//
//            for (Map.Entry<String, Integer> entry : model.phraseTable.entrySet())
//            {
//                System.out.println(entry);
//                String s = entry.getValue() + "\t" + entry.getKey();
//                stringBuilder.append(s);
//            }
//
//            returnString = stringBuilder.toString();
//
//            return returnString;
//
//        }

    }