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
        Man[][] currentBoard = board.clone(); // create a copy of current chess board
		
		Man man = currentBoard[i][j];
		
		int y;
		int x;
		
		if(man.isBlack()) {
			 y = directions[0];
			 x = directions[1];
		} else{
			 y = -directions[0];
			 x = -directions[1];
		}

		if (man.isBlack()) { //black checker (at top?)
			if (currentBoard[i + x][j + y] == null) {
				if (i + x >= 0 && i + x <= 7 && j + y >= 0 && j + y <= 7) {
					currentBoard[i][j] = null;
					currentBoard[i + x][j + y] = new Man(true); // move black checker
				}
			} else if (!currentBoard[i + x][j + y].isBlack()) {
				if (i + x + x >= 0 && i + x + x <= 7 && j + y + y >= 0 && j + y + y <= 7) {
					currentBoard[i][j] = null;
					currentBoard[i + x][j + y] = null; // kill the white checker
					currentBoard[i + x + x][j + y + y] = new Man(true); // move black checker
				}
			} else{
				//can't move, since another black checker is at the position.
			}
		} else { //white checker (at bottom?)
			if (currentBoard[i + x][j + y] == null) {
				if (i + x >= 0 && i + x <= 7 && j + y >= 0 && j + y <= 7) {
					currentBoard[i][j] = null;
					currentBoard[i + x][j + y] = new Man(false); // move white checker
				}
			} else if (currentBoard[i + x][j + y].isBlack()) {
				if (i + x + x >= 0 && i + x + x <= 7 && j + y + y >= 0 && j + y + y <= 7) {
					currentBoard[i][j] = null;
					currentBoard[i + x][j + y] = null; // kill the black checker
					currentBoard[i + x + x][j + y + y] = new Man(false); // move white checker
				}
			} else{
				//can't move, since another white checker is at the position.
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
