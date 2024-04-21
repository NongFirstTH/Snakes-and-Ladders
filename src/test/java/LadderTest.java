import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LadderTest {
    BoardFactory bf = new BoardFactory();
    Board b = bf.Board3Player();
    @Test
    public void testSameObject(){
        assertEquals(b.getBoard()[9][1],b.ladders.getFirst());
        assertEquals(b.getBoard()[6][1],b.ladders.getFirst());
    }
    @Test
    public void testHead(){
        assertEquals(9,b.ladders.getFirst().head()[0]);
        assertEquals(1,b.ladders.getFirst().head()[1]);
    }

    @Test
    public void testTail(){
        assertEquals(6,b.ladders.getFirst().tail()[0]);
        assertEquals(1,b.ladders.getFirst().tail()[1]);
    }
}
