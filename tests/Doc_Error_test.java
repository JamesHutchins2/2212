package tests;
import Backend.Document;
import Backend.Word_Object;
import Backend.Doc_Error;
public class Doc_Error_test {

    public void testDocError(){
        //create a doc error object
        Doc_Error doc_error = new Doc_Error();

        System.out.println("Running Doc_Error_test");
    


        //test corpus
        String [] test_corpus = {"The", "Quick", "brown", "fox", "Jumps", "over", "the", "lazy", "dog.", "\n",
        "it's", "a", "beautifull", "sunny", "day", "outside.", "\n",
        "Tommorrow", "will", "be", "an", "intersting", "day", "for", "our", "picnic.", "\n",
        "She", "said", "her", "favoritte", "book", "is", "\"The", "Great", "Gatsby\".", "\n",
        "Could", "you", "pleese", "pass", "the", "ketchup?", "\n",
        "He", "decided", "to", "seperate", "the", "papers", "into", "two", "piles.", "\n",
        "Their", "going", "to", "visit", "the", "museum", "next", "week.", "\n",
        "The", "cat", "chased", "it's", "tail", "around", "the", "living", "room.", "\n",
        "We", "recieved", "an", "invitation", "for", "the", "gallery", "opening.", "\n",
        "He", "loves", "to", "play", "the", "guitar", "and", "the", "pianoo."};

        //create a document object

        Document doc = new Document(test_corpus);

        //test 1: test check words function

        //create 5 random word objects String word, boolean start_with_capital, boolean end_with_period, boolean is_real_word, boolean needs_capital, boolean needs_period, boolean is_double_word_after, boolean is_double_word_before, String suggestion_1, String suggestion_2, String suggestion_3

        Word_Object word1 = new Word_Object("the");
        Word_Object word2 = new Word_Object("quick");
        Word_Object word3 = new Word_Object("brown");
        Word_Object word4 = new Word_Object("fox");
        Word_Object word5 = new Word_Object("jumpsz");
        //last one is misspelt
        //run the check words function on the 5 word objects

        doc_error.checkWords(word1);
        doc_error.checkWords(word2);
        doc_error.checkWords(word3);
        doc_error.checkWords(word4);
        doc_error.checkWords(word5);

        //now we will check to see if the current misspelt words is 1
        if(doc_error.getCurrent_misspelt_words() == 1){
            System.out.println("Test 1: Passed");
        }
        //now check to see if word5 has the correct suggestions
        String[] w5_suggestions = word5.getSuggestions();

        for(int i = 0; i < w5_suggestions.length; i++){
            //check to see if any are jumps
            if(w5_suggestions[i].equals("jumps")){
                System.out.println("Test 2: Passed");
            }
        }

    






        //test check words

        //test check capitals

        //test check doubles

        //test check get and set of current misspelt words

        //test check get and set of corrected misspelt words

        //test check get and set of current double words

        //test check get and set of corrected double words

        //test check get and set of current capital errors

        //test check get and set of corrected capital errors

        //test check get and set of current misspelt words

        //test check get and set of corrected misspelt words

        //test check get and set of current double words
    
   


    }   
}
