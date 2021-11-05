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
        ArrayList<Board> boards = new ArrayList<Board>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; i++) {
                Man currentMan = board[i][j];
                if (currentMan != null) {
                    for (int z = 0; z < currentMan.directions.length; z++) {
                        if (currentMan.directions[z] != null) {
                            Board result = (Board) this.move(currentMan.directions[z], j, i);
                            if (result != null) boards.add(result);
                        }
                    }
                }
            }
        }

        return boards;

    }

    public CheckerBoard move(int[] directions, int i, int j) { // used in getAllBoards; returns null if not valid
        Man[][] currentBoard = board.clone(); // create a copy of current chess board
		
		Man man = currentBoard[i][j];
		
		int y;
		int x;
		
		if(man.isBlack()) {
			 x = directions[0];
			 y = directions[1];
		} else{
			 x = -directions[0];
			 y = -directions[1];
		}

	    	if (i + y >= 0 && i + y <= 7 && j + x >= 0 && j + x <= 7) {
			return null;
		}
	    
		if (!man.isBlack()) { //white checker (at top)
			if (currentBoard[i + y][j + x] == null) {
					currentBoard[i][j] = null;
					currentBoard[i + y][j + x] = new Man(false); // move white checker
			} else if (currentBoard[i + y][j + x].isBlack()) {
				if (i + y + y >= 0 && i + y + y <= 7 && j + x + x >= 0 && j + x + x <= 7) {
					currentBoard[i][j] = null;
					currentBoard[i + y][j + x] = null; // kill the black checker
					currentBoard[i + y + y][j + x + x] = new Man(false); // move white checker
				}
			} else{
				return null;
			}
		} else { //black checker (at bottom)
			if (currentBoard[i + y][j + x] == null) {
					currentBoard[i][j] = null;
					currentBoard[i + y][j + x] = new Man(true); // move black checker
			} else if (currentBoard[i + y][j + x].isBlack()) {
				if (i + y + y >= 0 && i + y + y <= 7 && j + x + x >= 0 && j + x + x <= 7) {
					currentBoard[i][j] = null;
					currentBoard[i + y][j + x] = null; // kill the white checker
					currentBoard[i + y + y][j + x + x] = new Man(true); // move black checker
				}
			} else{
				return null;
			}
		}
		return new CheckerBoard(currentBoard, (blackTurn) ? false:true, moveCount+1);
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
