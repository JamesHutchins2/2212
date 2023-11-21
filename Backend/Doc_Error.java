        // TO DO
            // **DONE**  replace error arrays with the tags from Word_Object
            // **DONE**  remove checkPeriods()                                       
            // **DONE**  remove checkPeriod() and checkCapital() from LinkedList.java 
            // **DONE**  include ! and ? as end of sentence                        
            // **DONE**  implement mismatched capitals, but include all caps words
         
        // Questions for the group
            // **ASKED** new line and capitalized... how to check for new line?
            // **ASKED** how are we gonna do the testing
            //           should i set the rest to false or no need? **********Question***********
            //           what should i do w corrected vars? **********Question***********

        // Comments for the group:
            // i added isNeeds_lowercase() and setNeeds_lowercase(boolean needs_lowercase) to Word_Object.java
            // test1

import java.lang.reflect.Array;
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
        

        //create an instance of the dictionary
        Dictionary dictionary = new Dictionary("Backend/dict_resources/words.txt");
    }

    // this checks if there are repeated words in the uploaded doc. if there are, it adds it to the double words list
    public void checkDoubleWords(Word_Object wordObject) {
        if(wordObject.getWord().equals(wordObject.getWord())){ // if the word is the same as the next word
            this.current_double_words++; // increment the current double words
            wordObject.setIs_double_word_before(true);
            wordObject.setIs_double_word_after(true);
        } 
    }

    // this checks if there are incorrect capitalizations in the uploaded doc.
    public void checkCapitals(Word_Object wordObject, ArrayList<String> Dictionary) {
        String wordString = wordObject.getWord(); // get the word string
        String nextWordString = wordObject.getNext_node().getWord(); // get the word string of the next word
        
        Word_Object prevWordObject = wordObject.getPrev_node(); // get the previous word
        int strLength = wordString.length(); // get the length of the word

       // CASE ONE: if the word ends with a . or ! or ? or new line
        if(wordString.substring(strLength-1) == "." || wordString.substring(strLength-1) == "?" || wordString.substring(strLength-1) == "!" || wordString.substring(strLength-1) == "\n" ){ 
            if(nextWordString.toUpperCase() != nextWordString){ // if the next word does not start with a capital
                this.current_capital_errors++; // increment the current capital errors
                wordObject.setNeeds_capital(true); // set the word to need a capital
            }
        }
       // CASE TWO: if the word is "I" and is not capitalized
        else if(wordString.equalsIgnoreCase("i") && wordString.toUpperCase() != wordString){ 
            this.current_capital_errors++; // increment the current capital errors
            wordObject.setNeeds_capital(true); // set the word to need a capital
        }
       // CASE THREE: if the word is the first word of the doc and is not capitalized
        else if (prevWordObject == null && wordString.substring(0,1).toUpperCase() != wordString.substring(0,1)){ 
            this.current_capital_errors++; // increment the current capital errors
            wordObject.setNeeds_capital(true); // set the word to need a capital
        }
       // CASE FOUR: if any other letters within the word are miscapitalized
        else if(wordString.substring(1).toLowerCase() != wordString.substring(1)){ 
            this.current_capital_errors++; // increment the current capital errors
            wordObject.setNeeds_lowercase(true); // set the word to need a lowercase
        }
        else{ // should i set the rest to false or no need? **********Question***********
            wordObject.setNeeds_capital(false);
            wordObject.setNeeds_lowercase(false);
        }
    }
}

    // MY OLD VERSION: 
    // under private int current_misspelt_words;
    //      private LinkedList wordsInDoc; // this is the linked list of all the words in the document
    //      private ArrayList<String> misspelledWordsList; // this is the list of all the misspelled words in the document
    //      private ArrayList<String> capitalErrorsList; // this is the list of all the capital errors in the document
    //      private ArrayList<String> doubleWordsList; // this is the list of all the double words in the document
    // under public Doc_Error() {
    //      this.misspelledWordsList = new ArrayList<>();
    //      this.capitalErrorsList = new ArrayList<>();
    //      this.doubleWordsList = new ArrayList<>();
    // NO NEED: this checks if the words in the uploaded doc are spelled correctly as per the dictionary. if not, it adds it to the misspelled words list
    // public void findIncorrectWords(Word_Object word, ArrayList<String> Dictionary) {
    //     for (int i = 0; i < wordsInDoc.length(); i++) {
    //         for (int j = 0; j < Dictionary.size(); j++) {
    //             if (wordsInDoc.get(i).getWord().equals(Dictionary.get(j))) {
    //                 this.current_misspelt_words++; // this can be used to decrement until we get to 0
    //                 misspelledWordsList.add(wordsInDoc.get(i).getWord());     
    //             }
    //         }
    //     }
    // }
    // this checks if there are repeated words in the uploaded doc. if there are, it adds it to the double words list
    // public void checkDoubleWords(Word_Object word_Object, ArrayList<String> Dictionary) {
    //     for(int i = 0; i < wordsInDoc.length(); i++){ // iterate through all the words in the document
    //         if(wordsInDoc.get(i).getWord().equals(wordsInDoc.get(i+1).getWord())){ // if the word is the same as the next word
    //             this.current_double_words++; // increment the current double words
    //             wordsInDoc.get(i).setIs_double_word_before(true);
    //             wordsInDoc.get(i+1).setIs_double_word_after(true);
    //         } 
    //     }
    // }
    // public void checkCapitals(Word_Object word_Object, ArrayList<String> Dictionary) {
    //     for(int i = 0; i < wordsInDoc.length(); i++){ // iterate through all the words in the document
    //         Word_Object wordString = wordsInDoc.get(i); // get the word
    //         Word_Object nextWordString = wordsInDoc.get(i).getNext_node(); // get the next word
    //         Word_Object prevWordString = wordsInDoc.get(i).getPrev_node(); // get the previous word
    //         // if the word ends with a . or ! or ? or new line
    //         if(wordString.getWord().substring(wordString.getWord().length()-1) == "." ||
    //           wordString.getWord().substring(wordString.getWord().length()-1) == "?" ||
    //           wordString.getWord().substring(wordString.getWord().length()-1) == "!" ||
    //           wordString.getWord().substring(wordString.getWord().length()-1) == "\n" ){ 
    //             if(nextWordString.getWord().toUpperCase() != wordString.getWord()){ // if the next word does not start with a capital
    //                 this.current_capital_errors++; // increment the current capital errors
    //                 wordString.setNeeds_capital(true); // set the word to need a capital
    //             }
    //         }
    //         // if the next word is "I" and is not capitalized
    //         else if(wordString.getWord().equalsIgnoreCase("i") && wordString.getWord().toUpperCase() != wordString.getWord()){ 
    //             this.current_capital_errors++; // increment the current capital errors
    //             capitalErrorsList.add(wordString.getWord()); // add the word to the capital errors list  
    //             wordString.setNeeds_capital(true); // set the word to need a capital              }
    //         }
    //         // if the word is the first word of the doc and is not capitalized
    //         else if (prevWordString == null && wordString.getWord().substring(0,1).toUpperCase() != wordString.getWord().substring(0,1)){ 
    //             this.current_capital_errors++; // increment the current capital errors
    //             capitalErrorsList.add(wordString.getWord()); // add the word to the capital errors list
    //             wordString.setNeeds_capital(true); // set the word to need a capital
    //         }
    //         // if any other letters within the word are miscapitalized
    //         else if(wordString.getWord().substring(0, 1).toLowerCase() == wordString.getWord().substring(0, 1)){ 
    //             for (int k = 1; k < wordString.getWord().length(); k++) { // iterate through all the letters in the word
    //                 if(wordString.getWord().substring(k, k+1).toUpperCase() == wordString.getWord().substring(k, k+1)){ 
    //                     this.current_capital_errors++; // increment the current capital errors
    //                     wordString.setNeeds_lowercase(true); // set the word to need a lowercase
    //                 }
    //             }
    //         }
    //     }
    // }