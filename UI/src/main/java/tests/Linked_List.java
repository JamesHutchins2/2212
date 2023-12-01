package tests;

import Backend.Word_Object;
import Backend.LinkedList;





public class Linked_List {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // Test adding elements
        Word_Object word1 = new Word_Object("Hello");
        Word_Object word2 = new Word_Object("World");
        list.add(word1);
        list.add(word2);

        

        //test to see if the strings mnatch
        boolean check1 = list.getHead().getWord().equals("Hello");
        boolean check2 = list.getTail().getWord().equals("World");

        if(check1 && check2){
            System.out.println("Test 1 Passsed");
        }
        else{
            System.out.println("Test 1 Failed");
        }


        // Test get_length
        int len = list.get_length();
        if(len == 2){
            System.out.println("Test 2 Passed");
        }
        else{
            System.out.println("Test 2 Failed");
        }

        // Test addFirst
        Word_Object word3 = new Word_Object("First");
        list.addFirst(word3);
        //check to see if the first word is head
        if(list.getHead().getWord().equals("First")){
            System.out.println("Test 3 Passed");
        }
        else{
            System.out.println("Test 3 Failed");
        }

        // Test removeWord
        list.removeWord(word2);
        // check that length is 2
        if(list.get_length() == 2){
            System.out.println("Test 4 Passed");
        }
        else{
            System.out.println("Test 4 Failed");
        }

        // Test calculate_indices and get_word_at_index
        list.calculate_indicies();
        Word_Object foundWord = list.get_word_at_index(5);
        //check that the word at index 5 is "First"
        if(foundWord.getWord().equals("First")){
            System.out.println("Test 5 Passed");
        }
        else{
            System.out.println("Test 5 Failed");
            System.out.println("word was: " + foundWord.getWord());
        }

        
    }
}
