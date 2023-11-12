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

    // Add other methods as needed, such as remove, find, etc.
}