public class Main {
    public static void main(String[] args) {
        BoardFactory bf = new BoardFactory();
        Board b = bf.Board3Player();
        Game g = new Game(b);
        while(b.players.size()!=1){
            g.play();
        }
    }
}
