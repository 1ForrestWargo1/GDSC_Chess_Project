package Chess;

public class Square {
    public Square up;
    public Square down;
    public Square left;
    public Square right;
    private Piece piece;

    public Square(Square up, Square down, Square left, Square right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece piece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    // Used by the pawns to return the direction of the method relative to the pawn.
    // Relative reffering to the directions being reversed for black pieces
    public Square getRight(boolean isWhite) {
        if (isWhite) {
            return right;
        } else {
            return left;
        }
    }

    public Square getLeft(boolean isWhite) {
        if (isWhite) {
            return left;
        } else {
            return right;
        }
    }

    public Square getUp(boolean isWhite) {
        if (isWhite) {
            return up;
        } else {
            return down;
        }
    }

    public Square getDown(boolean isWhite) {
        if (isWhite) {
            return down;
        } else {
            return up;
        }
    }

}
