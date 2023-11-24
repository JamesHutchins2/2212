import java.util.LinkedList;

public class dev {

// function

//get the text array

// j = 0

//get the head of teh linked list (head)
//curr = head
//loop through each word in the text array

    // i = 0
    //start = j
    // end = j + word_length
    // word = text_array[i]
    // list_start, list_end = get_word_indicies(curr)
    // if (start = list_start && end = list_end, and word.equals(curr.word)
        // curr = curr.next
        // j = j + word_length
    // else
        //we will have replace the word in the linked list, and update the index values of the next word based on this one



public void populateLinkedList(String[] text) {
  boolean isFirstWordOfSentence = true;

  //delete the old linked list
  this.wordBuffer = new LinkedList();
  System.out.println("document text:" + text);

  for (String word : text) {
      
      Word_Object curr = new Word_Object(word);
      char lastCharacter = word.charAt(word.length() - 1);

      // Set properties of curr based on word
      curr.setWord(word);
      if (lastCharacter == '.' || lastCharacter == '?' || lastCharacter == '!') {
          curr.setEnd_with_period(true);
          isFirstWordOfSentence = true;
      } else if (isFirstWordOfSentence) {
          curr.setStart_with_capital(true);
          isFirstWordOfSentence = false;
      }

      // Add word object to linked list
      wordBuffer.add(curr);
      
  }
  //ensure that the last item ends in null
  
  Word_Object last_word = (Word_Object) wordBuffer.getTail();
  last_word.setNext_node(null);

  //now we will call the run spell check function
  run_spell_check();
}
}
