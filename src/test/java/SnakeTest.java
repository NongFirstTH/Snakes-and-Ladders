import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SnakeTest{
    BoardFactory bf = new BoardFactory();
    Board b = bf.Board3Player();
    @Test
    public void testSameObject(){
        assertEquals(b.cell[7][2],b.snakes.getFirst());
        assertEquals(b.cell[9][2],b.snakes.getFirst());
    }
    @Test
    public void testHead(){
        assertEquals(7,b.snakes.getFirst().head()[0]);
        assertEquals(2,b.snakes.getFirst().head()[1]);
    }

    @Test
    public void testTail(){
        assertEquals(9,b.snakes.getFirst().tail()[0]);
        assertEquals(2,b.snakes.getFirst().tail()[1]);
    }
}