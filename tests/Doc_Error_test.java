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

        StringBuilder stringBuilder = new StringBuilder();
        for (String word : test_corpus) {
        stringBuilder.append(word);
        stringBuilder.append(" ");
        }

        String result = stringBuilder.toString().trim();

        //values to check errors for
        int test_mispelt = 5;
        int test_double = 1;
        int test_capital = 3;

        //create a document object
        Document doc = new Document(result);

        //running spell check
        doc.run_spell_check();

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
            System.out.println("Test 1 - Error count: Passed");
        }
        //now check to see if word5 has the correct suggestions
        String[] w5_suggestions = word5.getSuggestions();

        for(int i = 0; i < w5_suggestions.length; i++){
            //check to see if any are jumps
            if(w5_suggestions[i].equals("jumps")){
                System.out.println("Test 2 - Suggestion: Passed");
            }
        }

    
        //checking capitals: Test 3
            //can be within the word incorrectly capitalized (a) - line 9, word 7 of test corpus
            Word_Object word6 = new Word_Object("gaLlery");
            doc_error.checkCapitals(word6);

            if(doc_error.getCurrent_capital_errors() == 1){
                System.out.println("Test 3a: Passed");
            }else{
                System.out.println("Test 3a: Failed");
            }

            //and at the beginning of a sentance not capitalized (b) - line 2, word 1 of test corpus
            Document test3b = new Document("it's", "a", "beautifull", "sunny", "day", "outside.", "\n");
            test3b.run_spell_check();
            int[] temp3b = test3b.get_doc_error_values();
            if(temp3b[4] == 1){                 //need to double check this val
                System.out.println("Test 3b: Passed");
            }else{
                System.out.println("Test 3b: Failed");
            }


            //and in the middle of a sentence capitalized when it shouldnt be (c) - line 1, word 2 of test corpus
            Document test3c = new Document("The", "Quick", "brown", "fox", "Jumps", "over", "the", "lazy", "dog.", "\n");
            test3c.run_spell_check();
            int[] temp3c = test3c.get_doc_error_values();
            if(temp3c[4] == 2){                 //need to double check this val
                System.out.println("Test 3c: Passed");
            }else{
                System.out.println("Test 3c: Failed");
            }

            //now for all capitals
            int[] temp = doc.get_doc_error_values();
            if(temp[4] == 3){                 //need to double check this val
                System.out.println("Test 3d: Passed");
            }else{
                System.out.println("Test 3d: Failed");
            }

        //test check doubles: Test 4 - line 3, word 4 & 5 of test corpus
        int[] temp4 = doc.get_doc_error_values();
        if(temp4[2] == test_double){
            System.out.println("Test 4: Passed");
        }else{
            System.out.println("Test 4: Failed");
        }    


        //test check get and set of current misspelt words: Test 5
        int[] temp5 = doc.get_doc_error_values();
        if(temp5[0] == test_mispelt){
            System.out.println("Test 5: Passed");
        }else{
            System.out.println("Test 5: Failed");
        }

        //test check get and set of corrected misspelt words: Test 6
        //assume the user corrects the word jumpsz to jumps via the UI
        doc.correct_word("jumpsz", "jumps");
        int[] temp6 = doc.get_doc_error_values();
        //test correcting some of the words - 6a
        if(temp6[1] == 1){
            System.out.println("Test 6a: Passed");
        }else{
            System.out.println("Test 6a: Failed");
        }

        //test getting some of the words - 6b
        
        while(doc.getNext_node() != null){
            curr = doc.getNext_node();
            if(curr.getWord().equals("jumps")){
                System.out.println("Test 6b: Passed");
        }else if(curr.getWord().equals("jumpsz")){
            System.out.println("Test 6b: Failed");
        }
        }


        //test check get and set of current double words: Test 7
        Document t7 = new Document("Tommorrow", "will", "be", "an", "an", "intersting", "day", "for", "our", "picnic.", "\n");
        t7.run_spell_check();
        int[] temp7 = t7.get_doc_error_values();
        if(temp7[2] == test_double){
            System.out.println("Test 7: Passed");
        }else{
            System.out.println("Test 7: Failed");
        }

        //test check get and set of corrected double words: Test 8
        //assume the user removes one of the instances of an
        t7.correct_word("an", "");
        int[] temp8 = t7.get_doc_error_values();
        if(temp8[2] == 0 && temp8[3] == 1){
            System.out.println("Test 8: Passed");
        }else{
            System.out.println("Test 8: Failed");
        }

        //test check get and set of current capital errors: Test 9
        Document t9 = new Document("it's", "a", "beautifull", "sunny", "day", "outside.", "\n");
        t9.run_spell_check();
        int[] temp9 = t9.get_doc_error_values();
        if(temp9[4] == 1){
            System.out.println("Test 9: Passed");
        }else{
            System.out.println("Test 9: Failed");
        }

        //test check get and set of corrected capital errors: Test 10
        //assume the user capitalizes the i in it's
        t9.correct_word("it's", "It's");
        int[] temp10 = t9.get_doc_error_values();
        if(temp10[4] == 0 && temp10[5] == 1){
            System.out.println("Test 10: Passed");
        }else{
            System.out.println("Test 10: Failed");
        }

        //test check get and set of current misspelt words: Test 11
        Document t11 = new Document("She", "said", "her", "favoritte", "book", "is", "\"The", "Great", "Gatsby\".", "\n");
        t11.run_spell_check();
        int[] temp11 = t11.get_doc_error_values();
        if(temp11[0] == 1){
            System.out.println("Test 11: Passed");
        }else{
            System.out.println("Test 11: Failed");
        }

        //test check get and set of corrected misspelt words: Test 12
        //assume the user corrects the word favoritte to favorite
        t11.correct_word("favoritte", "favorite");
        int[] temp12 = t11.get_doc_error_values();
        if(temp12[0] == 0 && temp12[1] == 1){
            System.out.println("Test 12: Passed");
        }else{
            System.out.println("Test 12: Failed");
        }
        
    }   
}