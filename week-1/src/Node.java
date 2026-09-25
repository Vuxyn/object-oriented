public class Node {
    Node next;
    int id;

    public Node(Node next, int id){
        this.id = id;
        this.next = next;
    }

    int getId(){
        return this.id;
    }

    Node getNext(){
        return this.next;
    }

    void setId(int id){
        this.id = id;
    }

    void setNext(Node next){
        this.next = next;
    }
}

