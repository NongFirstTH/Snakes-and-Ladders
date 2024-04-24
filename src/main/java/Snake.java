public class Snake {
    private String name;
    private int[] head = new int[2];
    private int[] tail = new int[2];

    public Snake(String name, int[] head, int[] tail) {
        this.name = name;
        this.head = head;
        this.tail = tail;
    }

    public int[] head() {
        return head;
    }

    public int[] tail() {
        return tail;
    }

    public String name() {
        return name;
    }
}