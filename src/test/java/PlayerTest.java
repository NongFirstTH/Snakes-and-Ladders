import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    BoardFactory bf = new BoardFactory();
    Board b = bf.Board3Player();
    @Test
    public void TestsetPosition(){
        Player p = b.players.peek();
        p.setPosition(3);
        assertEquals(3, p.getPosition());
    }
    @Test
    public void TestCurrow(){
        assertEquals(9,b.players.peek().getCurrentRowCell());
    }
    @Test
    public void TestCurcol(){
        assertEquals(0,b.players.peek().getCurrentColumnCell());
    }
    @Test
    public void TestRollDice(){
        Dice d = new Dice(6);
        for(int i = 0;i<1000;i++){
            int r = b.players.peek().rollDice(d);
            assertTrue(r >= 1 && r <= 6);
        }
    }
}