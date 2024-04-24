public class Main {
    public static void main(String[] args) {
        BoardFactory boardFactory = new BoardFactory();
        Board board = boardFactory.Board3Player();
        Dice dice = new Dice(6);
        Game g = new Game(board, dice);
        g.play();
    }
}