import java.util.LinkedList;
import java.util.Queue;

public class Board {
    public Object[][] cell;
    public Queue<Player> players;
    public LinkedList<Ladder> ladders = new LinkedList<>();
    public LinkedList<Snake> snakes = new LinkedList<>();

    public Board(int boardSize, Queue<Player> players) {
        this.cell = new Object[boardSize][boardSize];// TODO : เปลี่ยนชื่อเป็น cells
        this.players = players;
        // TODO : ต้องเปลี่ยน boardSize ได้
        ladders.add(new Ladder("L1", new int[] { boardSize - 1, 1 }, new int[] { boardSize - 4, 1 }));
        ladders.add(new Ladder("L2", new int[] { boardSize - 1, 5 }, new int[] { boardSize - 2, 6 }));
        ladders.add(new Ladder("L3", new int[] { boardSize - 3, 7 }, new int[] { boardSize - 6, 7 }));
        ladders.add(new Ladder("L4", new int[] { boardSize - 5, 3 }, new int[] { boardSize - 7, 2 }));
        ladders.add(new Ladder("L5", new int[] { boardSize - 8, 6 }, new int[] { 0, 5 }));

        snakes.add(new Snake("S1", new int[] { boardSize - 3, 2 }, new int[] { boardSize - 1, 2 }));
        snakes.add(new Snake("S2", new int[] { boardSize - 5, 5 }, new int[] { boardSize - 3, 5 }));
        snakes.add(new Snake("S3", new int[] { boardSize - 6, 1 }, new int[] { boardSize - 5, 2 }));
        snakes.add(new Snake("S4", new int[] { boardSize - 8, 7 }, new int[] { boardSize - 6, 6 }));
        snakes.add(new Snake("S5", new int[] { 0, 9 }, new int[] { boardSize - 5, 9 }));
        snakes.add(new Snake("S6", new int[] { 0, 2 }, new int[] { boardSize - 6, 4 }));

        placeLadder();
        placeSnake();
    }

    private void placeLadder() {
        for (Ladder ladder : ladders) {
            cell[ladder.head()[0]][ladder.head()[1]] = ladder;
            cell[ladder.tail()[0]][ladder.tail()[1]] = ladder;
        }
    }

    private void placeSnake() {
        for (Snake snake : snakes) {
            cell[snake.head()[0]][snake.head()[1]] = snake;
            cell[snake.tail()[0]][snake.tail()[1]] = snake;
        }
    }
}
