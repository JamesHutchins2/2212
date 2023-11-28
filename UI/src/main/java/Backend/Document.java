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
  //create doc analysis object
  this.doc_analysis = new Doc_Analysis(wordBuffer);
}
public void populateLinkedList(String text) {
  // Split the text into words, keeping delimiters (spaces and punctuation)
  String[] wordsWithDelimiters = text.split("(?<=\\s)|(?=\\s)|(?<=\\p{Punct})|(?=\\p{Punct})");

  Word_Object current = wordBuffer.getHead();
  int spacesBefore = 0;

  for (String element : wordsWithDelimiters) {
      boolean isWord = !element.matches("\\s+"); // True if the element is a word, false if it's spaces

      if (isWord) {
          boolean endsWithPeriod = element.endsWith(".");
          boolean endsWithComma = element.endsWith(",");
          boolean endsWithQuestionMark = element.endsWith("?");
          boolean endsWithExclamationPoint = element.endsWith("!");
          
            

          if (endsWithPeriod || endsWithComma || endsWithQuestionMark || endsWithExclamationPoint) {
              // Remove the period from the word
              element = element.substring(0, element.length() - 1);
          }

          boolean wordChanged = current == null || 
                                !current.getWord().equals(element) || 
                                current.getSpaces_before() != spacesBefore || 
                                current.isEnd_with_period() != endsWithPeriod;

          if (wordChanged) {
              // Create a new Word_Object or update the existing one
              Word_Object newWord = new Word_Object(element);
              newWord.setEnd_with_period(endsWithPeriod);
              newWord.setSpaces_before(spacesBefore);
              newWord.setModified(true); 
              newWord.setEndsWithPunctuation(endsWithPeriod || endsWithQuestionMark || endsWithExclamationPoint);

              if (current == null) {
                  wordBuffer.add(newWord);
              } else {
                  wordBuffer.replaceWord(current, newWord);
                  current = newWord.getNext_node();
              }
          } else {
              current.setModified(false); // No change in word, spaces, and period status
              current = current.getNext_node(); // Move to next word
          }

          spacesBefore = 0; // Reset spacesBefore as we just processed a word
      } else {
          // Element is spaces or punctuation
          spacesBefore += element.length();
      }
  }
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

  
}