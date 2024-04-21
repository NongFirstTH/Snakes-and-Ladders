public class Main {
    public static void main(String[] args) {
        BoardFactory bf = new BoardFactory();
        Board b = bf.Board3Player();
        Game g = new Game(b);
        System.out.println(b.getBoard()[0][0]);
    }
}
