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
        
        setMen(board);
    }

    public void setMen() { // sets pieces in default positions
       for (int i = 0; i < 3; i++) {
           for(int j = 0; j < board[i].length; j++) {
               if ((i + j + 1) % 2 == 0) {
                   board[i][j] = new Man(false);
               }
           }
       }

       for (int i = 5; i < 8; i++) {
           for(int j = 0; j < board[i].length; j++) {
                if ((i + j + 1) % 2 == 0) {
                    board[i][j] = new Man(true);
                }
           }
       }
    }

    public void setMen(Man[][] board) { // sets pieces to the same positions the board passed in
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; i < board[i].length; j++) {
                if (board[i][j] != null) this.board[i][j] = new Man(board[i][j].isBlack);
                else this.board[i][j] = null;
            }
        }
    }

    public ArrayList<Board> getAllBoards() { // returns a list of all possible subsequent board states
        // creates and returns new ArrayList of CheckerBoards
        ArrayList<CheckerBoard> boards = new ArrayList<CheckerBoard>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; i++) {
                Man currentMan = board[i][j];
                if (currentMan != null) {
                    for (int z = 0; z < currentMan.directions.length; z++) {
                        if (currentMan.directions[z] != null) {
                            CheckerBoard result = this.move(currentMan.directions[z], j, i);
                            if (result != null) boards.add(result);
                        }
                    }
                }
            }
        }

    }

    public CheckerBoard move(int[] directions, int x, int y) { // used in getAllBoards; returns null if not valid
        // creates new checkerboard
    
    }

    public void makeMove(CheckerBoard nextBoard) { // actually makes the move on this. board
        board = nextBoard.getBoard();
    }


    public boolean isOver() { // checks if game is over
        int blackCount = 0;
        int whiteCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; i < board[i].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].isBlack) {
                        blackCount++;
                    } else {
                        whiteCount++;
                    }
                } 
            }
        }
        return blackCount == 0 || whiteCount == 0;
    }


    // getters and setters
    public Man[][] getBoard() {
        return this.board;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(1 + " ");
                }
            }
            System.out.println();
        }
    }

    public int evaluate() {
        int blackCount = 0;
        int whiteCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; i < board[i].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].isBlack) {
                        blackCount++;
                    } else {
                        whiteCount++;
                    }
                } 
            }
        }
        return blackCount - whiteCount;
    }


}
