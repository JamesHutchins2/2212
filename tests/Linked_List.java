package tests;
import Backend.LinkedList;
import Backend.Word_Object;
import Backend.LinkedList;
import Backend.Word_Object;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Linked_List_Test {
    public void testLinkedList(){

        // Create a linked list object
        Linked_List_Test linked_list = new Linked_List_Test();
        linkedList.add(wordObject);

        System.out.println("Running Linked_List_Test");

        // Test Case 1: check if linked list is created
        if (linked_list != null){
        
            Linked_List_Test current = head;
            while (current != null) {

                // Move to the next node
                current = current.next();
            }
            System.out.println("Test case 1 passed: Linked list is created and is not empty.");
        } 
        else {
            System.out.println("Test case 1 failed: Linked list not created.");
        }

        // Test Case 2: check if word object is added to linked list
        // Create word object
        Word_Object wordObject = new Word_Object("example");

        // Check if the word is in the linked list
        if (linkedList.containsWord("example")) {
            System.out.println("Test case 2 passed: Word added to the linked list.");
        } 
        else {
            System.out.println("Test case 2 failed: Word not added to the linked list.");
        }

        // Test Case 3: Check if words are removed from linked list
        linkedList.removeWord(wordObject);

        // Check if the word object is not present in the linked list after removal
        if (!linkedList.containsWord("example")) {
            System.out.println("Test case 3 passed: word object deleted from the linked list.");
        } 
        else {
            System.out.println("Test case 3 failed: word object still present in the linked list.");
        }


    }


}
