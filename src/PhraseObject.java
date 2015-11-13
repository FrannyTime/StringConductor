import java.util.Comparator;
import java.util.List;

/**
 * Created by PeteCurtis on 11/9/15.
 */
public class PhraseObject{
    private String phrase;
    private int frequency;
    private int numberOfWords;

    PhraseObject(String newPhrase, int newNumberOfWords)
    {
        this.numberOfWords = newNumberOfWords;
        this.phrase = newPhrase;
        this.frequency = 1;
    }

    public String getPhrase(){
        return phrase;
    }

    public void incrementFrequency(){
        frequency += 1;
    }

    public int getFrequency(){
        return frequency;
    }

    public int getNumberOfWords(){
        return numberOfWords;
    }
}
