import java.io.*;
import java.util.*;

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

        this.word = words.get(rand.nextInt(words.size()));
        
        while(usedwords.contains(this.word))
        {
            this.word = words.get(rand.nextInt(words.size()));
        }
        
        this.usedwords.add(this.word);

        for(int i = 0; i < this.word.length(); i++)
        {
            this.actualword = this.actualword + Character.toString(this.word.charAt(i)) + " ";
        }

        ArrayList<String> wordlist = new ArrayList<>();

        for(int i = 0; i < this.word.length(); i++)
        {
            wordlist.add("_");
            wordlist.add(" ");
        }

        StringBuilder s = new StringBuilder();

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