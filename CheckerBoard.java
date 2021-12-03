import java.util.ArrayList;
import java.util.Scanner;

public class CheckerBoard implements Board {
    private Man[][] board; // 2D array of board
    private boolean blackTurn;
    private int moveCount;
    MiniMax miniMax = new MiniMax();

    public CheckerBoard() { // default constructor
        board = new Man[8][8];
        blackTurn = true;
        moveCount = 0;
        setMen();
    }

    public CheckerBoard(Man[][] board, Boolean turn, int moveCount) { // constructor which makes a new board from a
                                                                      // pre-existing board
        this.board = new Man[8][8];
        // if (board == null) System.out.println("board is orignally null");
        blackTurn = turn;
        this.moveCount = moveCount;

        setMen(board);
        // if (board == null) System.out.println("board is orignally null");
    }

    public void setMen() { // sets pieces in default positions
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j + 1) % 2 == 0) {
                    board[i][j] = new Man(false);
                }
            }
        }

        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((i + j + 1) % 2 == 0) {
                    board[i][j] = new Man(true); // bottom side pieces are black and go first
                }
            }
        }
    }

    public void setMen(Man[][] board) { // sets pieces to the same positions the board passed in
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != null)
                    this.board[i][j] = new Man(board[i][j].isBlack);
                // else this.board[i][j] = null;
            }
        }
    }

    public ArrayList<Board> getAllBoards() { // returns a list of all possible subsequent board states
        // creates and returns new ArrayList of CheckerBoards
        ArrayList<Board> boards = new ArrayList<Board>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Man currentMan = board[j][i]; // pointer to coordinate
                if (currentMan != null && currentMan.isBlack == blackTurn) { // if piece exists at coordinate and the
                                                                             // piece is for the correct turn
                    for (int z = 0; z < currentMan.directions.length; z++) {
                        if (currentMan.directions[z] != null) { // for every element in the direction array
                            Board result = (Board) this.move(currentMan.directions[z], j, i); // simulate the move
                            if (result != null)
                                boards.add(result); // add it to the array
                        }
                    }
                }
            }
        }

        return boards;

    }

    // public CheckerBoard movev2(int[] directions, int i, int j) {

    // }

    public CheckerBoard move(int[] directions, int i, int j) { // used in getAllBoards and returns the board result from
                                                               // the "move" inputted; returns null if not valid
        CheckerBoard result = new CheckerBoard(this.board, !this.blackTurn, moveCount + 1);
        Man[][] currentBoard = result.board; // create a copy of current chess board
        // if (result.board == null) System.out.println("constructor failed");
        Man man = currentBoard[i][j];

        int y;
        int x;

        if (man.isBlack()) {
            y = directions[0];
            x = directions[1];
        } else {
            y = -directions[0];
            x = -directions[1];
        }

        if (!(i + y >= 0 && i + y <= 7 && j + x >= 0 && j + x <= 7)) {
            return null;
        }

        if (!man.isBlack()) { // white checker (at top)
            if (currentBoard[i + y][j + x] == null) {
                currentBoard[i + y][j + x] = currentBoard[i][j]; // move white checker
                currentBoard[i][j] = null;
            } else if (currentBoard[i + y][j + x].isBlack()) {
                if (i + y + y >= 0 && i + y + y <= 7 && j + x + x >= 0 && j + x + x <= 7
                        && currentBoard[i + y + y][j + x + x] == null) {
                    currentBoard[i + y][j + x] = null; // kill the black checker
                    currentBoard[i + y + y][j + x + x] = currentBoard[i][j]; // move white checker
                    currentBoard[i][j] = null;
                }
            } else {
                // System.out.println("move is inavlid");
                return null;
            }
        } else { // black checker (at bottom)
            if (currentBoard[i + y][j + x] == null) {
                currentBoard[i + y][j + x] = currentBoard[i][j]; // move black checker
                currentBoard[i][j] = null;
            } else if (!currentBoard[i + y][j + x].isBlack()) {
                if (i + y + y >= 0 && i + y + y <= 7 && j + x + x >= 0 && j + x + x <= 7
                        && currentBoard[i + y + y][j + x + x] == null) {
                    currentBoard[i + y][j + x] = null; // kill the white checker
                    currentBoard[i + y + y][j + x + x] = currentBoard[i][j];
                    currentBoard[i][j] = null; // move black checker
                }
            } else {
                // System.out.println("move is inavlid");
                return null;
            }
        }
        return result;
    }

    public void makeMove(CheckerBoard nextBoard) { // actually makes the move on this. board
        board = nextBoard.getBoard();
        blackTurn = !blackTurn;
        moveCount++;
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
    public Man[][] getBoard() { // returns current board state
        return this.board;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Man currentPiece = board[i][j];
                if (currentPiece == null) {
                    System.out.print(0 + " ");
                } else {
                    if (currentPiece.isBlack) {
                        System.out.print(1 + " ");
                    } else {
                        System.out.print(2 + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Man currentPiece = board[i][j];
                if (currentPiece == null) {
                    result += 0 + " ";
                } else {
                    if (currentPiece.isBlack) {
                        result += 1 + " ";
                    } else {
                        result += 2 + " ";
                    }
                }
            }
        }
        return result;
    }

    public int evaluateBoard() { // evaluates the board for the minimax algorithm
        int blackCount = 0;
        int redCount = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; i < board[i].length; j++) {
                if (board[i][j] != null) {
                    if (board[i][j].isBlack) {
                        blackCount++;
                    } else {
                        redCount++;
                    }
                }
            }
        }
        if (blackCount == 0)
            return -13; // if the game is won return 13 or -13
        else if (redCount == 0)
            return 13;
        return blackCount - redCount; // otherwise return the difference in the number of pieces
    }

    public void runGame() {
        Scanner scn = new Scanner(System.in);
        String answer = "yes";

        int choiceIndex = 0;

        while (answer.equals("yes")) {
            printBoard();
            System.out.println();

            ArrayList<Board> allPossibleNext = getAllBoards();
            System.out.println("All possible boards: ");
            for (int i = 0; i < allPossibleNext.size(); i++) {
                CheckerBoard current = (CheckerBoard) (allPossibleNext.get(i));
                System.out.println(i + ":");
                current.printBoard();
                System.out.println();
            }
            System.out.print("Choose next board");
            choiceIndex = scn.nextInt();
            System.out.println();
            makeMove((CheckerBoard) (allPossibleNext.get(choiceIndex)));
            System.out.println("New board: ");
            printBoard();
            System.out.println();

            if (this.isOver()) {
                if (evaluateBoard() > 0) {
                    System.out.println("Red won");
                    break;
                } else {
                    System.out.println("White won");
                    break;
                }
            }

            ArrayList<Board> miniMaxNext = getAllBoards();
            int highestEvaluation = Integer.MAX_VALUE;
            Board bestNextBoard = null;
            for (Board board : miniMaxNext) {
                if (miniMax.evaluateBoardMax(board, 6) < highestEvaluation) {
                    bestNextBoard = board;
                }
            }
            this.makeMove((CheckerBoard) bestNextBoard);
            if (this.isOver()) {
                if (evaluateBoard() > 0) {
                    System.out.println("Red won");
                    break;
                } else {
                    System.out.println("White won");
                    break;
                }
            }

            // System.out.print("Would you like to make another move? (enter yes to
            // continue, no to terminate program) ");
            // answer = scn.nextLine();

        }
        System.out.println("program ended");
        scn.close();
    }

    public static void main(String args[]) {
        CheckerBoard board = new CheckerBoard();
        board.runGame();

    }

}
