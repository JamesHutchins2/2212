import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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

    //create a string to hold the file
    String file_contents = "";
    
    try{
      //create a buffer reader
      BufferedReader reader = Files.newBufferedReader(filePath);
      //loop to read the file, throw an exception if it fails
      String line = reader.readLine();
       while(line != null){
        file_contents = file_contents + line;
        //append A /n to the end of the line
        file_contents = file_contents + "\n";
        //read the next line
        line = reader.readLine();
      }
    }catch(IOException e){
      return("Error reading file");
    }
    
    //return the file contents in a string. 
    return file_contents;
    

  }

  //function to convert the string to a file (need filename??)
  private static void stringToFile(LinkedList words, String path){
    
    Word_Object current_word = words.getHead();

    String text = "";

    while(current_word.hasNext()){
      //get the word
      String word = current_word.getWord();
      //check capitalization
      if(current_word.isStart_with_capital()){
        //split into char array
        char[] char_array = word.toCharArray();
        //get the first char
        char first_char = char_array[0];
        //convert to string
        String first_char_string = Character.toString(first_char);
        //capitalize the first char
        first_char_string = first_char_string.toUpperCase();
        //replace the first char with the capitalized char
        word = word.replaceFirst(Character.toString(first_char), first_char_string);
        
      }
      //add the word to the text
      text = text + word;
      //if it has a period add one at the end of the word
      if(current_word.isEnd_with_period()){
        text = text + ".";
      }

      //add a space
      text = text + " ";
      
      //get the next word
      current_word = current_word.getNext_node();
    }

    //now we have the text, we need to write it to a file

    //get the path
    Path filePath = Paths.get(path);

    //create a buffered writer
    try{
      BufferedWriter writer = Files.newBufferedWriter(filePath);
      //write the text to the file
      writer.write(text);
      //close the writer
      writer.close();
  }catch(IOException e){
    System.out.println("Error writing to file");
  }
  }
  
}
