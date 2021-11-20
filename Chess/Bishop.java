package Chess;

import java.util.ArrayList;;

public class Bishop implements Piece {
    private boolean isWhite;
    private Square[][] scope;

    // constructor
    public Bishop(boolean isWhite, Square startingSquare) {
        this.isWhite = isWhite;
        this.scope = new Square[9][9];
        startingSquare.setPiece(this);
        this.updateScope(startingSquare, 4, 4);
    }

    // updates the scope of variable the square and x and y coordinates of the
    // piece as paraemters
    private void updateScope(Square startingSquare, int x, int y) { // each call represents the path the bishop can take
        if (startingSquare == null) { // if out of bounds of the board
            return;
        } else if (y < 4 && x > 4) { // if the path is up and to the right
            updateScope(startingSquare.right.up, x + 1, y - 1);
        } else if (y < 4 && x < 4) { // if the path is up and to the left
            updateScope(startingSquare.left.up, x - 1, y - 1);
        } else if (y > 4 && x > 4) { // if the path is down and to the right
            updateScope(startingSquare.right.down, x + 1, y + 1);
        } else if (y > 4 && x < 4) { // if the path is down and to the left
            updateScope(startingSquare.left.down, x - 1, y + 1);
        } else { // if the starting square is the piece's starting position
            updateScope(startingSquare.right.up, x + 1, y - 1); // call in directions
            updateScope(startingSquare.left.up, x - 1, y - 1);
            updateScope(startingSquare.right.down, x + 1, y + 1);
            updateScope(startingSquare.left.down, x - 1, y + 1);
        }
        scope[y][x] = startingSquare; // set the current square to the corresponding square on the board and scope
    }

    // Method returns an array of all the squares at which the piece can
    // potentially move. Uses the same recursive logic as update scope
    public ArrayList<Square> getMoves() {
        return getMoves(4, 4, new ArrayList<Square>());
    }

    public ArrayList<Square> getMoves(int x, int y, ArrayList<Square> squares) {
        if (scope[y][x] == null) {
            return squares;
        } else if (y < 4 && x > 4) {
            if (scope[y][x].isEmpty()) { // if there is not a piece at the current square which is "in the way"
                getMoves(x + 1, y - 1, squares);
            }
        } else if (y > 4 && x < 4) {
            if (scope[y][x].isEmpty()) {
                getMoves(x - 1, y - 1, squares);
            }
        } else if (y > 4 && x > 4) {
            if (scope[y][x].isEmpty()) {
                getMoves(x + 1, y + 1, squares);
            }
        } else if (y < 4 && x < 4) {
            if (scope[y][x].isEmpty()) {
                getMoves(x - 1, y + 1, squares);
            }
        } else {
            getMoves(x + 1, y - 1, squares);
            getMoves(x - 1, y - 1, squares);
            getMoves(x + 1, y + 1, squares);
            getMoves(x - 1, y + 1, squares);
        }
        if (scope[y][x].isEmpty() | scope[y][x].piece().isWhite() != this.isWhite) { // if the square does not contain
                                                                                     // an ally piece
            squares.add(scope[y][x]);
        }
        return squares;
    }

    // moves the piece from the starting square to end square
    // assumes move is valid
    public void makeMove(Square endSquare) {
        endSquare.setPiece(scope[4][4].piece());
        scope[4][4].setPiece(null);
        updateScope(endSquare, 4, 4);
    }

    // getters and setters
    public boolean isWhite() {
        return isWhite;
    }

    public int getType() {
        return 1;
    }

    public void printScope() {
        for (int i = 0; i < scope.length; i++) {
            for (int j = 0; j < scope[i].length; j++) {
                Square currentSquare = scope[i][j];
                if (currentSquare == null | currentSquare.isEmpty()) {
                    System.out.print("p ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

}
