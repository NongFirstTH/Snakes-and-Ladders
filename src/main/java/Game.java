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
        Player p = board.players.peek();
        int roll = p.RollDice();
        System.out.println(p.color + " roll " + roll);
        printBoard();
        move(p,roll);
        System.out.println(p.color +" pos = "+ p.position +" r:"+ p.currow+" c:" + p.curcol);
        checkWinner();
        turn();
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
                p.setPosition(calPosition(row, col), n);
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

    public void printBoard(){
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                boolean hasPlayer = false;
                    for(Player p : board.players){
                        if(i == p.currow && j == p.curcol){
                            System.out.printf("%-10s",p.color);
                            hasPlayer = true;
                        }
                    }
                if(!hasPlayer) {
                    if(board.getBoard()[i][j] == null) System.out.printf("%-10s",calPosition(i,j));
                }
                    if(board.getBoard()[i][j] instanceof Ladder l){
                        System.out.printf("%-10s",l.name());
                    }else if(board.getBoard()[i][j] instanceof Snake s){
                        System.out.printf("%-10s",s.name());
                    }
            }
            System.out.println();
        }
    }

}

