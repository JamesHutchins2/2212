
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

    public void checkWords(Word_Object word, ArrayList<String> Dictionary) {
        //First check if the word is an actual word

        //get the word from the word object
        String word_text = word.getWord();
        // check to see if word in dict
        Boolean is_a_word = Dictionary.contains(word_text);
        
        //if it is not a word (False evaluation)
        if(!is_a_word){
            //itterate the current misspelt words
            this.current_misspelt_words++;
            //add a flag to the word object
            word.setIs_real_word(false);
        }
    }

    //adding in a new function here
    public void checkDoubleWord(Word_Object word_Object){

        //get the current word text
        String word_text = word_Object.getWord();

        //get the next word object

        Word_Object next_word_object = word_Object.getNext_node();

        String next_word = next_word_object.getWord();


        //check to see if this word and next word are the same word
        if(word_text.equals(next_word)){
            //if they are the same word, then it is a double word
            this.current_double_words++;
            //add a flag to the word object
            word_Object.setIs_double_word_after(true);

        }
    }

    public void checkCapitals(Word_Object word_Object, ArrayList<String> Dictionary) {

        //let's go through the cases when it should be a capital
        //1. if it is just the letter I
        //2. first word in the sentence
        //3. if it is the first word in the document
        //4. if it is the first word on a new line


        //lets implement case 1

        //get the word text
        String word_text = word_Object.getWord();

        //check to see if the word is just the letter I in capital form
        if(word_text.equals("I") && word_text.toUpperCase().equals(word_text)){
            
            // we do nothing here because it is correct
        }else{
            //add flag to the word object needs upper case
            word_Object.setNeeds_capital(true);
            //add to the current capital errors
            this.current_capital_errors++;
        }

        //check to see if the word is the first word in the sentence
        //get the previous word object
        Word_Object prev_word_object = word_Object.getPrev_node();

        //get the previous word text
        String prev_word_text = prev_word_object.getWord();

        //check to see if the previous word text ends with a period
        if(prev_word_text.endsWith(".")){
            //split the current word into char array
            char[] word_text_char_array = word_text.toCharArray();
            
            //check to see if first char is upper case
            if(Character.isUpperCase(word_text_char_array[0])){
                //we do nothing here because it is correct
            }else{
                //add flag to the word object needs upper case
                word_Object.setNeeds_capital(true);
                //add to the current capital errors
                this.current_capital_errors++;
            }
            
        }

        //case 3 first word in document
        //check to see if the previous word object is null

        if(word_Object.getPrev_node() == null){
            //split the current word into char array
            char[] word_text_char_array = word_text.toCharArray();
            
            //check to see if first char is upper case
            if(Character.isUpperCase(word_text_char_array[0])){
                //we do nothing here because it is correct
            }else{
                //add flag to the word object needs upper case
                word_Object.setNeeds_capital(true);
                //add to the current capital errors
                this.current_capital_errors++;
            }
        }


        //case 4 first word on a new line
        

        




    }
}
