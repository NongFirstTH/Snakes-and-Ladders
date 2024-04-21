import java.util.LinkedList;
import java.util.Queue;

public class BoardFactory {
    public Board Board3Player(){
        int n = 10;
        Player p1 = new Player("red");
        Player p2 = new Player("blue");
        Player p3 = new Player("green");

        Queue<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

        return new Board(n,players);
    }
}
