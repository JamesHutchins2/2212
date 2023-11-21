package Backend;
class Doc_Analysis{
  //this class is to return the analysis portion 
  
  //defining instance variables
  public char[] text;
  private int charCount = 0;
  private int wordCount = 0;
  private int lineCount = 0;
  //constructor
  public Doc_Analysis(LinkedList wordBuffer){
    //get the text
    String[] text = getText(wordBuffer);
    //get the char
    char[] char_array = getChar(text);
    //get the char count
    //get the line count
    lineCount = getLineCount(char_array);

}


  //to return line count
  private int getLineCount(char[] text){
    //loop through entire file
    int i = 0;
    while( i < text.length){
      //if newline, count
      if(text[i] == '\n'){
         lineCount += 1;
      }
    }
    return lineCount;
  }

  private String[] getText(LinkedList wordBuffer){

    //loop through the linked list and get the text
    //fist initialize the array
    String[] text = new String[wordBuffer.get_length()];

    //get the first word object
    Word_Object curr_word_object = (Word_Object) wordBuffer.getHead();

    //loop through the linked list until we reach the end
    int i = 0;
    while(curr_word_object.hasNext()){
      //get the word
      String word = curr_word_object.getWord();
      //add to the array
      text[i] = word;
      //increment the counter
      i += 1;
    }

    //update the instance variable
    this.wordCount = i;


    return text;
  }

private char[] getChar(String[] text){
  //loop through the array and get the char
  //first initialize the array
  char[] char_array = new char[text.length];

  //loop through the array
  int i = 0;
  while(i < text.length){
    //get the word
    String word = text[i];
    //split the word into char array
    char[] word_char_array = word.toCharArray();
    //loop through the char array
    int j = 0;
    while(j < word_char_array.length){
      //add to the char array
      char_array[j] = word_char_array[j];
      //increment the counter
      j += 1;
    }
    //increment the counter
    i += 1;
  }

  //update the instance variable
  
  this.charCount = i;

  return char_array;
}

  

public void update(LinkedList wordBuffer){
  //get the text
  String[] text = getText(wordBuffer);
  //get the char
  char[] char_array = getChar(text);
  //get the char count
  //get the line count
  lineCount = getLineCount(char_array);
}

  //getters and setters
  public int get_char_count(){
    return charCount;
  }
  public int get_word_count(){
    return wordCount;
  }
  public int get_line_count(){
    return lineCount;
  }

  
}
