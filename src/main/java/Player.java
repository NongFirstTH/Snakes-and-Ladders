public class Player {
    private int position;
    private int currentRowCell;
    private int currentColumnCell;
    private String color;
    private int boardSize;

    public Player(String color, int boardSize) {
        this.color = color;
        this.boardSize = boardSize;
    }

    public void setPosition(int position) {
        this.position = position;
        currentRowCell = calculateRowCell(position);
        currentColumnCell = calculateColumnCell(position);
    }

    public void move(Board board, int face) {
        setPosition(position + face);

        //TODO : ประกาศตัวแปรโดยให้ค่าตั้งต้นเป็น board.cells[currentRowCell][currentColumnCell];
        Object currentCell;

        if (board.cells[currentRowCell][currentColumnCell] == null) {
            currentCell = null;
        } else {
            currentCell = board.cells[currentRowCell][currentColumnCell];
        }

        if (isPlayerAtFinishPosition()) {
            int finishPosition = boardSize * boardSize;
            int positionToGo = finishPosition - (position - finishPosition);

            setPosition(positionToGo);
        } else if (currentCell == null) {
            return;
        } else if (currentCell instanceof Ladder ladder) {
            int tailPosition = calculatePosition(ladder.tail()[0], ladder.tail()[1]);

            setPosition(tailPosition);
        } else if (currentCell instanceof Snake snake) {
            int tailPosition = calculatePosition(snake.tail()[0], snake.tail()[1]);

            setPosition(tailPosition);
        }
    }

    private int calculateRowCell(int position) {
        int maxIndexOfBoard = boardSize - 1;
        int rowOfPositionFromLower = (position - 1) / boardSize;

        if (isPlayerAtFinishPosition()) {
            return 0;
        } else {
            return maxIndexOfBoard - rowOfPositionFromLower;
        }
    }

    private int calculateColumnCell(int position) {
        int maxIndexOfBoard = boardSize - 1;
        int unitDigitOfPosition = position % boardSize;

        if (isPlayerAtFinishPosition()) {
            if (boardSize % 2 == 0) {
                return 0;
            } else {
                return boardSize - 1;
            }
        } else if (isRowOfIndexOfPositionOdd()) {
            if (isPositionDividedByBoardsize()) {
                return 0;
            } else {
                return boardSize - unitDigitOfPosition;
            }
        } else {
            if (isFirstRow()) {
                return position - 1;
            } else {
                if (isPositionDividedByBoardsize()) {
                    return maxIndexOfBoard;
                } else {
                    return unitDigitOfPosition - 1;
                }
            }
        }
    }

    //TODO : colCell -> columnCell
    private int calculatePosition(int rowCell, int colCell) {
        int position = 1;
        int remainDigit = (boardSize - 1 - rowCell) * 10;

        if (rowCell % 2 == 0) {
            int unitDigit = boardSize - colCell;

            position = remainDigit + unitDigit;
        } else {
            int unitDigit = colCell + 1;

            position = remainDigit + unitDigit;
        }

        return position;
    }

    public int getPosition() {
        return position;
    }

    public int getCurrentRowCell() {
        return currentRowCell;
    }

    public int getCurrentColumnCell() {
        return currentColumnCell;
    }

    public String getColor() {
        return color;
    }

    public int rollDice(Dice dice) {
        return dice.face();
    }

    private Boolean isPlayerAtFinishPosition() {

        //TODO : คำนวณ boardSize * boardSize ก่อน แล้วค่อยเอามาใช้ 
        int finishPosition = boardSize * boardSize;

        return position >= finishPosition;
    }

    private Boolean isRowOfIndexOfPositionOdd() {
        return (position / boardSize) % 2 == 1;
    }

    private Boolean isPositionDividedByBoardsize() {
        return position % boardSize == 0;
    }

    private Boolean isFirstRow() {
        return currentRowCell == boardSize - 1;
    }
}