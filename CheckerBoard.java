import java.util.ArrayList;

public class CheckerBoard implements Board {
    private Men[][] board;
    private Boolean blackTurn;
    private int moveCount;

    public CheckerBoard() {
        board = new Men[8][8];
        blackTurn = true;
        moveCount = 0;
        setMen();
    }

    public CheckerBoard(Boolean turn, int moveCount) {
        board = new Men[8][8];
        blackTurn = turn;
        this.moveCount = moveCount;
        setMen();
    }

    public CheckerBoard(Men[][] board, Boolean turn, int moveCount) {
        board = new Men[8][8];
        blackTurn = turn;
        this.moveCount = moveCount;
        
        setMen();
    }

    public void setMen() { // sets pieces in default positions
       
    }

    public void setMen(Men[][] board) { // sets pieces to the same positions the board passed in

    }

    public ArrayList<CheckerBoard> getAllBoards() { // returns a list of all possible subsequent board states
        // creates and returns new ArrayList of CheckerBoards
    }

    public CheckerBoard move(String direction) { // used in getAllBoards; returns null if not valid
        // creates new checkerboard
    }

    public void makeMove(Board nextBoard) { // actually makes the move on this. board
        board = nextBoard.getBoard();
    }


    public Boolean isOver() { // checks if game is over

    }

    // getters and setters


}
