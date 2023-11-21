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

    public int get_length(){
        int length = 0;
        Word_Object curr = head;
        while(curr != null){
            length++;
            curr = curr.getNext_node();
        }
        return length;
    }
    

    // Add other methods as needed, such as remove, find, etc.
}