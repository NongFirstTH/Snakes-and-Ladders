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

        for(Player p : players){
            p.currow = n-1;
            p.curcol = 0;
        }

        ladders.add(new Ladder(new int[]{n-1,1},new int[]{n-4,1}));
        ladders.add(new Ladder(new int[]{n-1,5},new int[]{n-2,6}));

        snakes.add(new Snake(new int[]{n-3,2},new int[]{n-1,2}));
        snakes.add(new Snake(new int[]{n-5,5},new int[]{n-3,5}));

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
