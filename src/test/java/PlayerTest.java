import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    BoardFactory bf = new BoardFactory();
    Board b = bf.Board3Player();
    int position = b.players.peek().position;
    @Test
    public void TestPosition(){
        assertEquals(1,position);
    }
    @Test
    public void TestCurrow(){
        assertEquals(9,b.players.peek().currow);
    }
    @Test
    public void TestCurcol(){
        assertEquals(0,b.players.peek().curcol);
    }
    @Test
    public void TestRollDice(){
        for(int i = 0;i<1000;i++){
            int r = b.players.peek().RollDice();
            assertTrue(r >= 1 && r <= 6);
        }
    }
}