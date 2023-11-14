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

    // This version/branch is copied and pasted from another branch nourans-linkedlist
    // Remove a node from the list
    public void remove(Word_Object node) {
        if (node == head) {
            head = node.getNext_node();
            head.setPrev_node(null);
        } else if (node == tail) {
            tail = node.getPrev_node();
            tail.setNext_node(null);
        } else {
            node.getPrev_node().setNext_node(node.getNext_node());
            node.getNext_node().setPrev_node(node.getPrev_node());
        }
    } 

    void checkPeriod(Word_Object node){
        if(node.getWord().endsWith(".")){
            node.setEnd_with_period(true);
        }   
    }

    void checkCapital(Word_Object node){
        if(Character.isUpperCase(node.getWord().charAt(0))){
            node.setStart_with_capital(true);
        }
    }
}
}
