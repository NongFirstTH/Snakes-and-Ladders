import java.util.LinkedList;

public class Board {
    public Object[][] cells;
    public LinkedList<Player> players;
    public LinkedList<Ladder> ladders = new LinkedList<>();
    public LinkedList<Snake> snakes = new LinkedList<>();

    public Board(int boardSize, LinkedList<Player> players) {
        this.cells = new Object[boardSize][boardSize];
        this.players = players;

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
            cells[ladder.head()[0]][ladder.head()[1]] = ladder;
            cells[ladder.tail()[0]][ladder.tail()[1]] = ladder;
        }
    }

    private void placeSnake() {
        for (Snake snake : snakes) {
            cells[snake.head()[0]][snake.head()[1]] = snake;
            cells[snake.tail()[0]][snake.tail()[1]] = snake;
        }
    }
}