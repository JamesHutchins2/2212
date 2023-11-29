package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Doc_Error {
    //create the document error variables
    private int current_misspelt_words;
    private int corrected_misspelt_words;
    private int current_double_words;
    private int corrected_double_words;
    private int current_capital_errors;
    private int corrected_capital_errors;
    Dictionary dictionary;

    //initialize the object
    public Doc_Error(){
        //set vars to zero
        this.current_misspelt_words = 0;
        this.corrected_misspelt_words = 0;
        this.current_double_words = 0;
        this.corrected_double_words = 0;
        this.current_capital_errors = 0;
        this.corrected_capital_errors = 0;

        //create an instance of the dictionary
        
        // String path_test = "../UI/src/main/java/Backend/dict_resources/words.txt";
        // String path_run = "../../Backend/dict_resources/words.txt";
        // String path = "C:/Users/james/Desktop/2212_final/splash_fix/2212/UI/src/main/java/Backend/dict_resources/words.txt";
        String relativePath = "2212_final/splash_fix/2212/UI/src/main/java/Backend/dict_resources/words.txt";
        File file = new File(System.getProperty("user.dir") + File.separator + relativePath);

        
        dictionary = new Dictionary(file.getAbsolutePath());

        //put int a try catch block
        
    }
    //checks words from the document for misspellings
    public void checkWords(Word_Object word) {
        //First check if the word is an actual word

        //get the word from the word object
        String word_text = word.getWord();
        // check to see if word in dict
        //take off any punctuation
        if(word_text.endsWith(".") || word_text.endsWith(",") || word_text.endsWith(";") || word_text.endsWith(":") || word_text.endsWith("!") || word_text.endsWith("?") || word_text.endsWith("\"") || word_text.endsWith("\'")){
            word_text = word_text.substring(0, word_text.length() - 1);
        }

        Boolean is_a_word = dictionary.isWord(word_text);
        //set the word object is real word flag
        word.setIs_real_word(is_a_word);
        
        //if it is not a word (False evaluation)
        if(!is_a_word){
            System.out.println("Misspelt word!!!: " + word_text);
            //itterate the current misspelt words
            this.current_misspelt_words++;
            //add a flag to the word object
            word.setIs_real_word(false);

            //now get suggestions for the word
            //create an array list to hold the suggestions
            String [] suggestions = dictionary.getSuggestions(word_text);
            //add the suggestions to the word object
            //print out the suggestions
            for(int i = 0; i < suggestions.length; i++){
                System.out.println(suggestions[i]);
            }
            word.setSuggestions(suggestions);
            
        }
    }

    //check double word function
    public void checkDoubleWord(Word_Object word_Object){

        //get the current word text
        String word_text = word_Object.getWord();

        //get the next word object if it is not null
        if(word_Object.getNext_node() == null){
            //if it is null then we do nothing
            return;
        }
        else{
            Word_Object next_word_object = word_Object.getNext_node();
            //extract the word string
            String next_word = next_word_object.getWord();
            System.out.println("next word: " + next_word);
            System.out.println("current word: " + word_text);

            //check to see if this word and next word are the same word
            if(word_text.equals(next_word)){
                //if they are the same word, then it is a double word
                this.current_double_words++;
                System.out.println("Double word!!!: " + word_text);
                
                //add a flag to the word object
                word_Object.setIs_double_word_after(true);
                word_Object.getNext_node().setIs_double_word_before(true);

            }
        }
    
    }

    public void set_is_first(Word_Object word){
        //check to see if there is a previous word
        if(word.getPrev_node() == null){
            //if there is no previous word, then this is the first word
            word.setIs_first_word(true);
        }
        else{
            //if the previous word ends with punctuation, then this is the first word
            if(word.getPrev_node().getEndsWithPunctuation()){
                word.setIs_first_word(true);
            }
            else{
                //if the previous word does not end with punctuation, then this is not the first word
                word.setIs_first_word(false);
            }
        }
    }

    // This is my new attempt at implemeting this I have added some new params to the word object for doing so and have added populating functions to do so. 
    public void checkCapitals(Word_Object head) {
        Word_Object current = head;
    
        while (current != null) {
            String word = current.getWord();
    
            // Check for the first word or words after punctuation (as marked by is_first_word)
            if (current.isIs_first_word()) {
                if (!Character.isUpperCase(word.charAt(0))) {
                    current.setNeeds_first_capital(true);
                    System.out.println("Needs first capital: " + word);
                }
    
                // Check if any other letters in the word are capitalized
                for (int i = 1; i < word.length(); i++) {
                    if (Character.isUpperCase(word.charAt(i))) {
                        current.setNeeds_lower_but_first(true);

                        break;
                    }
                    System.out.println("Needs Lower But first: " + word);
                }
            } else {
                // Check for capitals anywhere in the word
                for (int i = 0; i < word.length(); i++) {
                    if (Character.isUpperCase(word.charAt(i))) {
                        current.setNeeds_lower(true);
                        break;
                    }
                    System.out.println("Needs Lower: " + word);
                }
            }
    
            // Move to the next node in the list
            current = current.getNext_node();
        }
    }


    

    //add to user dict function
    public void addToUserDict(String word){
        //add the word to the user dict
        dictionary.add_user_word(word);
    }

    //functions to down count the errors as they are corrected
    public void downCountMisspelt(){
        //decrement the current misspelt words
        this.current_misspelt_words--;
        //increment the corrected misspelt words
        this.corrected_misspelt_words++;
    }

    public void downCountDoubleWord(){
        //decrement the current double words
        this.current_double_words--;
        //increment the corrected double words
        this.corrected_double_words++;
    }

    public void downCountCapital(){
        //decrement the current capital errors
        this.current_capital_errors--;
        //increment the corrected capital errors
        this.corrected_capital_errors++;
    }

    //getters and setters for the document class to use to popluate the front end values. 
    public int getCurrent_misspelt_words() {
        return current_misspelt_words;
    }

    public void setCurrent_misspelt_words(int current_misspelt_words) {
        this.current_misspelt_words = current_misspelt_words;
    }

    public int getCorrected_misspelt_words() {
        return corrected_misspelt_words;
    }

    public void setCorrected_misspelt_words(int corrected_misspelt_words) {
        this.corrected_misspelt_words = corrected_misspelt_words;
    }

    public int getCurrent_double_words() {
        return current_double_words;
    }

    public void setCurrent_double_words(int current_double_words) {
        this.current_double_words = current_double_words;
    }

    public int getCorrected_double_words() {
        return corrected_double_words;
    }

    public void setCorrected_double_words(int corrected_double_words) {
        this.corrected_double_words = corrected_double_words;
    }

    public int getCurrent_capital_errors() {
        return current_capital_errors;
    }

    public void setCurrent_capital_errors(int current_capital_errors) {
        this.current_capital_errors = current_capital_errors;
    }

    public int getCorrected_capital_errors() {
        return corrected_capital_errors;
    }

    public void setCorrected_capital_errors(int corrected_capital_errors) {
        this.corrected_capital_errors = corrected_capital_errors;
    }

    


}