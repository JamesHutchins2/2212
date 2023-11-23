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


    public void calculate_indicies() {
        // We will populate the index values for each word object.
        // Get the head of the linked list
        Word_Object curr = head;
    
        // Set the start index to 0
        int index = 0;
    
        // Loop through the linked list
        while (curr != null) {
            // Set the start index
            curr.setStart_index(index);
    
            // Get the word
            String word = curr.getWord();
    
            // Get the length of the word
            int word_length = word.length();
    
            // Add the word length to the index
            index += word_length;
    
            // Set the end index
            curr.setEnd_index(index);
    
            // Increment the index by 1 for space, by 2 for period and space
            if (curr.isEnd_with_period()) {
                index += 2;
            } else {
                index += 1;
            }
    
            // Move to the next node
            curr = curr.getNext_node();
        }
    }
    

    public Word_Object get_word_at_index(int index){
        System.out.println("get word at index: " + index);
        //get the head of the linked list
        Word_Object curr = head;
        System.out.println("index: " + index);
        System.out.println("start index: " + curr.getWord());

        //loop through the linked list
        while(curr != null){

            //check to see if the index is in the range of the word
            if(index >= curr.getStart_index() && index <= curr.getEnd_index()){
                return curr;
            }

            //increment the index
            curr = curr.getNext_node();

        }

        //if we get here, we did not find the word
        return null;

    }
    
    

    // Add other methods as needed, such as remove, find, etc.
}