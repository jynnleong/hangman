import java.io.*;
import java.util.*;

/**
 * This is a class that generates random words from the text file that's been included.
 */
public class GetWord
{
    private List<String> words;
    private Random rand;
    public String word, guess, actualword;
    public HashSet<String> usedwords;

  public GetWord() 
    {
    }
    
    public ArrayList<String> getword()
    {
        rand = new Random();
        words =  new ArrayList<>();
        this.actualword = "";
        this.usedwords = new HashSet<>();

        //Reading the file containing the words to guess 
        File file = new File("/Users/jynnleong/Desktop/Coding practice/Hangman java/Words.txt");

        try(BufferedReader b = new BufferedReader(new FileReader(file)))
        {
            while(b.ready())
            {
                words.add(b.readLine());
            }
            b.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        //Randomly retrieve a word from the .txt file
        this.word = words.get(rand.nextInt(words.size()));
        
        // The while loop ensures that the same word won't be used as the word to guess 
        while(usedwords.contains(this.word))
        {
            this.word = words.get(rand.nextInt(words.size()));
        }
        
        //If the word has not yet been used, it will be added into the HashSet to ensure no duplication
        this.usedwords.add(this.word);

        // This for loop is to make sure the length of the word is the same as the blank string displayed on screen
        for(int i = 0; i < this.word.length(); i++)
        {
            this.actualword = this.actualword + Character.toString(this.word.charAt(i)) + " ";
        }

        ArrayList<String> wordlist = new ArrayList<>();

        // The for loop converts the word to be guessed to "_" 
        for(int i = 0; i < this.word.length(); i++)
        {
            wordlist.add("_");
            wordlist.add(" ");
        }

        StringBuilder s = new StringBuilder();

        // The StringBuilder then turns the elements from the "wordlist" array into a string
        for(int i = 0; i < wordlist.size(); i++)
        {
            s.append(wordlist.get(i));
        }

        this.guess = s.toString();

        return wordlist;
    }

    public String getwordtoguess()
    {
        return this.guess;
    }
}
