import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
    private Hashtable<String, Boolean> dictionary;

    public Dictionary() {
        dictionary = new Hashtable<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dict_resources/words.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.put(line, true);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isWord(String word) {
        return dictionary.containsKey(word);
    }

    public find_word(String word) {
        String word = dictionary.get(word);

        if(word == null) {
            return false;
        } else {
            return true;
        }
    }
}