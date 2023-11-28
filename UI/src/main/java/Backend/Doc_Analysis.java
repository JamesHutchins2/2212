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
  private int getLineCount(char[] text) {
    int lineCount = 1; // Initialize lineCount
    int i = 0;
    while (i < text.length) {
        // If newline, count
        if (text[i] == '\n') {
            lineCount += 1;
        }
        i++; // Increment i to avoid infinite loop
    }
    return lineCount;
  }

  private String[] getText(LinkedList wordBuffer) {
    int length = wordBuffer.get_length();
    String[] text = new String[length];

    Word_Object curr_word_object = wordBuffer.getHead();
    int i = 0;
    while (curr_word_object != null) {
        text[i] = curr_word_object.getWord();
        i++;
        curr_word_object = curr_word_object.getNext_node();
    }

    this.wordCount = i;
    return text;
  }

  private char[] getChar(String[] text) {
    // Calculate total length needed
    int totalLength = 0;
    for (String word : text) {
        totalLength += word.length();
    }

    // Initialize the array with the correct size
    char[] char_array = new char[totalLength];

    // Loop through the array and fill char_array
    int currentIndex = 0;
    for (String word : text) {
        for (char c : word.toCharArray()) {
            char_array[currentIndex++] = c;
        }
    }

    // Update the instance variable
    this.charCount = totalLength;

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
