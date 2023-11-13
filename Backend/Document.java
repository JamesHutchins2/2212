public class Document extends Word_Object{
//this class is to work as the hub for the spell check system creating the linked list from the workspace text.
//It transforms the data types into our word object so that the words can be analyzed by the spell check.

// first creating instance variables
String[] text;  // variable to hold last checked text instance in document
Linkedlist wordBuffer = new Linkedlist();  //linked list to hold our word object list (for the entire document)

public void populateLinkedList(String[] text){
  int first = 1;
  while(text.hasnext()){
    int last = text(i).length();
    if(text(last).equals(".") || text(last).equals("?") || text(last).equals("!")){
      //means the sentence has ended, add boolean for end of sentence for capital check
      //adding the capital check
      first = 1;
      //adding word object to linked list
      Word_Object curr = new Word_Object(text(i),     
      
    }else{
      //means no capital case, adding to linked list. checking if first int = 1 --> meaning the first word of the sentence
      if(first == 1){
        //resetting the value
        first = 0;

        //adding to linked list with capital
        
      }else{
        //just add the word to linked list, no capital check
        
      }
    }
  }
  //incrementing the counter
  i += 1;
}



}
