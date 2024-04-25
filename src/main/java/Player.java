public class Player {
    private int position;
    private int currentRowCell;
    private int currentColumnCell;
    private int boardSize;
    private int finishPosition;
    private String color;

    public Player(String color, int boardSize) {
        this.color = color;
        this.boardSize = boardSize;
        this.finishPosition = boardSize * boardSize;
    }

    public void setPosition(int position) {
        this.position = position;
        currentRowCell = calculateRowCell(position);
        currentColumnCell = calculateColumnCell(position);
    }

    public void move(Board board, int face) {
        setPosition(position + face);
        Object currentCell = board.cells[currentRowCell][currentColumnCell];

        if (isPlayerAtFinishPosition()) {
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
            if (isBoardSizeEven()) {
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
            if (isPositionEqualFirstRow()) {
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

    private int calculatePosition(int rowCell, int columnCell) {
        int position = 1;
        int remainDigitOfPosition = (boardSize - 1 - rowCell) * 10;

        if (isRowOfCellEven(rowCell)) {
            int unitDigitOfPosition = boardSize - columnCell;

            position = remainDigitOfPosition + unitDigitOfPosition;
        } else {
            int unitDigitOfPosition = columnCell + 1;

            position = remainDigitOfPosition + unitDigitOfPosition;
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

    private Boolean isRowOfCellEven(int rowCell) {
        return rowCell % 2 == 0;
    }

    private Boolean isBoardSizeEven() {
        return boardSize % 2 == 0;
    }

    private Boolean isPlayerAtFinishPosition() {
        return position >= finishPosition;
    }

    private Boolean isRowOfIndexOfPositionOdd() {
        return (position / boardSize) % 2 == 1;
    }

    private Boolean isPositionDividedByBoardsize() {
        return position % boardSize == 0;
    }

    private Boolean isPositionEqualFirstRow() {
        return currentRowCell == boardSize - 1;
    }
}