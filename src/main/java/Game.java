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
            Player p = board.players.peek();
            int roll = p.rollDice();
            p.move(board, roll);
            printBoard(p, roll);
            checkWinner();
            turn();
        }
        printWinner();
    }
    
    public void turn(){
        Player p = board.players.poll();
        board.players.add(p);
    }

    public void checkWinner(){
        int finish = boardSize*boardSize;
        for(Player p : board.players){
            if(p.getPosition() == finish){
                winners.add(p);
                board.players.remove(p);
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
    
    public void printBoard(Player player,int roll){
        int widthhead = (163-14-player.getColor().length()-((int)Math.log10(player.getPosition())+1))/2;
        String Hbar = "|" + "-".repeat(widthhead) + "%s" + "-".repeat(widthhead) + "|";
        System.out.printf(Hbar,player.getColor() + " turn: roll = " + roll);System.out.println();

        for(int i = 0;i<boardSize;i++){
            for(int j = 0;j<boardSize;j++){
                StringBuilder Stringplayer = new StringBuilder();
                boolean hasPlayer = false;
                    for(Player p : board.players){
                        if(i == p.getCurrentRowCell() && j == p.getCurrentColCell()){
                            Stringplayer.append(p.getColor()).append(" ");
                            hasPlayer = true;
                        }
                    }

                    String format = "%" + (-18) + "s";
                if(!hasPlayer) {
                    if(board.cell[i][j] == null) System.out.printf(format,calPosition(i,j));
                }else{
                    System.out.printf(String.format(format,Stringplayer.toString()));
                }

                    if(board.cell[i][j] instanceof Ladder l){
                        System.out.printf(format,"| "+l.name()+" |");
                    }else if(board.cell[i][j] instanceof Snake s){
                        System.out.printf(format,'_'+s.name()+'_');
                    }
            }
            System.out.println();
        }
        int widthtail = (163-20-player.getColor().length()-((int)Math.log10(player.getPosition())+1))/2;
        String Tbar = "|" + "-".repeat(widthtail) + "%s" + "-".repeat(widthtail) + "|";
        System.out.printf(Tbar,player.getColor() +" go to: possition = "+ player.getPosition());System.out.println();
    }

    public void printWinner(){
        String format = "|%-10s|";
        int i = 1;
        System.out.print("-".repeat(12));System.out.println();
        System.out.printf(format,"Winner");System.out.println();
        System.out.print("-".repeat(12));System.out.println();

        while(!winners.isEmpty()){
            System.out.printf(format,i++ +" "+winners.poll().getColor());System.out.println();
        }
        System.out.printf(format,i +" "+board.players.poll().getColor());System.out.println();

        System.out.print("-".repeat(12));System.out.println();
    }
}

