//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.text.BreakIterator;
//import java.util.*;
//
//import javafx.util.Pair;
//import org.mozilla.universalchardet.UniversalDetector;
//
///**
// * Created by Natera on 11/10/15.
// */
//public class TestModel_v2
//{
//    public static ArrayList<ArrayList<Pair<Integer, String>>> wordCountList = new ArrayList<>(50);
//    public static final Integer MAX_PHRASE_LENGTH = 11;
//    public static final Integer MIN_PHRASE_LENGTH = 4;
//    private static final String PHRASE_DELIMITER = " ";
//
//    public static String theDocument = "";
//
//    public static final String FILE_LOCATION = "/Users/PeteCurtis/git/StringConductor/src/noWeirdText";
//
//    public static String readFile(String location) throws FileNotFoundException
//    {
//        java.io.File docFile = new java.io.File(location);
//
//        try
//        {
//            Scanner input = new Scanner(docFile);
//
//            while (input.hasNext())
//            {
//                theDocument += input.nextLine();
//            }
//            input.close();
//        }
//        catch(FileNotFoundException e)
//        {
//            System.out.println("FileNotFound");
//        }
//
//        return theDocument;
//    }
//
//    public static void main (String[] args) throws IOException
//    {
////        SC_Model_v2_franny model = new SC_Model_v2_franny();
////        SC_Model_v2_PC model2 = new SC_Model_v2_PC();
//        DALMerged_v2 model = new DALMerged_v2();
//        SC_Controller_v1_franny franny = new SC_Controller_v1_franny();
//
//        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
//
//        String document = readFile(FILE_LOCATION);
//        iterator.setText(document);
//        int start = iterator.first();
//
//        // starting here is file format detector code from UniversalDetector
//        UniversalDetector detector = new UniversalDetector(null);
//        byte[] buf = new byte[10000];
//        java.io.FileInputStream fis = new java.io.FileInputStream(FILE_LOCATION);
//
//        int nread;
//
//        while ((nread = fis.read(buf)) > 0 && !detector.isDone())
//        {
//            detector.handleData(buf, 0, nread);
//        }
//
//        detector.dataEnd();
//
//        String encoding = detector.getDetectedCharset();
//        if (encoding != null)
//        {
//            System.out.println("Detected encoding = " + encoding);
//        }
//        else
//        {
//            System.out.println("No encoding detected.");
//        }
//
//        detector.reset();
//        // the detector code ends here
//
//        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next())
//        {
//            String sentence = document.substring(start, end);
//            System.out.println(sentence);
//            String[] sentenceArray = sentence.split("\\s+");
//            model.processSentenceArray(sentenceArray, MAX_PHRASE_LENGTH, MIN_PHRASE_LENGTH, PHRASE_DELIMITER);
//        }
//
//        for (Map.Entry<String, Integer> entry : model.frequencyTable.entrySet())
//        {
//            String s = entry.getValue() + "\t" + entry.getKey();
//            System.out.println(s);
//            Integer wordCount = countTheWords(entry.getKey());
//            System.out.println(wordCount);
//        }
////
////        for (ArrayList<Pair<Integer, String>> list : wordCountList)
////        {
////            for (Pair<Integer, String> phrase : list)
////            {
////                System.out.println(phrase.getKey() + "\t" + phrase.getValue());
////            }
////        }
//
//        ArrayList<String> test = new ArrayList<>();
//
//        test = model.getWordCountResults(5);
//
//        for (String item : test)
//        {
//            System.out.println(item);
//        }
//
//        String string = franny.processArrayList(test);
//    }
//
//
//
//    private static Integer countTheWords(String phrase)
//    {
//        Integer wordCount;
//        String[] sentenceArray = phrase.split("\\s+");
//        wordCount = sentenceArray.length;
//
//        return wordCount;
//    }
//
//
//}
