import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.LinkedList;
import java.util.Queue;

public class TestLadder {
    @Test
    public void testHead(){
        int n = 10;
        Player p1 = new Player("red");
        Player p2 = new Player("blue");
        Player p3 = new Player("green");

        Queue<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);

       Board b = new Board(n,players);
       assertEquals(b.getBoard()[9][1],b.ladders.getFirst().head());

    }

    @Test
    public void testTail(){

    }
}
