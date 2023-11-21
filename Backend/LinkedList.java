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

    boolean isInList(Word_Object node){
        Word_Object curr = head;
        while(curr != null){
            if(curr.getWord().equals(node.getWord())){
                return true;
            }
            curr = curr.getNext_node();
        }
        return false;
    }

    int length(){
        Word_Object curr = head;
        int count = 0;
        while(curr != null){
            count++;
            curr = curr.getNext_node();
        }
        return count;
    }

    Word_Object get(int index){
        Word_Object curr = head;
        int count = 0;
        while(curr != null){
            if(count == index){
                return curr;
            }
            count++;
            curr = curr.getNext_node();
        }
        return null;
    }

}