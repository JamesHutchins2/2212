import java.util.LinkedList;
public class Document{
//this class is to work as the hub for the spell check system creating the linked list from the workspace text.
//It transforms the data types into our word object so that the words can be analyzed by the spell check.

// first creating instance variables
public String[] text;  // variable to hold last checked text instance in document
public LinkedList wordBuffer = new LinkedList();  //linked list to hold our word object list (for the entire document)

public void populateLinkedList(String[] text, LinkedList wordBuffer){
  int first = 1;
  int i = 0;
  while(i < text.length){
    Word_Object curr = new Word_Object();
    int last = text[i].length();
    if((text[last].equals(".")) || (text[last].equals("?")) || (text[last].equals("!"))){
      //means the sentence has ended, add boolean for end of sentence for capital check
      //adding the capital check
      curr.setWord(text[i]); 
      curr.setEnd_with_period(true);
      //adding word object to linked list
      wordBuffer.add(curr);
      
    }else{
      //means no capital case, adding to linked list. checking if first int = 1 --> meaning the first word of the sentence
      if(first == 1){
        //resetting the value
        first = 0;
        //adding to linked list with capital
        curr.setWord(text[i]);
        curr.setStart_with_capital(true);
      }else{
        //just add the word to linked list, no capital check
        curr.setWord(text[i]);
        
      }
    }
  }
  //incrementing the counter
  i += 1;
}



}
