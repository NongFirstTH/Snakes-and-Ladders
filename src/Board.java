import java.util.LinkedList;
import java.util.Queue;

public class Board{
    public Object [][] arr;
    public Queue<Player> players;
    public LinkedList<Ladder> ladders;
    public LinkedList<Snake> snakes;

    public Board(int n){
        this.arr = new Object[n][n];
        //แต่ละคนยืนจุดเริ่ม
    }

    public Object[][] getBoard(){
        return arr;
    }

    public void placeLadder(Ladder ladder){
        arr[ladder.head()[0]][ladder.head()[1]] = ladder;
        arr[ladder.tail()[0]][ladder.tail()[1]] = ladder;
    }
    
    public void placeSnake(Snake snake){
        arr[snake.head()[0]][snake.head()[1]] = snake;
        arr[snake.tail()[0]][snake.tail()[1]] = snake;
    }
}
