/**
 * Created by PeteCurtis on 10/22/15.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.text.BreakIterator;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by PeteCurtis on 10/15/15.
 *
 * The purpose of this class is to import a text document phrase by phrase into the String Conductors memory.
 *
 *
 */
public class FileReader {
    String theDocument ="";

    /**
     * @param file file variable
     * This checks to see if a file is in UTF-8 or not
     * @return  True if a file is UTF-8
     * False if it is not
     * */
    public String getDocument()
    {
        return theDocument;
    }

    private boolean checkFileFormat(File file)
    {
        return true;
    }

    //Method to import text with scanner
    //Change arg to "File docFile" after Daniel writes interface to locate docFile
    public void textScanner() throws FileNotFoundException
    {
        String path = "/Users/PeteCurtis/git/StringConductor/src/com/company/stuff.txt";
        java.io.File docFile = new java.io.File(path);
        try {
            Scanner input = new Scanner(docFile);
            while (input.hasNext()) {
                theDocument += input.nextLine();
            }
            input.close();
            System.out.println(theDocument);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("FileNotFound");
        } //catch block
    }

} //class
