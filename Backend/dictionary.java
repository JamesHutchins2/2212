import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
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
    private static int threshold = 4;

    //constructor
    public Dictionary(String fileLocation) {
        words = new HashSet<>();
        populateTable(fileLocation);
    }

    //populate the set with words from the text file
    private void populateTable(String fileLocation) {
        File file = new File(fileLocation);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line.toLowerCase()); // Assuming case-insensitivity
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    //check to see if a word is in the dictionary
    public boolean isWord(String word) {
        return words.contains(word.toLowerCase());
    }

    public Set<String> lengthFilter(String misspelledWord) {
        Set<String> filteredWords = new HashSet<>();
        for (String word : words) {
            if (Math.abs(word.length() - misspelledWord.length()) <= threshold) {
                filteredWords.add(word);
            }
        }
        return filteredWords;
    }


    public String[] getSuggestions(String misspelledWord) {
        Set<String> filteredWords = lengthFilter(misspelledWord);
    
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()) // Explicit comparator for reversed order
        );
    
        for (String word : filteredWords) {
            int distance = levenshteinDistance(misspelledWord, word);
            pq.offer(new AbstractMap.SimpleEntry<>(word, distance));
    
            if (pq.size() > 3) {
                pq.poll(); // Now removes the word with the largest distance
            }
        }
    
        List<String> suggestions = new ArrayList<>();
        while (!pq.isEmpty()) {
            suggestions.add(pq.poll().getKey()); // No reversal needed
        }
    
        return suggestions.toArray(new String[0]);
    }

    


    private static int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j - 1] + costOfSubstitution(a.charAt(i - 1), b.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1
                    );
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
    
}