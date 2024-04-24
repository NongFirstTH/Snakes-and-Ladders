import java.util.LinkedList;
import java.util.Queue;

public class Game {
    public Board board;
    public Queue<Player> winners = new LinkedList<>();
    private int boardSize;
    private Dice dice;

    public Game(Board board, Dice dice) {// TODO : เว้น spacebarก่อนปีกกา
        this.board = board;
        this.boardSize = board.cells.length;
        this.dice = dice;
    }

    public void play() {
        Player player = board.players.peek();

        while (board.players.size() != 1) {
            int face = player.rollDice(dice);// TODO : เปลี่ยน roll เป็น face

            player.move(board, face);
            printHeader(player, face);
            printBoard();
            printFooter(player);
            checkWinner();
            player = changeTurnFromCurrentPlayer(player);
        }

        printWinners();
    }

    public Player changeTurnFromCurrentPlayer(Player player) {
        int indexOfNextPlayer = board.players.indexOf(player) + 1;
        return board.players.get(indexOfNextPlayer % board.players.size());
    }

    public void checkWinner() {
        int finishPosition = boardSize * boardSize;

        for (Player player : board.players) {
            if (player.getPosition() == finishPosition) {
                winners.add(player);
                board.players.remove(player);
                return;
            }
        }
    }

    private int calculatePosition(int rowCell, int colCell) {
        int position = 1;
        int remainDigit = (boardSize - 1 - rowCell) * 10;

        if (rowCell % 2 == 0) {
            int unitDigit = boardSize - colCell;

            position = remainDigit + unitDigit;
        } else {
            int unitDigit = colCell + 1;

            position = remainDigit + unitDigit;
        }

        return position;
    }

    public void printHeader(Player player, int roll) {
        int dashWidth = 163;
        int textAtMiddleWidth = dashWidth - 14 - player.getColor().length();
        int digitOfdice = (int) Math.log10(roll) + 1;
        int headerWidth = (textAtMiddleWidth - digitOfdice) / 2;
        String Header = "|" + "-".repeat(headerWidth) + "%s" + "-".repeat(headerWidth) + "|";

        System.out.printf(Header, player.getColor() + " turn: roll = " + roll);
        System.out.println();
    }

    public void printFooter(Player player) {
        int dashWidth = 163;
        int textAtMiddleWidth = dashWidth - 20 - player.getColor().length();
        int digitOfPosition = (int) Math.log10(player.getPosition());
        int footerWidth = (textAtMiddleWidth - digitOfPosition) / 2;
        String footer = "|" + "-".repeat(footerWidth) + "%s" + "-".repeat(footerWidth) + "|";

        System.out.printf(footer, player.getColor() + " go to: possition = " + player.getPosition());
        System.out.println();
    }

    public void printBoard() {
        for (int rowCell = 0; rowCell < boardSize; rowCell++) {
            for (int colCell = 0; colCell < boardSize; colCell++) {
                StringBuilder stringPlayer = new StringBuilder();
                boolean hasPlayerAtCell = false;

                for (Player player : board.players) {
                    if (rowCell == player.getCurrentRowCell() && colCell == player.getCurrentColumnCell()) {
                        stringPlayer.append(player.getColor()).append(" ");
                        hasPlayerAtCell = true;
                    }
                }

                String format = "%" + (-18) + "s";

                if (!hasPlayerAtCell) {
                    if (board.cells[rowCell][colCell] == null)
                        System.out.printf(format, calculatePosition(rowCell, colCell));
                } else {
                    System.out.printf(String.format(format, stringPlayer.toString()));
                }

                if (board.cells[rowCell][colCell] instanceof Ladder ladder) {
                    System.out.printf(format, "| " + ladder.name() + " |");
                } else if (board.cells[rowCell][colCell] instanceof Snake snake) {
                    System.out.printf(format, '_' + snake.name() + '_');
                }

            }
            System.out.println();
        }
    }

    public void printWinners() {
        String format = "|%-10s|";
        int rank = 1;

        System.out.print("-".repeat(12));
        System.out.println();
        System.out.printf(format, "Winner");
        System.out.println();
        System.out.print("-".repeat(12));
        System.out.println();

        while (!winners.isEmpty()) {
            System.out.printf(format, rank++ + " " + winners.poll().getColor());
            System.out.println();
        }

        System.out.printf(format, rank + " " + board.players.poll().getColor());

        System.out.println();
        System.out.print("-".repeat(12));
        System.out.println();
    }
}