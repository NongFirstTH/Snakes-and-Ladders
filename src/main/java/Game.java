import java.util.LinkedList;
import java.util.Queue;

public class Game {
    public Board board;
    public Queue<Player> winners = new LinkedList<>();
    private int boardSize;
    private int finishPosition;
    private Dice dice;

    public Game(Board board, Dice dice) {
        this.board = board;
        this.boardSize = board.cells.length;
        this.finishPosition = boardSize * boardSize;
        this.dice = dice;
    }

    public void play() {
        Player player = board.players.peek();

        while (isPlayerRemainOnBoard()) {
            int face = player.rollDice(dice);

            player.move(board, face);
            printHeader(player, face);
            printBoard();
            printFooter(player);
            checkWinner();
            player = changeTurnFromCurrentPlayer(player);
        }

        printWinners();
    }

    private Boolean isPlayerRemainOnBoard() {
        return board.players.size() != 1;
    }

    public Player changeTurnFromCurrentPlayer(Player player) {
        int indexOfNextPlayer = board.players.indexOf(player) + 1;

        return board.players.get(indexOfNextPlayer % board.players.size());
    }

    public void checkWinner() {
        for (Player player : board.players) {
            if (isPlayerAtFinishPosition(player)) {
                winners.add(player);
                board.players.remove(player);
                return;
            }
        }
    }

    private int calculatePosition(int rowCell, int columnCell, int allNumberOfCellAboveCurrentRowCell) {
        int position = 1;

        if (isBoardSizeEven()) {
            if (isRowOfCellEven(rowCell)) {
                position = descendingOrderOfPosition(allNumberOfCellAboveCurrentRowCell, columnCell);
            } else {
                position = ascendingOrderOfPosition(allNumberOfCellAboveCurrentRowCell, columnCell);
            }
        } else {
            if (isRowOfCellEven(rowCell)) {
                position = ascendingOrderOfPosition(allNumberOfCellAboveCurrentRowCell, columnCell);
            } else {
                position = descendingOrderOfPosition(allNumberOfCellAboveCurrentRowCell, columnCell);
            }
        }

        return finishPosition - position;
    }

    private Boolean isPlayerAtFinishPosition(Player player) {
        return player.getPosition() == finishPosition;
    }
    
    private Boolean isBoardSizeEven() {
        return boardSize % 2 == 0;
    }

    private Boolean isRowOfCellEven(int rowCell) {
        return rowCell % 2 == 0;
    }

    private int descendingOrderOfPosition(int allNumberOfCellAboveCurrentRowCell, int columnCell) {
        return allNumberOfCellAboveCurrentRowCell + columnCell;
    }

    private int ascendingOrderOfPosition(int allNumberOfCellAboveCurrentRowCell, int columnCell) {
        return allNumberOfCellAboveCurrentRowCell - columnCell + boardSize - 1;
    }

    public void printHeader(Player player, int roll) {
        int dashWidth = ((boardSize - 1) * 18) + 1;
        int textTurnRollWidth = 14;
        int textAtMiddleWidth = dashWidth - textTurnRollWidth - player.getColor().length();
        int digitOfDice = (int) Math.log10(roll) + 1;
        int headerWidth = (textAtMiddleWidth - digitOfDice) / 2;
        String header = "|" + "-".repeat(headerWidth) + "%s" + "-".repeat(headerWidth) + "|";

        System.out.printf(header, player.getColor() + " turn: roll = " + roll);
        System.out.println();
    }

    public void printFooter(Player player) {
        int dashWidth = ((boardSize - 1) * 18) + 1;
        int textGoToPositionWidth = 14;
        int textAtMiddleWidth = dashWidth - textGoToPositionWidth - player.getColor().length();
        int digitOfPosition = (int) Math.log10(player.getPosition());
        int footerWidth = (textAtMiddleWidth - digitOfPosition) / 2;
        String footer = "|" + "-".repeat(footerWidth) + "%s" + "-".repeat(footerWidth) + "|";

        System.out.printf(footer, player.getColor() + " go to: possition = " + player.getPosition());
        System.out.println();
    }

    public void printBoard() {
        for (int rowCell = 0; rowCell < boardSize; rowCell++) {
            int allNumberOfCellAboveCurrentRowCell = rowCell * boardSize;

            for (int columnCell = 0; columnCell < boardSize; columnCell++) {
                StringBuilder stringPlayer = new StringBuilder();
                boolean hasPlayerAtCell = false;

                for (Player player : board.players) {
                    if (rowCell == player.getCurrentRowCell() && columnCell == player.getCurrentColumnCell()) {
                        stringPlayer.append(player.getColor()).append(" ");
                        hasPlayerAtCell = true;
                    }
                }

                String format = "%" + (-18) + "s";

                if (!hasPlayerAtCell) {
                    if (board.cells[rowCell][columnCell] == null)
                        System.out.printf(format,calculatePosition(rowCell, columnCell, allNumberOfCellAboveCurrentRowCell));
                } else {
                    System.out.printf(String.format(format, stringPlayer.toString()));
                }

                if (board.cells[rowCell][columnCell] instanceof Ladder ladder) {
                    System.out.printf(format, "|" + ladder.name() + "|");
                } else if (board.cells[rowCell][columnCell] instanceof Snake snake) {
                    System.out.printf(format, '_' + snake.name() + '_');
                }
            }

            System.out.println();
        }
    }

    public void printWinners() {
        String format = "|%-10s|";
        int rank = 1;

        repeatDash();
        System.out.printf(format, "Winner");
        System.out.println();
        repeatDash();

        while (!winners.isEmpty()) {
            System.out.printf(format, rank++ + " " + winners.poll().getColor());
            System.out.println();
        }

        System.out.printf(format, rank + " " + board.players.poll().getColor());
        System.out.println();
        repeatDash();
    }

    private void repeatDash() {
        System.out.println("-".repeat(12));
    }
}