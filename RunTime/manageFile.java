class manageFile{
  //this is a class that works with the UI to open a file, save a file, etc.

  //instance variables
  public String[] text;
  private String path;

  //method to convert a file to a string
  private static String fileToString(String path){
    //get path
    Path filePath = Paths.get(path);

    //now convert file to string
    String temp = Files.readString(filePath);

    //putting it in the appropriate format
    
  }

  //function to convert the string to a file (need filename??)
  private static void stringToFile(String[] text, String path){
    BufferedWriter out = new BufferedWriter(new FileWriter(new File(path)));
    String temp;
    int i = 0;
    int test = 0;
    //loop for the file to convert to single string
      while(text.hasnext()){
        //temp value to see if it is end of sentence or not
        char t = text(i).charAt(text.length() -1);
        if(t.equals(".") || t.equals("?") || t.equals("!") ){
          test = 1;
        }else{
          //if the last word had a sentence end
          if(test == 1){
            temp = temp + text(i);
            //otherwise, put a space
          }else{
            temp = temp + " " + text(i);
          }
        }
        i += 1;     
    }
    //writing to file
    out.println(temp);
    out.close();
    
  }
  
}
