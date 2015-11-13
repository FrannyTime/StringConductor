import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.*;

/**
 * Created by PeteCurtis on 11/10/15.
 * This tester was used to make sure that the Phrase Object Comparators work properly
 */
public class DALTestPhraseObjectTreeComparator {

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

            //get the file at this location & process in the the SC_Model_v2 object
            String document = readFile(FILE_LOCATION);
            iterator.setText(document);
            int start = iterator.first();

            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
            {
                String sentence = document.substring(start, end);
                String[] sentenceArray = sentence.split("\\s+");
                model.processSentenceArray(sentenceArray, MAX_PHRASE_LENGTH, MIN_PHRASE_LENGTH, PHRASE_DELIMITER);
            }


/***/
            //test a length 4 phrase
            ArrayList<String> bob = new ArrayList<>();
            bob = SC_Model_v2_PC.getWordCountResults(4);

            for(String s: bob)
            {
                System.out.print(s);
            }
/**

            //return all PhraseObjects from TreeMap & store them in an ArrayList<DALPHraseObjects>
            Collection<DALPhraseObject> po = model.phraseTable.values();
            ArrayList<DALPhraseObject> DALPhraseObjectArrayList = new ArrayList<>();
            DALPhraseObjectArrayList.addAll(po);
 */

/**
            //sort and print DALPhraseObjects by phrase length
            DALPhraseObjectArrayList.sort(new DALPhraseComparator());
            for(DALPhraseObject poo: DALPhraseObjectArrayList){
                System.out.print(poo.getNumberOfWords());
                System.out.print(poo.getPhrase());
                System.out.println(poo.getFrequency());
            }
            //print blank line
            System.out.println("");
 */
 /**
            //sort and print by frequency
            String s = null;
            DALPhraseObjectArrayList.sort(new DALPhraseWordCountComparator());
  */
 /**
            System.out.print("Frequency"+"\t  " + "NumberOfWords"+"\t" +"Phrase"+"\n");
            for(DALPhraseObject poo: DALPhraseObjectArrayList){
//                System.out.print(poo.getNumberOfWords());
//                System.out.print(poo.getPhrase());
//                System.out.println(poo.getFrequency());
                s = poo.getFrequency()+"\t \t \t \t" + poo.getNumberOfWords() +"\t \t \t \t \t" + poo.getPhrase() ;
                if(s!=null && poo.getNumberOfWords() ==9)
                {System.out.println(s);}

            }
            //print blank line
            System.out.println("");
  */
 /**
            //sort and print by word count comparator
            DALPhraseObjectArrayList.sort(new DALPhraseWordCountComparator());
            for(DALPhraseObject poo: DALPhraseObjectArrayList){
                System.out.print(poo.getNumberOfWords());
                System.out.print(poo.getPhrase());
                System.out.println(poo.getFrequency());
            }
            //print blank line
            System.out.println("");
  */
 /**
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
  */
        }

    }

