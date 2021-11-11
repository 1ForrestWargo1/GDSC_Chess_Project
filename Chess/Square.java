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

}
