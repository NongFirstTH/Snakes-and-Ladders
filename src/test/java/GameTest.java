import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.Queue;

public class GameTest {
    BoardFactory bf = new BoardFactory();
    Board b = bf.Board3Player();
    Game g = new Game(b);
    
    @Test
    public void testTurn(){
        int n = 10;
        Player p1 = new Player("red");
        Player p2 = new Player("blue");
        Player p3 = new Player("green");
        
        p1.setPosition(1, n);
        p2.setPosition(1, n);
        p3.setPosition(1, n);
        
        Queue<Player> players = new LinkedList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        Board b = new Board(n, players);
        Game g = new Game(b);

        g.turn();
        assertEquals(b.players.peek(),p2);
        g.turn();
        assertEquals(b.players.peek(),p3);
        g.turn();
        assertEquals(b.players.peek(),p1);
    }

    @Test
    public void testCheckLadderHead(){
        assertEquals(null,g.checkLadderHead(6,1));
        assertEquals(b.getBoard()[9][1],g.checkLadderHead(9,1));
        assertEquals(null,g.checkLadderHead(6,1));
        assertEquals(b.getBoard()[6][1],g.checkLadderHead(9,1));
        assertNotEquals(b.getBoard()[6][1],g.checkLadderHead(9,5));
        assertNotEquals(b.getBoard()[9][1],g.checkLadderHead(9,6));
        assertNull(g.checkLadderHead(2,1));
    }
    
    @Test
    public void testCheckSnakeHead(){
        assertEquals(null,g.checkSnakeHead(9,2));
        assertEquals(b.getBoard()[7][2],g.checkSnakeHead(7,2));
        assertEquals(null,g.checkSnakeHead(9,2));
        assertEquals(b.getBoard()[9][2],g.checkSnakeHead(7,2));
        assertNotEquals(b.getBoard()[9][2],g.checkSnakeHead(7,5));
        assertNotEquals(b.getBoard()[7][2],g.checkSnakeHead(9,6));
        assertNull(g.checkSnakeHead(2,1));
    }

    @Test
    public void testMove(){
        Player p = b.players.peek();
        g.move(p,2);
        assertEquals(3, p.position);
        g.move(p,3);
        assertEquals(14, p.position);
        g.move(p,1);
        assertEquals(15, p.position);
        g.move(p,1);
        assertEquals(16, p.position);
        g.move(p,5);
        assertEquals(21, p.position);
        g.move(p,1);
        assertEquals(22, p.position);
        g.move(p,1);
        assertEquals(3, p.position);
        g.move(p,3);
        assertEquals(14, p.position);

        p.setPosition(99,10);
        g.move(p,1);
        assertEquals(100, p.position);
        g.move(p,3);
        assertEquals(97, p.position);
    }

    @Test
    public void testCheckWinner(){
        Player p = b.players.peek();
        p.setPosition(99,10);
        g.move(p,1);
        g.checkWinner();
        assertEquals(g.winner.peek(),p);
    }
}