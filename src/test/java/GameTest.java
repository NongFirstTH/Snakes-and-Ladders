import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;

public class GameTest {
    BoardFactory bf = new BoardFactory();
    Board b = bf.Board3Player();
    Dice d = new Dice(6);
    Game g = new Game(b, d);

    @Test
    public void testTurn() {
        int n = 10;
        Player p1 = new Player("red", n);
        Player p2 = new Player("blue", n);
        Player p3 = new Player("green", n);

        p1.setPosition(1);
        p2.setPosition(1);
        p3.setPosition(1);

        LinkedList<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        Board b = new Board(n, players);
        Game g = new Game(b, d);

        assertEquals(p2, g.changeTurnFromCurrentPlayer(p1));
        assertEquals(p3, g.changeTurnFromCurrentPlayer(p2));
        assertEquals(p1, g.changeTurnFromCurrentPlayer(p3));
    }

    @Test
    public void testMove() {
        Player p = b.players.peek();
        p.move(b, 2);
        assertEquals(3, p.getPosition());
        p.move(b, 3);
        assertEquals(14, p.getPosition());
        p.move(b, 1);
        assertEquals(15, p.getPosition());
        p.move(b, 1);
        assertEquals(16, p.getPosition());
        p.move(b, 5);
        assertEquals(21, p.getPosition());
        p.move(b, 1);
        assertEquals(22, p.getPosition());
        p.move(b, 1);
        assertEquals(3, p.getPosition());
        p.move(b, 3);
        assertEquals(14, p.getPosition());

        p.setPosition(99);
        p.move(b, 1);
        assertEquals(100, p.getPosition());
        p.move(b, 3);
        assertEquals(97, p.getPosition());
    }

    @Test
    public void testCheckWinner() {
        Player p = b.players.peek();
        p.setPosition(99);
        p.move(b, 1);
        g.checkWinner();
        assertEquals(g.winners.peek(), p);
    }
}