package Chess;

import java.util.ArrayList;;

public interface Piece {

    public int getType();

    public boolean isWhite();

    public ArrayList<Square> getMoves();

    public void makeMove(Square endSquare);

    // To Do: refactor pieces and board to include this method
    // public Piece createCopy(int x, int y, ChessBoard);

}
