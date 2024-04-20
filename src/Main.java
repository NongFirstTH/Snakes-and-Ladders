import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int n = 10;
        Board board = new Board(n);
        Player p1 = new Player("red");
        Player p2 = new Player("blue");
        Player p3 = new Player("green");
        
        Queue<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        
        LinkedList<Ladder> ladders = new LinkedList<>();
        ladders.add(new Ladder(new int[]{n-1,1},new int[]{n-4,1}));
        ladders.add(new Ladder(new int[]{n-1,5},new int[]{n-2,6}));

        LinkedList<Snake> snakes = new LinkedList<>();

        Game g = new Game(board);
    }
}
