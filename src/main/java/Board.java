import java.util.LinkedList;
import java.util.Queue;

public class Board{
    public Object [][] arr;
    public Queue<Player> players;
    public LinkedList<Ladder> ladders = new LinkedList<>();
    public LinkedList<Snake> snakes = new LinkedList<>();

    public Board(int n,Queue<Player> players){
        this.arr = new Object[n][n];
        this.players = players;
        
        ladders.add(new Ladder("L1",new int[]{n-1,1},new int[]{n-4,1}));
        ladders.add(new Ladder("L2",new int[]{n-1,5},new int[]{n-2,6}));
        ladders.add(new Ladder("L3",new int[]{n-3,7},new int[]{n-6,7}));
        ladders.add(new Ladder("L4",new int[]{n-5,3},new int[]{n-7,2}));
        ladders.add(new Ladder("L5",new int[]{n-8,6},new int[]{0,5}));

        snakes.add(new Snake("S1",new int[]{n-3,2},new int[]{n-1,2}));
        snakes.add(new Snake("S2",new int[]{n-5,5},new int[]{n-3,5}));
        snakes.add(new Snake("S3",new int[]{n-6,1},new int[]{n-5,2}));
        snakes.add(new Snake("S4",new int[]{n-8,7},new int[]{n-6,6}));
        snakes.add(new Snake("S5",new int[]{0,9},new int[]{n-5,9}));
        snakes.add(new Snake("S6",new int[]{0,2},new int[]{n-6,4}));

        placeLadder();
        placeSnake();
    }

    public Object[][] getBoard(){
        return arr;
    }

    private void placeLadder(){
        for(Ladder ladder : ladders){
            arr[ladder.head()[0]][ladder.head()[1]] = ladder;
            arr[ladder.tail()[0]][ladder.tail()[1]] = ladder;
        }
    }

    private void placeSnake(){
        for(Snake snake : snakes){
            arr[snake.head()[0]][snake.head()[1]] = snake;
            arr[snake.tail()[0]][snake.tail()[1]] = snake;
        }
    }
}
