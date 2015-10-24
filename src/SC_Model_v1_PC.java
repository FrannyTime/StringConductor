import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
/**
 * Created by PeteCurtis on 10/22/15.
 */
public class SC_Model_v1_PC {
    /**
     * Created by PeteCurtis on 10/22/15.
     */

        public static void main(String[] args) throws FileNotFoundException {
            //listen for click on import
            //once you have a file pass that to the model
            //the model then has a file object

            //make new objects
            File sampleFile = new File("/Users/PeteCurtis/git/StringConductor/src/stuff.txt");
            java.io.FileReader fr = new FileReader(sampleFile);
//            System.out.println(fr);

//            fr.textScanner();
//            DocumentParser pp = new DocumentParser();
//
//            //pass new document from FileReader into phrase Parser
//            pp.documentParser(fr.getDocument());


        }
    }

