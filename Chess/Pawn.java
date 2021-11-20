package Chess;

import java.util.ArrayList;

public class Pawn implements Piece {
    private boolean isWhite;
    private boolean hasMoved;
    private boolean hasJustMoved; // for en passant
    private Square[][] scope;

    // constructor
    public Pawn(boolean isWhite, Square startingSquare) {
        this.isWhite = isWhite;
        this.hasMoved = false;
        this.hasJustMoved = false;
        this.scope = new Square[3][3];
        startingSquare.setPiece(this);
        this.updateScope(startingSquare, 1, 2);
    }

    private void updateScope(Square startingSquare, int x, int y) {

        if (startingSquare != null) {
            if (startingSquare.piece().equals(this)) {
                scope[y][x] = startingSquare;
                scope[y][x - 1] = startingSquare.getLeft(isWhite);
                scope[y][x + 1] = startingSquare.getRight(isWhite);
                if (y + 1 <= 2) {
                    this.updateScope(startingSquare.up, x, y + 1);
                }
            }
        }
    }

    public ArrayList<Square> getMoves() { // returns squares piece can move to (including illegal moves)
        ArrayList<Square> squares = new ArrayList<Square>();

        if (!scope[1][0].isEmpty() && scope[1][0].piece().isWhite() != this.isWhite) { // if pawn can capture a piece
            squares.add(scope[1][0]);
        } else if (!scope[2][0].isEmpty() && scope[2][0].piece().getType() == 1
                && ((Pawn) scope[2][0].piece()).hasJustMoved()) { // if en passant is valid
            squares.add(scope[1][0]);
        }

        if (!scope[1][2].isEmpty() && scope[1][2].piece().isWhite() != this.isWhite) { // same logic as above
            squares.add(scope[1][2]);
        } else if (!scope[2][2].isEmpty() && scope[2][2].piece().getType() == 1
                && ((Pawn) scope[2][2].piece()).hasJustMoved()) {
            squares.add(scope[1][2]);
        }

        if (scope[1][1].isEmpty()) { // if the space in front of pawn is empty
            squares.add(scope[1][1]); // add the move
            if (!hasMoved && scope[0][2].isEmpty()) { // if the pawn has not moved and the space two squares in front is
                                                      // open
                squares.add(scope[0][1]);
            }
        }
        return squares;
    }

    // moves the piece to the square in the parameter (assume move is valid)
    public void makeMove(Square move) {
        move.setPiece(this);
        scope[2][1].setPiece(null);
        if (!move.down.isEmpty()) {
            move.down.setPiece(null);
        }
        updateScope(move, 1, 2);
    }

    // getters and setters
    public boolean isWhite() {
        return isWhite;
    }

    public int getType() {
        return 1;
    }

    public boolean hasJustMoved() {
        return hasJustMoved;
    }

}
