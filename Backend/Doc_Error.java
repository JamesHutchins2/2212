
import java.util.ArrayList;

public class Doc_Error {
    private int current_misspelt_words;
    private int corrected_misspelt_words;
    private int current_double_words;
    private int corrected_double_words;
    private int current_capital_errors;
    private int corrected_capital_errors;

    public Doc_Error() {
        this.current_misspelt_words = 0;
        this.corrected_misspelt_words = 0;
        this.current_double_words = 0;
        this.corrected_double_words = 0;
        this.current_capital_errors = 0;
        this.corrected_capital_errors = 0;
    }

    public void checkWords(String word_Object, ArrayList<String> Dictionary) {
        if (!Dictionary.contains(word_Object)) {
            this.current_misspelt_words++;
        }
    }

    public void checkCapitals(String word_Object, ArrayList<String> Dictionary) {
        if (word_Object.length() > 1 && !word_Object.equals(word_Object.toUpperCase())) {
            String lowerCaseWord = word_Object.toLowerCase();
            if (Dictionary.contains(lowerCaseWord)) {
                this.current_capital_errors++;
            }
        }
    }
}
