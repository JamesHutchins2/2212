package tests;

import Backend.Word_Object;
import Backend.LinkedList;



public class word_object_test {

    public static void main(String[] args) {
        // Create an instance of Word_Object
        Word_Object wordObj = new Word_Object("Example");

        // Test basic getters and setters
        wordObj.setEnd_with_period(true);
        System.out.println("End with period: " + wordObj.isEnd_with_period()); // Expected: true

        // Test checking punctuation
        boolean hasPunctuation = wordObj.check_end_punctuation();
        System.out.println("Ends with punctuation: " + hasPunctuation); // Expected: false (if word is "Example")

        // Test setting and getting capitalization
        wordObj.setNeeds_first_capital(true);
        System.out.println("Needs first capital: " + wordObj.isNeeds_first_capital()); // Expected: true

        // Test replace word functionality
        wordObj.setWord("Replacement");
        System.out.println("Replaced word: " + wordObj.getWord()); // Expected: Replacement

        // Test setting and getting suggestions
        String[] suggestions = {"Suggestion1", "Suggestion2", "Suggestion3"};
        wordObj.setSuggestions(suggestions);
        System.out.println("First suggestion: " + wordObj.getSuggestions()[0]); // Expected: Suggestion1

        
        wordObj.setEnd_with_period(true);
        wordObj.setWord("Example.");
        if(wordObj.check_end_punctuation()){
            System.out.println("test 1 passed");
        } else {
            System.out.println("test 1 failed");
        }

        // Test capitalization settings
        wordObj.setNeeds_capital(true);
        if(wordObj.isNeeds_capital()){
            System.out.println("test 2 passed");
        } else {
            System.out.println("test 2 failed");
        }

        // Test setting and getting the 'is real word' property
        wordObj.setIs_real_word(true);
        if(wordObj.isIs_real_word()){
            System.out.println("test 3 passed");
        } else {
            System.out.println("test 3 failed");
        }

        // Test setting and getting double word properties
        wordObj.setIs_double_word_after(true);
        if(wordObj.isIs_double_word_after()){
            System.out.println("test 4 passed");
        } else {
            System.out.println("test 4 failed");
        }

        wordObj.setIs_double_word_before(true);
        if(wordObj.isIs_double_word_before()){
            System.out.println("test 5 passed");
        } else {
            System.out.println("test 5 failed");
        }

        
        // Test setting and getting start and end indices
        wordObj.setStart_index(5);
        wordObj.setEnd_index(12);
        if(wordObj.getStart_index() == 5){
            System.out.println("test 6 passed");
        } else {
            System.out.println("test 6 failed");
        }
        if(wordObj.getEnd_index() == 12){
            System.out.println("test 7 passed");
        } else {
            System.out.println("test 7 failed");
        }

        // Test the functionality of replace_word and its effect on indices
        wordObj.setWord("ReplacedWord");
        if(wordObj.getWord() == "ReplacedWord"){
            System.out.println("test 8 passed");
        } else {
            System.out.println("test 8 failed");
        }
        
        // Test capitalization array setting and getting
        int[] capitalization = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // Assuming 'ReplacedWord' is the current word
        wordObj.setCapital_at(capitalization);
        if(wordObj.getIs_capital_at()[0] == 1){
            System.out.println("test 9 passed");
        } else {
            System.out.println("test 9 failed");
        
        }

       
    }
}