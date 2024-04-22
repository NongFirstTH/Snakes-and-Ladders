import java.util.LinkedList;
import java.util.Queue;

public class Game {
    public Board board;
    public Queue<Player> winner = new LinkedList<>();
    private int n;

    public Game(Board board){
        this.board = board;
        n = board.getBoard().length;
    }

    public void play(){
        while(board.players.size()!=1) {
            Player p = board.players.peek();
            int roll = p.RollDice();
            move(p, roll);
            printBoard(p, roll);
            checkWinner();
            turn();
        }
        printWinner();
    }

    private int calPosition(int row,int col){
        int position = 1;
        if(row%2 == 0) position = (n-1-row)*10 + n-col;
        else position = (n-1-row)*10 + col+1;

        return position;
    }
    public void move(Player p,int rollDice){
        p.setPosition(p.position + rollDice,n);
        int row = p.currow;
        int col = p.curcol;

            if(p.position > n*n){
                int finish = n*n;
                p.setPosition(finish-(p.position - finish),n);
            }else if(board.getBoard()[row][col] == null){
                return ;
            }else if(board.getBoard()[row][col].equals(checkLadderHead(row,col))){
                Ladder l = (Ladder) board.getBoard()[row][col];
                p.setPosition(calPosition(l.tail()[0], l.tail()[1]), n);
            }else if(board.getBoard()[row][col].equals(checkSnakeHead(row,col))){
                Snake s = (Snake) board.getBoard()[row][col];
                p.setPosition(calPosition(s.tail()[0], s.tail()[1]), n);
            }
    }

    public void turn(){
        Player p = board.players.poll();
        board.players.add(p);
    }

    public Ladder checkLadderHead(int row , int col){
        if(board.getBoard()[row][col] == null) return null;
        for(Ladder ladder : board.ladders){
            if(board.getBoard()[row][col].equals(ladder) && row == ladder.head()[0] &&  col == ladder.head()[1] ) return ladder;
        }
        return null;
    }
    
    public Snake checkSnakeHead(int row , int col){
        if(board.getBoard()[row][col] == null) return null;
        for(Snake snake : board.snakes){
            if(board.getBoard()[row][col].equals(snake)  && row == snake.head()[0] &&  col == snake.head()[1] ) return snake;
        }
        return null;
    }

    public void checkWinner(){
        int finish = n*n;
        for(Player p : board.players){
            if(p.position == finish){
                winner.add(p);
                board.players.remove(p);
                return;
            }
        }
    }

    public void printBoard(Player player,int roll){
        int widthhead = (163-14-player.color.length()-((int)Math.log10(player.position)+1))/2;
        String Hbar = "|" + "-".repeat(widthhead) + "%s" + "-".repeat(widthhead) + "|";
        System.out.printf(Hbar,player.color + " turn: roll = " + roll);System.out.println();

        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                StringBuilder Stringplayer = new StringBuilder();
                boolean hasPlayer = false;
                    for(Player p : board.players){
                        if(i == p.currow && j == p.curcol){
                            Stringplayer.append(p.color).append(" ");
                            hasPlayer = true;
                        }
                    }

                    String format = "%" + (-18) + "s";
                if(!hasPlayer) {
                    if(board.getBoard()[i][j] == null) System.out.printf(format,calPosition(i,j));
                }else{
                    System.out.printf(String.format(format,Stringplayer.toString()));
                }

                    if(board.getBoard()[i][j] instanceof Ladder l){
                        System.out.printf(format,"| "+l.name()+" |");
                    }else if(board.getBoard()[i][j] instanceof Snake s){
                        System.out.printf(format,'_'+s.name()+'_');
                    }
            }
            System.out.println();
        }
        int widthtail = (163-20-player.color.length()-((int)Math.log10(player.position)+1))/2;
        String Tbar = "|" + "-".repeat(widthtail) + "%s" + "-".repeat(widthtail) + "|";
        System.out.printf(Tbar,player.color +" go to: possition = "+ player.position);System.out.println();
    }

    public void printWinner(){
        String format = "|%-10s|";
        int i = 1;
        System.out.print("-".repeat(12));System.out.println();
        System.out.printf(format,"Winner");System.out.println();
        System.out.print("-".repeat(12));System.out.println();

        while(!winner.isEmpty()){
            System.out.printf(format,i++ +" "+winner.poll().color);System.out.println();
        }
        System.out.printf(format,i +" "+board.players.poll().color);System.out.println();

        System.out.print("-".repeat(12));System.out.println();
    }
}

