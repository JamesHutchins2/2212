Class Doc_Analysis{
  //this class is to return the analysis portion 
  
  //defining instance variables
  public char[] text;
  private int charCount = 0;
  private int wordCount = 0;
  private int lineCount = 0;

  //method to return char count
  private int getCharCount(char[] text){
    //the size of the array is the total char count
    charCount = text.size();
  }

  //to return word count
  private int getWordCount(char[] word){
    //loop through char array
    while(text.hasnext()){
      //checking if space, period, means new word
      if(text(i).equals(" ") || text(i).equals(".") || text(i).equals("?") || text(i).equals("!")){
        wordCount += 1;
      }
    }
  }


  //to return line count
  private int getLineCount(char[] text){
    //loop through entire file
    while(text.hasnext()){
      //if newline, count
      if(char[i].equals('\n')){
         lineCount += 1;
      }
    }
  }
  
}
