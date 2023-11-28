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
        "Tommorrow", "will", "be", "an", "an", "intersting", "day", "for", "our", "picnic.", "\n",
        "She", "said", "her", "favoritte", "book", "is", "\"The", "Great", "Gatsby\".", "\n",
        "Could", "you", "pleese", "pass", "the", "ketchup?", "\n",
        "He", "decided", "to", "seperate", "the", "papers", "into", "two", "piles.", "\n",
        "Their", "going", "to", "visit", "the", "museum", "next", "week.", "\n",
        "The", "cat", "chased", "it's", "tail", "around", "the", "living", "room.", "\n",
        "We", "recieved", "an", "invitation", "for", "the", "gaLlery", "opening.", "\n",
        "He", "loves", "to", "play", "the", "guitar", "and", "the", "pianoo."};

        //values to check errors for
        int test_mispelt = 5;
        int test_double = 1;
        int test_capital = 3;
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

    
        //checking capitals: Test 3
            doc.get_doc_error_values();
            //can be within the word incorrectly capitalized (a) - line 9, word 7 of test corpus
            Word_Object word6 = new Word_Object("gaLlery");
            doc_error.checkCapitals(word6);

            if(doc_error.getCurrent_capital_errors() == 1){
                System.out.println("Test 3a: Passed");
            }else{
                System.out.println("Test 3a: Failed");
            }

            //and at the beginning of a sentance not capitalized (b) - line 2, word 1 of test corpus
            

            //and in the middle of a sentence capitalized when it shouldnt be (c) - line 1, word 2 of test corpus



        //test check doubles: Test 4 - line 3, word 4 & 5 of test corpus
        doc.run_spell_check();
        if(doc_error.getCurrent_double_words() == test_double){
            System.out.println("Test 4: Passed");
        }else{
            System.out.println("Test 4: Failed");
        }    


        //test check get and set of current misspelt words: Test 5
        doc.run_spell_check();
        if(doc_error.getCurrent_misspelt_words() == test_mispelt){
            System.out.println("Test 5: Passed");
        }else{
            System.out.println("Test 5: Failed");
        }

        //test check get and set of corrected misspelt words: Test 6

        //get spell check values
        doc.run_spell_check();
        doc.get_doc_error_values();

        //test correcting some of the words - 6a


        //correct all of the words - 6b



        //test check get and set of current double words: Test 7

        //test check get and set of corrected double words: Test 8

        //test check get and set of current capital errors: Test 9

        //test check get and set of corrected capital errors: Test 10

        //test check get and set of current misspelt words: Test 11

        //test check get and set of corrected misspelt words: Test 12

        //test check get and set of current double words: Test 13
    
   


    }   
}
