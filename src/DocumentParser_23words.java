/**
 * Created by PeteCurtis on 10/22/15.
 */
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

/**
 * Created by PeteCurtis on 10/15/15.
 *This takes the entire document as one string delimited by periods and parses it into the hash table.
 */
public class DocumentParser_23words
{

    //attributes
    Hashtable<String, Integer> phraseTable = new Hashtable();

    public void documentParser(String document)
    {
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
        iterator.setText(document);
        int start = iterator.first();

        for (int end = iterator.next();
             end != BreakIterator.DONE;
             start = end, end = iterator.next())
        {
            ArrayList<String> sentenceList = new ArrayList();
            String sentence = document.substring(start, end);
            // break the sentence into words delimited by a space
            String[] sentenceArray = sentence.split("\\s+");
            int i = 0;

            while (i < sentenceArray.length - 1)
            {
                try
                {
                    if (sentenceArray[i+1] != null)
                    {
                        String hashEntry = sentenceArray[i] + " " + sentenceArray[i+1];
                        phraseTable.put(hashEntry, 5);
                    }
                    else
                    {
                        continue;
                    }
                }
                catch (ArrayIndexOutOfBoundsException aiobe)
                {
                    System.out.println(aiobe);
                }
                i++;
            }

            i = 0;

            while (i < sentenceArray.length - 1)
            {
                try
                {
                    if (sentenceArray[i+2] != null)
                    {
                        String hashEntry = sentenceArray[i] + " " + sentenceArray[i+1] + " " + sentenceArray[i+2];
                        phraseTable.put(hashEntry, 6);
                    }
                    else
                    {
                        continue;
                    }
                }
                catch (ArrayIndexOutOfBoundsException aiobe)
                {
                    System.out.println(aiobe);
                }
                i++;
            }


//            for (String word : splitArray)
//            {
//                System.out.println(word);
//            }


            //  phraseTable.put(sentence, 5);
            //pass each sentance to sentanceParserMethod
            // sentenceParser(sentance);

            //System.out.println(document.substring(start, end));
        }

        for (Map.Entry<String, Integer> entry : phraseTable.entrySet())
        {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
        //for loop
    } //method

    private void sentenceParser(String sentence)
    {
        //set delimiter to whitespace
        //loop through each phrase length
        //loop through the sentence

        //store the phrase in the HashTable(with frequency)

    }

}//class

