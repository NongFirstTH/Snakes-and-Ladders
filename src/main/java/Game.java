public class Game {
    public Board board;
    private int n;

    public Game(Board board){
        this.board = board;
        n = board.getBoard().length;
    }

    public void play(){
        Player p = board.players.peek();
        move(p,p.RollDice());
        turn();
    }

    public void move(Player p,int rollDice){
        p.setPosition(p.position + rollDice,n);
        int row = p.currow;
        int col = p.curcol;

            if(board.getBoard()[row][col] == null){
                return ;
            }else if(board.getBoard()[row][col].equals(checkLadderHead(row,col))){
                Ladder l = (Ladder) board.getBoard()[row][col];
                p.setPosition(l.tail()[1]+n-l.tail()[0]+p.position,n);
            }else if(board.getBoard()[row][col].equals(checkSnakeHead(row,col))){
                Snake s = (Snake) board.getBoard()[row][col];
                p.setPosition(s.tail()[1]+n-s.tail()[0],n);
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

    }

    public void printBoard(){

    }

}

