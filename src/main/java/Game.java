import java.util.LinkedList;
import java.util.Queue;

public class Game {
    public Board board;
    public Queue<Player> winners = new LinkedList<>();
    private int boardSize;
    private Dice dice;

    public Game(Board board, Dice dice) {
        this.board = board;
        this.boardSize = board.cells.length;
        this.dice = dice;
    }

    public void play() {
        Player player = board.players.peek();

        //TODO : ไม่ต้องเอา Player ออกจาก board เกม ให้เช็คตอนเปลี่ยน turn แต่ละคน
        //TODO : ใช่ชื่อให้ condition ใน while เป็น ?
        while (board.players.size() != 1) {
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

    private int calculatePosition(int rowCell, int columnCell) {
        int position = 1;
        int remainDigit = (boardSize - 1 - rowCell) * 10;

        if (rowCell % 2 == 0) {
            int unitDigit = boardSize - columnCell;

            position = remainDigit + unitDigit;
        } else {
            int unitDigit = columnCell + 1;

            position = remainDigit + unitDigit;
        }

        return position;
    }

    public void printHeader(Player player, int roll) {
        int dashWidth = ((boardSize - 1) * 18) + 1;
        int textTurnRollWidth = 14;
        int textAtMiddleWidth = dashWidth - textTurnRollWidth - player.getColor().length();
        
        //TODO : d -> D
        int digitOfdice = (int) Math.log10(roll) + 1;
        int headerWidth = (textAtMiddleWidth - digitOfdice) / 2;
        
        //TODO : H -> h
        String Header = "|" + "-".repeat(headerWidth) + "%s" + "-".repeat(headerWidth) + "|";

        System.out.printf(Header, player.getColor() + " turn: roll = " + roll);
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
                        System.out.printf(format, calculatePosition(rowCell, columnCell));
                } else {
                    System.out.printf(String.format(format, stringPlayer.toString()));
                }

                if (board.cells[rowCell][columnCell] instanceof Ladder ladder) {
                    System.out.printf(format, "| " + ladder.name() + " |");
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

        //TODO : สร้างฟังก์ชันแยกตรง print repeat
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