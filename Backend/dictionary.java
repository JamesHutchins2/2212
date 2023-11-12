import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Dictionary {
    private Hashtable<String, Boolean> dictionary;

    public Dictionary() {
        dictionary = new Hashtable<>();
        populateTable("Backend/dict_resources/words.txt");  // Assume this is the path to your dictionary file
    }

    public boolean isWord(String word) {
        return dictionary.containsKey(word);
    }

    public boolean findWord(String word) {
        return dictionary.containsKey(word);
    }

    public String[] getWords() {
        return dictionary.keySet().toArray(new String[0]);
    }

    private void put(String word) {
        dictionary.put(word, true);
    }

    private void populateTable(String fileLocation) {
        File file = new File(fileLocation);
        BufferedReader bufferedReader = null;
    
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int count = 0; // Added for debugging
            while ((line = bufferedReader.readLine()) != null) {
                put(line);
                count++; // Increment word count
            }
            System.out.println("Total words loaded: " + count); // Display total words loaded
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while closing the file: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}