package Backend;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Set;
import java.io.IOException;

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
        //filter out words that are not within a certain length of the misspelled word
        Set<String> filteredWords = lengthFilter(misspelledWord);
        //create a priority queue to hold the words so we know which ones are the most similar to the word in question
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                //compare entries by value
                (entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()) // Explicit comparator for reversed order
        );

        //combine the filtered words with the user words
        try{
            Set<String> user_words_set = new HashSet<>(Arrays.asList(user_words));
            filteredWords.addAll(user_words_set);
        }catch(Exception e){
            //no user words so pass
            
        }
        
        //loop through the filtered words
        for (String word : filteredWords) {
            //get the levenshtein distance between the misspelled word and the word in the dictionary
            int distance = levenshteinDistance(misspelledWord, word);
            //add the word and distance to the priority queue
            pq.offer(new AbstractMap.SimpleEntry<>(word, distance));
            
            // Only keep the top 3 results
            if (pq.size() > 3) {
                pq.poll(); // Now removes the word with the largest distance
            }
        }

        
        //MIGHT NEED TO FIX @!!!!!!!!!!!!!!
        //create a list to hold the suggestions
        List<String> suggestions = new ArrayList<>();
        //loop through the priority queue
        while (!pq.isEmpty()) {
            //add the suggestions to the list
            suggestions.add(pq.poll().getKey()); // No reversal needed
        }
        //return the suggestions
        return suggestions.toArray(new String[0]);
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