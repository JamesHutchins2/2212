package tests;
import Backend.Document;
import Backend.Word_Object;
import Backend.Doc_Error;
import Backend.Doc_Analysis;

public class Doc_Error_test {

    public void testDocError(){
        //create a doc error object
        Doc_Error doc_error = new Doc_Error();

        System.out.println("Running Doc_Error_test");
    


        //test corpus - updated for the needed format
        String test_corpus = "The Quick brown fox Jumps over the lazy dog.\n" +
        "it's a beautifull sunny day outside.\n" +
        "Tommorrow will be an an intersting day for our picnic.\n" +
        
        "Could you pleese pass the ketchup?\n" +
        "He decided to seperate the papers into two piles.\n" +
        "Their going to visit the museum next week.\n" +
        "The cat chased it's tail around the living room.\n" +
        "We recieved an invitation for the gaLlery opening.\n" +
        "He loves to play the guitar and the pianoo.";

        //values to check errors for
        int test_mispelt = 7;
        int test_double = 1;
        int test_capital = 3;

        //create a document object
        Document doc = new Document(test_corpus);

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
            Document d3a = new Document(word6.getWord());
            d3a.mark_capitals(word6);
            
            if(word6.getIs_capital_at()[2] == 1){
                System.out.println("Test 3a Indicating: Passed");
            }else{
                System.out.println("Test 3a Indicating: Failed");
            }

            if(word6.isNeeds_lower() == true){
                System.out.println("Test 3a Checking: Passed");
            }else{
                System.out.println("Test 3a Checking: Failed");
            }

            //and at the beginning of a sentance not capitalized (b) - line 2, word 1 of test corpus
            Document test3b = new Document("it's a beautifull sunny day outside.\n");
            Word_Object curr3b = test3b.wordBuffer.getHead();
            while(curr3b.hasNext()){
                if(curr3b.getWord().equals("it's")){
                test3b.mark_capitals(curr3b);
                break;
            } 
                curr3b = curr3b.getNext_node();
            }
 
            if(curr3b.getIs_capital_at()[0] == 0){                 //need to double check this val
                System.out.println("Test 3b Marking: Passed");
            }else{
                System.out.println("Test 3b Marking: Failed");
            }
            if(curr3b.isNeeds_capital() == true){
                System.out.println("Test 3b Checking: Passed");
            }else{
                System.out.println("Test 3b Checking: Failed");
            }


            //and in the middle of a sentence capitalized when it shouldnt be (c) - line 1, word 2 of test corpus
            Document test3c = new Document("The Quick brown fox Jumps over the lazy dog.\n");
            Word_Object curr3c = test3c.wordBuffer.getHead();
            while(curr3c.hasNext()){
                if(curr3c.getWord().equals("Quick")){
                test3c.mark_capitals(curr3c);
                break;
            } 
                curr3c = curr3c.getNext_node();
            }

            if(curr3c.getIs_capital_at()[0] == 1){                //need to double check this val
                System.out.println("Test 3c Checking: Passed");
            }else{
                System.out.println("Test 3c Checking: Failed");
            }
            
            if(curr3c.isNeeds_lower() == true){
                System.out.println("Test 3c Marking: Passed");
            }else{
                System.out.println("Test 3c Marking: Failed");
            }

            //checking all capital countings: Test 3d            
            int[] temp = doc.get_doc_error_values();
            System.out.println(temp[4]);
            if(temp[4] == 3){                 //need to double check this val
                System.out.println("Test 3d Number Capital errors: Passed");
            }else{
                System.out.println("Test 3d Number Capital errors: Failed");
            }

        //test check doubles: Test 4 - line 3, word 4 & 5 of test corpus
        int[] temp4 = doc.get_doc_error_values();
        if(temp4[2] == test_double){
            System.out.println("Test 4 Num double word errors: Passed");
        }else{
            System.out.println("Test 4 Num double word errors: Failed");
        }    


        //test check get and set of current misspelt words: Test 5
        int[] temp5 = doc.get_doc_error_values();
        System.out.println(temp5[0]);
        if(temp5[0] == test_mispelt){
            System.out.println("Test 5: Passed");
        }else{
            System.out.println("Test 5: Failed");
        }

        //test check get and set of corrected misspelt words: Test 6
        //assume the user corrects the word jumpsz to jumps via the UI
        Document d6a = new Document("The Quick brown fox jumpsz over the lazy dog.\n");
        Word_Object curr = d6a.wordBuffer.getHead();
        while(curr.hasNext()){
            if(curr.getWord().equals("jumpsz")){
                curr.setWord("jumps");
                d6a.doc_error.downCountMisspelt();
            }
        curr = curr.getNext_node();
    }
        int[] temp6 = d6a.get_doc_error_values();
        //test correcting some of the words - 6a
        if(temp6[1] == 1){
            System.out.println("Test 6a: Passed");
        }else{
            System.out.println("Test 6a: Failed");
        } 
        //test getting some of the words - 6b
    
        Word_Object curr6b = d6a.wordBuffer.getHead();
        while(curr6b.hasNext()){
            if(curr6b.getWord().equals("jumps")){
                System.out.println("Test 6b: Passed");
                break;
        }else if(curr6b.getWord().equals("jumpsz")){
            System.out.println("Test 6b: Failed");
        }
         curr6b = curr6b.getNext_node();
        }
    

        //test check get and set of current double words: Test 7
        Document t7 = new Document("Tommorrow will be an an intersting day for our picnic.\n");
        t7.run_spell_check();
        int[] temp7 = t7.get_doc_error_values();
        if(temp7[2] == test_double){
            System.out.println("Test 7: Passed");
        }else{
            System.out.println("Test 7: Failed");
        }

        //test check get and set of corrected double words: Test 8
        //assume the user removes one of the instances of an
        int instance = 0;
        Word_Object curr8 = t7.wordBuffer.getHead();
        while(curr8.hasNext()){
            if(curr8.getWord().equals("an") && instance == 1){
                curr8.setWord("");
                t7.doc_error.downCountDoubleWord();
            }else if(curr8.getWord().equals("an")){
                instance++;
            }
            curr8 = curr8.getNext_node();
        }

        int[] temp8 = t7.get_doc_error_values();
        if(temp8[2] == 0 && temp8[3] == 1){
            System.out.println("Test 8: Passed");
        }else{
            System.out.println("Test 8: Failed");
        }

        //test check get and set of current capital errors: Test 9
        Document t9 = new Document("it's a beautifull sunny day outside.\n");
        t9.run_spell_check();
        int[] temp9 = t9.get_doc_error_values();
        if(temp9[4] == 1){
            System.out.println("Test 9 Get Capital Error: Passed");
        }else{
            System.out.println("Test 9 Get Capital Error: Failed");
        }

        //test check get and set of corrected capital errors: Test 10
        //assume the user capitalizes the i in it's
        
        Word_Object currt9 = t9.wordBuffer.getHead();
        while(currt9.hasNext()){
            if(currt9.getWord().equals("it's")){
                currt9.setWord("It's");
                t9.doc_error.downCountCapital();
            }
            currt9 = currt9.getNext_node();
        }
        int[] temp10 = t9.get_doc_error_values();
        if(temp10[4] == 0 && temp10[5] == 1){
            System.out.println("Test 10 Correct Capitals: Passed");
        }else{
            System.out.println("Test 10 Correct Capitals: Failed");
        }

        //test check get and set of current misspelt words: Test 11
        Document t11 = new Document("He loves to play the guitar and the pianoo.");
        t11.run_spell_check();
        int[] temp11 = t11.get_doc_error_values();
        if(temp11[0] == 1){
            System.out.println("Test 11: Passed");
        }else{
            System.out.println("Test 11: Failed");
        }

        //test check get and set of corrected misspelt words: Test 12
        //assume the user corrects the word favoritte to favorite
        Word_Object currt11 = t11.wordBuffer.getHead();
        while(currt11 != null){
            if(currt11.getWord().equals("pianoo.")){
                currt11.setWord("piano");
                t11.doc_error.downCountMisspelt();
                break;
            }
            currt11 = currt11.getNext_node();
        }
        
        int[] temp12 = t11.get_doc_error_values();
        if(temp12[0] == 0 && temp12[1] == 1){
            System.out.println("Test 12: Passed");
        }else{
            System.out.println("Test 12: Failed");
        }
        
    }   
}