package Backend;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Set;
import java.io.IOException;

/**
 * @author      James Hutchins
 * @author      Michelle Bourdon
 * @author      Jessica Kerr
 * @author      Laila El attar
 * @author      Nouran Sakr
 * @version     1.0
 * @since       0.0
 * This class represents the dictionary which words are checked against to see if they are correct
 */
public class Dictionary {
    
    //create a set that will contain words from the text file dict
    private Set<String> words;
    private String[] user_words;

    // cut off for word length to speed up search
    private static int threshold = 4;

    //constructor
    public Dictionary(String fileLocation) {
        words = new HashSet<>();
        populateTable(fileLocation);
    }

    //populate the set with words from the text file
    private void populateTable(String fileLocation) {
        //get file location, and create a file object
        File file = new File(fileLocation);
        //try to read the file
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line.toLowerCase()); // add each word to the set
            }
            System.out.println("WOrd" + words);
        } catch (IOException e) {
            e.printStackTrace(); // print error if file not found
        }
    }

    
    //check to see if a word is in the dictionary
    public boolean isWord(String word) {
        //check to see if the word is in the set put to lowercase jic
        if(words.contains(word.toLowerCase())){
            //if it contains the word, return true
            return true;
        }else{
            //check to see if user words is null
        if(user_words == null){
            //if it is, return false
            return false;
        }
            //check the user words
            for(int i = 0; i < user_words.length; i++){
                //if the word is in the user words, return true
                if(user_words[i].equals(word)){
                    return true;
                }
            }
            //word is not in the dictionary, return false
            return false;
        }
    }

    //filter out words that are not within a certain length of the misspelled word
    public Set<String> lengthFilter(String misspelledWord) {
        //create a new set object to hold words 
        Set<String> filteredWords = new HashSet<>();
        //loop through the words in the dictionary
        for (String word : words) {
            //if the word is within the threshold of the misspelled word, add it to the set
            if (Math.abs(word.length() - misspelledWord.length()) <= threshold) {
                //add the word
                filteredWords.add(word);
            }
        }
        //return the filtered words
        return filteredWords;
    }

    //get suggestions for a misspelled word
    public String[] getSuggestions(String misspelledWord) {
        // Create a set to hold filtered words
       
        // Add all existing words to the set
        Set<String> filteredWords = lengthFilter(misspelledWord);
    
        // Create a priority queue to hold words along with their Levenshtein distance to the misspelled word
        // Words with smaller distances (closer matches) will be given higher priority
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );

    
        // Try to add user-defined words to the filtered set
        try {
            Set<String> user_words_set = new HashSet<>(Arrays.asList(user_words));
            filteredWords.addAll(user_words_set);
        } catch (Exception e) {
            // No user words, so we just pass and use the default words
            System.out.println("No user words");
        }
    
        // Iterate over the filtered words
        for (String word : filteredWords) {
            // Calculate the Levenshtein distance between the misspelled word and the current word
            int distance = levenshteinDistance(misspelledWord, word);
    
            // Add the word along with its distance to the priority queue
            pq.offer(new AbstractMap.SimpleEntry<>(word, distance));
    
            // Keep only the top 3 closest words in the queue
            if (pq.size() > 3) {
                pq.poll(); // Remove the word with the largest distance
            }
        }
    
        // Create a list to store the suggestions
        List<String> suggestions = new ArrayList<>();
        
        // Retrieve the top 3 suggestions from the priority queue
        while (!pq.isEmpty()) {
            suggestions.add(0, pq.poll().getKey()); // Add at the beginning to reverse the order
        }
    
        // Print the suggestions for debugging
        for (String suggestion : suggestions) {
            System.out.println(suggestion);
        }
        //print the length of the suggestions
        System.out.println("suggestions length: " + suggestions.size());
    
        // Convert the list of suggestions to an array and return
        return suggestions.toArray(new String[suggestions.size()]);
    }

    

    //algorithm to find the levenshtein distance between two words (used for creating suggestions)
    //takes 2 strings as parameters
    private static int levenshteinDistance(String a, String b) {
        //create a 2d array to hold the distances of the words
        //each index represents the distance between the first i char of string a, and the first j char of string b
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        //loop through the length of word 1
        for (int i = 0; i <= a.length(); i++) {
            //loop through the length of word 2
            for (int j = 0; j <= b.length(); j++) {
                //if the index is 0, set the distance to the length of the other word
                if (i == 0) {
                    dp[i][j] = j;

                } else if (j == 0) {//if the index is 0, set the distance to the length of the other word
                    dp[i][j] = i;
                } else {
                    // in all other cases select the minimum of the following
                    dp[i][j] = min(
                            //previous index + the cost of substitution of the characters at the last index
                            dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)),
                            //previous index + 1 (word 1)
                            dp[i - 1][j] + 1,
                            //previous index + 1 (word 2)
                            dp[i][j - 1] + 1
                    );
                }
            }
        }
        //return the distance between the two words
        return dp[a.length()][b.length()];
    }
    //returns 0 if they are not equal, 1 if they are
    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    //returns the minimum of the numbers passed in
    private static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }


    public void add_user_word(String word) {
        // Initialize user_words if it's null
        if (user_words == null) {
            user_words = new String[0];
        }
    
        // Add the word to the user words array
        user_words = Arrays.copyOf(user_words, user_words.length + 1);
        user_words[user_words.length - 1] = word;
    }
    
    
}
