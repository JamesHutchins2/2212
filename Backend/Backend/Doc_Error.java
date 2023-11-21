package Backend;


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
    public Doc_Error() {
        //set vars to zero
        this.current_misspelt_words = 0;
        this.corrected_misspelt_words = 0;
        this.current_double_words = 0;
        this.corrected_double_words = 0;
        this.current_capital_errors = 0;
        this.corrected_capital_errors = 0;

        //create an instance of the dictionary
        this.dictionary = new Dictionary("Backend/dict_resources/words.txt");
    }

    //checks words from the document for misspellings
    public void checkWords(Word_Object word) {
        //First check if the word is an actual word

        //get the word from the word object
        String word_text = word.getWord();
        // check to see if word in dict
        Boolean is_a_word = dictionary.isWord(word_text);
        
        //if it is not a word (False evaluation)
        if(!is_a_word){
            //itterate the current misspelt words
            this.current_misspelt_words++;
            //add a flag to the word object
            word.setIs_real_word(false);

            //now get suggestions for the word
            //create an array list to hold the suggestions
            String [] suggestions = dictionary.getSuggestions(word_text);
            //add the suggestions to the word object
            word.setSuggestions(suggestions);
            
        }
    }

    //check double word function
    public void checkDoubleWord(Word_Object word_Object){

        //get the current word text
        String word_text = word_Object.getWord();

        //get the next word object
        Word_Object next_word_object = word_Object.getNext_node();
        //extract the word string
        String next_word = next_word_object.getWord();


        //check to see if this word and next word are the same word
        if(word_text.equals(next_word)){
            //if they are the same word, then it is a double word
            this.current_double_words++;
            //add a flag to the word object
            word_Object.setIs_double_word_after(true);

        }
    }

    public void checkCapitals(Word_Object word_Object) {

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

    //getters and setters
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
