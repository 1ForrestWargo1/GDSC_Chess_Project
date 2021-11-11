package Chess;

import java.util.ArrayList;

public class Pawn implements Piece {
    private boolean isWhite;
    private boolean hasMoved;
    private boolean hasJustMoved; // for en passant
    private int type;
    private Square[][] scope;
    private ArrayList<Square> moves;

    // constructor
    public Pawn(boolean isWhite, Square startingSquare) {
        this.isWhite = isWhite;
        this.type = 1;
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
                scope[y][x - 1] = startingSquare.left;
                scope[y][x + 1] = startingSquare.right;
                if (y + 1 <= 2) {
                    this.updateScope(startingSquare.up, x, y + 1);
                }
            } else {

            }
        }
    }

    private ArrayList<Square> getMoves() {
        ArrayList<Square> squares = new ArrayList<Square>();
        for (int i = 0; i < scope.length; i++) {
            for (int j = 0; j < scope[i].length; j++) {
                Square current = scope[i][j];
                if (current != null && !(i == 2 && j == 0) && !(i == 2 && j == 2)) {
                    squares.add(current);
                }
            }
        }

        return squares;
    }

    public void makeMove(Square move) {
        scope[2][1] = null;
        move.setPiece(this);
        if (move.down.piece() != null && !move.down.piece().equals(this)) {
            move.down.setPiece(null);
        }
        updateScope(move, 1, 2);
    }

    // getters and setters
    public boolean isWhite() {
        return isWhite;
    }

    public int type() {
        return type;
    }

}
