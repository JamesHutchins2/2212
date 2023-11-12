import java.util.*;

public class SpellChecker {

    public static String[] getSuggestions(String misspelledWord, Dictionary dictionary) {
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue)
        );

        for (String word : dictionary.getWords()) { // Assuming getWords() returns a collection of all words in the dictionary
            int distance = levenshteinDistance(misspelledWord, word);
            pq.offer(new AbstractMap.SimpleEntry<>(word, distance));

            if (pq.size() > 3) {
                pq.poll();
            }
        }

        List<String> suggestions = new ArrayList<>();
        while (!pq.isEmpty()) {
            suggestions.add(0, pq.poll().getKey());  // Adding at 0 to reverse the order
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