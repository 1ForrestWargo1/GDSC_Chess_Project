import java.util.ArrayList;

public class CheckerBoard implements Board {
    private Man[][] board;
    private boolean blackTurn;
    private int moveCount;

    public CheckerBoard() {
        board = new Man[8][8];
        blackTurn = true;
        moveCount = 0;
        setMen();
    }

    public CheckerBoard(Boolean turn, int moveCount) {
        board = new Man[8][8];
        blackTurn = turn;
        this.moveCount = moveCount;
        setMen();
    }

    public CheckerBoard(Man[][] board, Boolean turn, int moveCount) {
        board = new Man[8][8];
        blackTurn = turn;
        this.moveCount = moveCount;
        
        setMen();
    }

    public void setMen() { // sets pieces in default positions
       
    }

    public void setMen(Man[][] board) { // sets pieces to the same positions the board passed in

    }

    public ArrayList<Board> getAllBoards() { // returns a list of all possible subsequent board states
        // creates and returns new ArrayList of CheckerBoards
        ArrayList<CheckerBoard> boards = new ArrayList<CheckerBoard>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; i++) {
                if (board[i][j] != null) {
                    CheckerBoard case1 = move(true);
                    CheckerBoard case2 = move(false);

                    if (case1 != null) boards.add(case1);
                    if (case2 != null) boards.add(case2);
                }
            }
        }

    }

    public CheckerBoard move(boolean toRight) { // used in getAllBoards; returns null if not valid
        // creates new checkerboard
    
    }

    public void makeMove(Board nextBoard) { // actually makes the move on this. board
        board = nextBoard.getBoard();
    }


    public boolean isOver() { // checks if game is over

    }

    // getters and setters


}
