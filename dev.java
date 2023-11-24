import java.util.LinkedList;

public class dev {

// function

//get the text array

// j = 0

//get the head of teh linked list (head)
//curr = head
//loop through char of the string
    //collect the word using the space as a delimiter
    //c = ""
    // while c != " " 
        //c += text[j]
        //j++
    //now we have the word
    //check to see if the word is the same as the word in the linked list
    //if curr.word == text[j] && word.key == key
        //j++
        //curr = curr.next
    //else
        //we need to replace this word, and update the linked list
        //replace_word(curr, text[j])
        // update the key value of the word_obejct
        // word.key = previous.key + word.length
    // the next item will be a space object 
    //check that it is a space object
    //if text[j] == " "
        //j = j + 1
        //curr = curr.next
    //else
    

// we take in an array of words
// we will give each of these a indec, 1 , 2, 3, 4
// we will give each a end key (previous word key + word length)
// we will also keep spaces as a word, and give them a key of previous word key + 1, with the flag is space
// we will also keep new lines as a word, and give them a key of previous word key + 1, with the flag is new line

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
