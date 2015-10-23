import java.io.*;
import java.io.FileReader;

/**
 * Created by Natera on 10/22/15.
 */
public class SC_Model_v1_franny
{
/**
 * Created by PeteCurtis on 10/22/15.
 */


    static String readFile (String fileName)throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        finally
        {
            br.close();
        }
    }

    public static void main (String[] args) throws IOException, FileNotFoundException
    {

        String filePath = "/Users/Natera/Documents/CS/SC_text.txt";
        String myString = readFile(filePath);
        System.out.println(myString);

    }
}



