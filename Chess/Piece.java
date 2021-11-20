package Chess;

import java.util.ArrayList;;

public interface Piece {

    public int getType();

    public boolean isWhite();

    public ArrayList<Square> getMoves();

}
