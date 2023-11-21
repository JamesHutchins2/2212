package Backend;
public class LinkedList {
    private Word_Object head; // Head of the list
    private Word_Object tail; // Tail of the list
    

    public LinkedList() {
        head = null;
        tail = null;
    }

    // Add a new node to the end of the list
    public void add(Word_Object newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.setNext_node(null);
        } else {
            tail.setNext_node(newNode);
            newNode.setPrev_node(tail);
            tail = newNode;
        }
    }

    // Add a new node to the beginning of the list
    public void addFirst(Word_Object newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.setNext_node(null);
        } else {
            newNode.setNext_node(head);
            head.setPrev_node(newNode);
            head = newNode;
        }
    }

    // get the head of the list
    public Word_Object getHead() {
        return head;
    }

    // get the tail of the list
    public Word_Object getTail() {
        return tail;
    }

    public int get_length() {
        int length = 0;
        Word_Object curr = head;
        while (curr != null) {
            length++;
            curr = curr.getNext_node();
        }
        return length;
    }


    public void calculate_indicies(){

        //we will populate the index values for each word object.

        //get the head of the linked list
        Word_Object curr = head;

        //set the start index to 0
        

        int index = 0;

        //loop through the linked list
        while(curr != null){

            //set the start index
            curr.setStart_index(index);

            //get the word
            String word = curr.getWord();

            //get the length of the word
            int word_length = word.length();

            //add the word length to the index
            index = index + word_length;

            //set the end index
            curr.setEnd_index(index);

            //increment the index by 1 for space, by 2 for period and space
            if(curr.isEnd_with_period()){
                index = index + 2;
            }else{
                index = index + 1;
            }

        }


    }
    
    

    // Add other methods as needed, such as remove, find, etc.
}