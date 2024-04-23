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

        currentRowCell = maxIndexOfBoard - rowOfPositionFromLower;
        
        if(isFinish()) {
            currentColCell = 0;
        } else if(isRowOfIndexOfPositionOdd()){
            if(isPositionDividedByBoardsize()){
                currentColCell = 0;
            }else{
                currentColCell = boardSize - unitDigitOfPosition;
            } 
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
        return currentColCell == boardSize-1;
    }
}
