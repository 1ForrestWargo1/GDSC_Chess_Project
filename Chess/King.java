package Chess;

import java.util.ArrayList;

public class King implements Piece {
    private boolean isWhite;
    private Square[][] scope;

    public King(boolean isWhite, Square startingSquare) {
        scope = new Square[3][3];
    }

    public void updateScope(Square startingSquare) {
        startingSquare.setPiece(this);
        scope[1][1] = startingSquare;
        scope[1][2] = scope[1][1].right;
        scope[1][0] = scope[1][1].left;

        scope[0][1] = startingSquare.up;
        scope[0][0] = scope[0][1].left;
        scope[0][2] = scope[0][1].right;

        scope[2][1] = startingSquare.up;
        scope[2][0] = scope[2][1].left;
        scope[2][2] = scope[2][1].right;
    }

    public ArrayList<Square> getMoves() {
        return getMoves(1, 1, new ArrayList<Square>());
    }

    public ArrayList<Square> getMoves(int x, int y, ArrayList<Square> squares) {

        return squares;
    }

    public void makeMove() {

    }

    public int getType() {
        return 100;
    }

    public boolean isWhite() {
        return isWhite;
    }

}
