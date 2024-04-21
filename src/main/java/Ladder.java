public class Ladder implements ObjectInterface{
    private int [] head = new int[2];
    private int [] tail = new int[2];

    public Ladder(int [] head,int [] tail) {
        this.head = head;
        this.tail = tail;
    }
    
    public int [] head(){
        return head;
    }
    
    public int [] tail(){
        return tail;
    }
}
