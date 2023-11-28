package Backend;


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
}
public void populateLinkedList(String text) {
  // Split the text into words, keeping delimiters (spaces and punctuation)
  String[] wordsWithDelimiters = text.split("(?<=\\s)|(?=\\s)|(?<=\\p{Punct})|(?=\\p{Punct})");

  int index = 0;
  Word_Object current = wordBuffer.getHead();
  int spacesBefore = 0;
  boolean wordChanged;

  for (String element : wordsWithDelimiters) {
      boolean isWord = !element.matches("\\s+"); // True if the element is a word, false if it's spaces

      if (isWord) {
          wordChanged = current == null || !current.getWord().equals(element) || current.getSpaces_before() != spacesBefore;

          if (wordChanged) {
              // Create a new Word_Object or update the existing one
              Word_Object newWord = new Word_Object(element);
              newWord.setWord(element);
              newWord.setSpaces_before(spacesBefore);
              newWord.setModified(true); // Mark as modified for spell check

              if (current == null) {
                  wordBuffer.add(newWord);
              } else {
                  wordBuffer.replaceWord(current, newWord);
                  current = newWord.getNext_node();
              }
          } else {
              current.setModified(false); // No change in word and spaces
              current = current.getNext_node(); // Move to next word
          }

          spacesBefore = 0; // Reset spacesBefore as we just processed a word
      } else {
          // Element is spaces or punctuation
          spacesBefore += element.length();
      }

      index += element.length();
  }

  // Remove any remaining words in the old list
  while (current != null) {
      Word_Object next = current.getNext_node();
      wordBuffer.removeWord(current);
      current = next;
  }

  // Recalculate indices and run spell check on modified words
  wordBuffer.calculate_indicies();
  run_spell_check(); // Modify this method to check only modified words
}

public void run_spell_check() {
  Word_Object current = wordBuffer.getHead();
  while (current != null) {
      if (current.isModified()) {
          // Run spell check on this word
          doc_error.checkWords(current);
          current.setModified(false); // Reset the modified flag after checking
      }
      current = current.getNext_node();
  }
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