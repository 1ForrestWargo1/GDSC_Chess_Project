package Chess;

import java.util.ArrayList;

public class King implements Piece {
    private boolean hasMoved;
    private boolean isWhite;
    private Square[][] scope;

    public King(boolean isWhite, Square startingSquare) {
        this.hasMoved = false;
        scope = new Square[3][3];

    }

    private void updateScope(Square startingSquare) {
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

    public ArrayList<Square> getMoves() { // can return moves which put king in check
        ArrayList<Square> squares = new ArrayList<Square>();
        for (int i = 0; i < scope.length; i++) {
            for (int j = 0; j < scope[i].length; j++) {
                Square currentSquare = scope[i][j];
                if (currentSquare.isEmpty() | currentSquare.piece().isWhite() != isWhite) {
                    squares.add(currentSquare);
                }
            }
        }
        if (scope[1][2] != null && scope[1][2].isEmpty() && scope[1][2].right != null && scope[1][2].right.isEmpty()) {
            if (!this.hasMoved && scope[1][2].right.right != null && scope[1][2].right.right.piece().getType() == 5
                    && scope[1][2].right.right.piece().isWhite() == isWhite
                    && !((Rook) scope[1][2].right.right.piece()).hasMoved()) {
                squares.add(scope[1][2].right.right);
            }
        }
        return squares;
    }

    public void makeMove(Square endSquare) { // assumes move is valid
        if (!endSquare.isEmpty() && endSquare.piece().getType() == 5) {
            endSquare.left.setPiece(this);
            endSquare.left.left.setPiece(endSquare.piece());
            scope[1][1].setPiece(null);
            endSquare.setPiece(null);
            updateScope(endSquare.right);
            return;
        }

        endSquare.setPiece(this);
        scope[1][1].setPiece(null);
        updateScope(endSquare);
    }

    public int getType() {
        return 100;
    }

    public boolean isWhite() {
        return isWhite;
    }

}
