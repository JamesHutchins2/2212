package Backend;


public class Document{
//this class is to work as the hub for the spell check system creating the linked list from the workspace text.
//It transforms the data types into our word object so that the words can be analyzed by the spell check.

// first creating instance variables
public String[] text;  // variable to hold last checked text instance in document
public LinkedList wordBuffer = new LinkedList();  //linked list to hold our word object list (for the entire document)
//create an instance of Doc error
Doc_Error doc_error = new Doc_Error();
Doc_Analysis doc_analysis;

//constructor
public Document(String[] text){
  this.text = text;
  populateLinkedList(text);
  //doc_analysis = new Doc_Analysis(wordBuffer);
  System.out.println("document created");
}
public void populateLinkedList(String[] text) {
  boolean isFirstWordOfSentence = true;

  for (String word : text) {
      System.out.println("looping:" + word);
      Word_Object curr = new Word_Object(word);
      char lastCharacter = word.charAt(word.length() - 1);

      // Set properties of curr based on word
      curr.setWord(word);
      if (lastCharacter == '.' || lastCharacter == '?' || lastCharacter == '!') {
          curr.setEnd_with_period(true);
          isFirstWordOfSentence = true;
      } else if (isFirstWordOfSentence) {
          curr.setStart_with_capital(true);
          isFirstWordOfSentence = false;
      }

      // Add word object to linked list
      wordBuffer.add(curr);
      
  }
  //ensure that the last item ends in null
  System.out.println("last word: " + wordBuffer.getTail().getWord());
  Word_Object last_word = (Word_Object) wordBuffer.getTail();
  last_word.setNext_node(null);
}

public LinkedList run_spell_check(){


  //loop through the linked list and check for errors

  //get the first word object
  Word_Object curr_word_object = (Word_Object) wordBuffer.getHead();

  //loop through the linked list until we reach the end
  while(curr_word_object.hasNext()){

    //call the checkWords function
    doc_error.checkWords(curr_word_object);

    //call the checkDoubleWord function
    //doc_error.checkDoubleWord(curr_word_object);

    //check to see if the word needs a capital
    //doc_error.checkCapitals(curr_word_object);
   
      
      
    

    curr_word_object = curr_word_object.getNext_node();
    }
  

  return wordBuffer;


}

public Word_Object get_word_in_linked_list(int index){
  System.out.println("called " + index);
  //call calculate indicies (linked list was just updated by calling function)
  wordBuffer.calculate_indicies();
  System.out.println("index calced " + index);

  //get the word object at the index
  Word_Object word = (Word_Object) wordBuffer.get_word_at_index(index);

  //return the word object
  return word;
}


public void update_doc_analysis(){
  //update the document analysis
  doc_analysis.update(wordBuffer);
}

public int[] get_doc_analysis(){
  

  //get the char count
  int char_count = doc_analysis.get_char_count();

  //get the word count
  int word_count = doc_analysis.get_word_count();
  
  //get the line count
  int line_count = doc_analysis.get_line_count();

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
}
