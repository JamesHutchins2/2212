class Doc_Analysis{
  //this class is to return the analysis portion 
  
  //defining instance variables
  public char[] text;
  private int charCount = 0;
  private int wordCount = 0;
  private int lineCount = 0;

  //method to return char count
  private int getCharCount(char[] text){
    //the size of the array is the total char count
    charCount = text.length;
    return charCount;
  }

  //to return word count
  private int getWordCount(char[] text){
    //loop through char array
    int i = 0;
    while(i < text.length){
      //checking if space, period, means new word

      if(text[i] == ' ' || text[i] == '.' || text[i] == '?' || text[i] == '!'){
        wordCount += 1;
      }
    }
    return wordCount;
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
  
}
