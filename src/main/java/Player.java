import java.util.Random;

public class Player{
    private int position;
    private int currentRowCell;
    private int currentColCell;
    private String color;
    private int boardSize;

    public Player(String color,int boardSize){
        this.color = color;
        this.boardSize = boardSize;
    }

    public void setPosition(int position,int boardSize){
        this.position = position;
        int maxIndexOfBoard = boardSize-1;
        int rowOfPositionFromLower = (position-1)/boardSize;
        int unitDigitOfPosition = position%boardSize;

        if(isFinish()) currentRowCell = 0;
        else currentRowCell = maxIndexOfBoard - rowOfPositionFromLower;
        
        if(isFinish()) {
            currentColCell = 0;
        } else if(isRowOfIndexOfPositionOdd()){
            if(isPositionDividedByBoardsize()) currentColCell = 0;
            else currentColCell = boardSize - unitDigitOfPosition;
        }else{
            if(isFirstRow()){
                currentColCell = position-1;
            }else{
                if(isPositionDividedByBoardsize()){
                    currentColCell = maxIndexOfBoard;
                }else{
                    currentColCell = unitDigitOfPosition-1;
                } 
            }
        }
    }

    public void move(Board board,int rollDice){
        setPosition(position + rollDice,boardSize);
        Object currentCell;

        if(board.cell[currentRowCell][currentColCell] == null){
            currentCell = null;
        }else{
            currentCell = board.cell[currentRowCell][currentColCell];
        }

        if(isFinish()){
            int finishPosition = boardSize*boardSize;
            int reversePosition = finishPosition-(position - finishPosition);

            setPosition(reversePosition,boardSize);
        }else if(currentCell == null){
            return ;
        }else if(currentCell instanceof Ladder ladder){
            int tailPosition = calPosition(ladder.tail()[0], ladder.tail()[1]);
            
            setPosition(tailPosition, boardSize);
        }else if(currentCell instanceof Snake snake){
            int tailPosition = calPosition(snake.tail()[0], snake.tail()[1]);
            
            setPosition(tailPosition, boardSize);
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

    public int getPosition(){
        return position;
    }

    public int getCurrentRowCell(){
        return currentRowCell;
    }

    public int getCurrentColCell(){
        return currentColCell;
    }

    public String getColor(){
        return color;
    }
    
    public int rollDice(){
        Random randomNumber = new Random();

        return randomNumber.nextInt(6)+1;
    }

    private Boolean isFinish(){
        int finishPosition = boardSize*boardSize;

        return position >= finishPosition;
    } 

    private Boolean isRowOfIndexOfPositionOdd(){
        return (position / boardSize)%2 == 1;
    }

    private Boolean isPositionDividedByBoardsize(){
        return position%boardSize==0;
    }

    private Boolean isFirstRow(){
        return currentRowCell == boardSize-1;
    }

}
