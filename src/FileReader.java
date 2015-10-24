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

public class FileReader {
    String theDocument ="";

    public static String readFile(String  location) throws FileNotFoundException
    {
        String theDocument = "";

        java.io.File docFile = new java.io.File(location);
        try
        {
            Scanner input = new Scanner(docFile);
            while (input.hasNext()) {
                theDocument += input.nextLine();
            }
            input.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("FileNotFound");
        } //catch block

        return theDocument;
    }

    /**
     * param file variable
     * This checks to see if a file is in UTF-8 or not
     * @return  True if a file is UTF-8
     * False if it is not
     *
    private boolean checkFileFormat(File file)
    {
        //CHECK TO SEE IF THE FILE IS A UTF FILE
        if(File)
        return false;
    }

    public String getDocument()
    {
        return theDocument;
    }


    /**
     * @param file file variable
     * This checks to see if a file is in UTF-8 or not
     * @return  True if a file is UTF-8
     * False if it is not
     *


} //class
        */