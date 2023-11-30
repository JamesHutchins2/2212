package Backend;

import java.util.HashMap;
import java.util.Map;

public class Document{
//this class is to work as the hub for the spell check system creating the linked list from the workspace text.
//It transforms the data types into our word object so that the words can be analyzed by the spell check.

// first creating instance variables
public String text;  // variable to hold last checked text instance in document
public LinkedList wordBuffer = new LinkedList();  //linked list to hold our word object list (for the entire document)
//create an instance of Doc error
Doc_Error doc_error = new Doc_Error();
Doc_Analysis doc_analysis;

//constructor
public Document(String text){
  this.text = text;
  populateLinkedList(text);
  //doc_analysis = new Doc_Analysis(wordBuffer);
  System.out.println("document created");
  //create doc analysis object
  this.doc_analysis = new Doc_Analysis(wordBuffer);
  this.doc_error = new Doc_Error();
}
public void populateLinkedList(String text) {
    
    //check to see if the string is empty
    if(text.equals("")){
      //do nothing
      return;
    }
    String[] wordsWithDelimiters = text.split("\\s+");
    //check to see if the linked list is empty
    int i = 0;
    
    

    Word_Object current = wordBuffer.getHead();
    
    while (current != null) {
      //check that i is in range of the wordsWithDelimiters
      if(i >= wordsWithDelimiters.length - 1){
        //remove the word
        wordBuffer.removeWord(current);
        //move to the next word
        current = current.getNext_node();
      }else if(current.getWord().equals(wordsWithDelimiters[i])){
        //do nothing
        i++;
      }else{
        String new_word = wordsWithDelimiters[i];
        //take off the punctuation if any
        if(new_word.endsWith(".") || new_word.endsWith(",") || new_word.endsWith(";") || new_word.endsWith(":") || new_word.endsWith("!") || new_word.endsWith("?") || new_word.endsWith("\"") || new_word.endsWith("\'")){
          new_word = new_word.substring(0, new_word.length() - 1);
        }
        //create a new word object
        Word_Object new_word_obj = new Word_Object(wordsWithDelimiters[i]);
        //replace the word object in the linked list
        wordBuffer.replaceWord(current, new_word_obj);
        //increment i
        i++;
        //call the capital function
        mark_capitals(new_word_obj);
      }
      if(current.hasNext()){
        current = current.getNext_node();
      }else{
        break;
      }
    }
    //now we need to add the rest of the words to the linked list
    while(i < wordsWithDelimiters.length){
      //create a new word object
      Word_Object new_word = new Word_Object(wordsWithDelimiters[i]);
      //add the word object to the linked list
      wordBuffer.add(new_word);
      //increment i
      i++;
      //call the capital function
      mark_capitals(new_word);
    }
  }
public String get_user_dict_formatted(){
  //call get_user_dict from doc_error
  String[] user_dict = doc_error.get_user_dict();

  //parse out the string array into a list of words delimited by commas
  String formatted_user_dict = "";

  //loop through the array
  for(int i = 0; i < user_dict.length; i++){
    //add the word to the string
    formatted_user_dict += user_dict[i];
    //add a comma
    formatted_user_dict += ",";
  }

  //return the formatted user dict
  return formatted_user_dict;

}
public void mark_capitals(Word_Object word){
  
  //set the word_objects capital array
  String this_word = word.getWord();

  //get the length of the word
  int length = this_word.length();

  //create an array to hold the capitals
  int[] capitals = new int[length];

  //loop through the word
  for(int i = 0; i < length; i++){
    //check to see if the char is a capital
    if(Character.isUpperCase(this_word.charAt(i))){
      capitals[i] = 1;
    }else{
      capitals[i] = 0;
    }
  }

  //set the capital array
  word.setCapital_at(capitals);

  //FROM THE CAPITAL ARRAY SE THE MARKERS
  word.set_capital_markets();


}




public void run_spell_check() {
  Word_Object current = wordBuffer.getHead();
  while (current != null) {
      if (current.isModified()) {
          // Run spell check on this word
          doc_error.checkWords(current);
          doc_error.checkDoubleWord(current);
          doc_error.checkCapitals(current);
          current.setModified(false); // Reset the modified flag after checking
      }
      current = current.getNext_node();
  }

  //let us update the doc analysis
  update_doc_analysis();
}

public Word_Object check_single_word(Word_Object word){
  //create a word object
  

  //check the word
  doc_error.checkWords(word);

  //return the word object
  return word;
}

public Word_Object get_word_in_linked_list(int index){
  
  //call calculate indicies (linked list was just updated by calling function)
  wordBuffer.calculate_indicies();
  

  //get the word object at the index
  Word_Object word = (Word_Object) wordBuffer.get_word_at_index(index);

  //return the word object
  return word;
}


public void update_doc_analysis(){
  System.out.println("updating doc analysis");
  //check to see if doc_analysis is null
  if(doc_analysis == null){
    //create a new doc analysis object and assign it to instance variable
    doc_analysis = new Doc_Analysis(wordBuffer);
  }
  //update the document analysis
  doc_analysis.update(wordBuffer);
  //get the values from doc analysis and print them
  int char_count = doc_analysis.get_char_count();
  int word_count = doc_analysis.get_word_count();
  int line_count = doc_analysis.get_line_count();

  System.out.println("char count: " + char_count);
  System.out.println("word count: " + word_count);
  System.out.println("line count: " + line_count);
}

public int[] get_doc_analysis(int num_lines){
  

  //get the char count
  int char_count = doc_analysis.get_char_count();

  //get the word count
  int word_count = doc_analysis.get_word_count();
  
  //get the line count
  int line_count = num_lines;

  //add them to an array
  int[] analysis = {char_count, word_count, line_count};

  return analysis;



}

public int[] get_doc_error_values(){
  //get the misspelt words
  int misspelt_words = doc_error.getCurrent_misspelt_words();


  int Corrected_misspelt_words = doc_error.getCorrected_misspelt_words();

  int Current_double_words = doc_error.getCurrent_double_words();

  int Corrected_double_words = doc_error.getCorrected_double_words();

  int Current_capital_errors = doc_error.getCurrent_capital_errors();

  int Corrected_capital_errors = doc_error.getCorrected_capital_errors();

  //add them to an array
  int[] errors = {misspelt_words, Corrected_misspelt_words, Current_double_words, Corrected_double_words, Current_capital_errors, Corrected_capital_errors};

  

  //return the array
  return errors;

  }


  public Doc_Error get_doc_error(){
    return doc_error;
  }

  public void add_to_user_dict(String word){
    //call the add to user dict function in doc error
    doc_error.addToUserDict(word);
  }

  

  public void decrease_current_misspelt_words(){
    //call the down count misspelt function in doc error
    doc_error.downCountMisspelt();
  }

  public void decrease_current_double_words(){
    //call the down count double word function in doc error
    doc_error.downCountDoubleWord();
  }

  public void decrease_current_capital_errors(){
    //call the down count capital function in doc error
    doc_error.downCountCapital();
  
  }

  
}