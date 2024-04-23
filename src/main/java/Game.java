import java.util.LinkedList;
import java.util.Queue;

public class Game {
    public Board board;
    public Queue<Player> winners = new LinkedList<>();
    private int boardSize;

    public Game(Board board){
        this.board = board;
        this.boardSize = board.cell.length;
    }

    public void play(){
        while(board.players.size()!=1) {
            Player player = board.players.peek();
            int roll = player.rollDice();

            player.move(board, roll);
            printHeader(player, roll);
            printBoard();
            printFooter(player, roll);
            checkWinner();
            changeTurn();
        }
        
        printWinners();
    }
    
    public void changeTurn(){
        Player player = board.players.poll();
        board.players.add(player);
    }

    public void checkWinner(){
        int finishPosition = boardSize*boardSize;

        for(Player player : board.players){
            if(player.getPosition() == finishPosition){
                winners.add(player);
                board.players.remove(player);
                return;
            }
        }
    }

    private int calPosition(int rowCell,int colCell){
        int position = 1;
        int remainDigit = (boardSize-1-rowCell)*10;
        
        if(rowCell%2 == 0){
            int unitDigit = boardSize-colCell;

            position = remainDigit + unitDigit;
        }else{
            int unitDigit = colCell+1;

            position = remainDigit + unitDigit;
        } 

        return position;
    }

    public void printHeader(Player player,int roll){
        int headerWidth = (163-14-player.getColor().length()-((int)Math.log10(player.getPosition())+1))/2;
        String Header = "|" + "-".repeat(headerWidth) + "%s" + "-".repeat(headerWidth) + "|";

        System.out.printf(Header,player.getColor() + " turn: roll = " + roll + '\n');
    }

    public void printFooter(Player player,int roll){
        int footerWidth = (163-20-player.getColor().length()-((int)Math.log10(player.getPosition())+1))/2;
        String footer = "|" + "-".repeat(footerWidth) + "%s" + "-".repeat(footerWidth) + "|";

        System.out.printf(footer,player.getColor() +" go to: possition = " + player.getPosition() + '\n');
    }

    public void printBoard(){
        for(int rowCell = 0; rowCell < boardSize; rowCell++){
            for(int colCell = 0; colCell < boardSize; colCell++){
                StringBuilder stringPlayer = new StringBuilder();
                boolean hasPlayerAtCell = false;

                for(Player player : board.players){
                    if(rowCell == player.getCurrentRowCell() && colCell == player.getCurrentColCell()){
                        stringPlayer.append(player.getColor()).append(" ");
                        hasPlayerAtCell = true;
                    }
                }

                String format = "%" + (-18) + "s";

                if(!hasPlayerAtCell) {
                    if(board.cell[rowCell][colCell] == null) System.out.printf(format,calPosition(rowCell,colCell));
                }else{
                    System.out.printf(String.format(format,stringPlayer.toString()));
                }

                if(board.cell[rowCell][colCell] instanceof Ladder ladder){
                    System.out.printf(format,"| " + ladder.name() + " |");
                }else if(board.cell[rowCell][colCell] instanceof Snake snake){
                    System.out.printf(format,'_' + snake.name() + '_');
                }

            }
            System.out.println();
        }
    }

    public void printWinners(){
        String format = "|%-10s|";
        int rank = 1;

        System.out.print("-".repeat(12));
        System.out.println();
        System.out.printf(format,"Winner");
        System.out.println();
        System.out.print("-".repeat(12));
        System.out.println();

        while(!winners.isEmpty()){
            System.out.printf(format,rank++ +" "+winners.poll().getColor());
            System.out.println();
        }

        System.out.printf(format,rank +" "+board.players.poll().getColor());
        
        System.out.println();
        System.out.print("-".repeat(12));
        System.out.println();
    }
}

