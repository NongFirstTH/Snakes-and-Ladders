import java.util.LinkedList;

public class Game {
    public Board board;

    public Game(Board board){
        this.board = board;
    }

    public void play(){
        Player p = board.players.peek();
        move(p,p.RollDice());
        turn();
    }

    private void move(Player p,int rollDice){
        
        //    if(board.getBoard()[row][col].equals(checkLadderHead(row,col))){
            
        //    }else if(board.getBoard()[row][col].equals(checkSnakeHead(row,col))){

        //    }else{
        //     p.position += rollDice;
        //    }
    }

    private void turn(){
        Player p = board.players.poll();
        board.players.add(p);
    }

    private Ladder checkLadderHead(int row , int col){
        for(Ladder ladder : board.ladders){
            if(board.getBoard()[row][col].equals(ladder)) return ladder;
        }
        return null;
    }
    
    private Snake checkSnakeHead(int row , int col){
        for(Snake snake : board.snakes){
            if(board.getBoard()[row][col].equals(snake)) return snake;
        }
        return null;
    }

    private void checkWinner(){

    }

    public void printBoard(){

    }

}

