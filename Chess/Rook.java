import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import Chess.Piece;
import Chess.Square;

public class Rook implements Piece {
    private boolean isWhite;
    private Square[][] scope;

    public Rook(boolean isWhite, Square startingSquare) {
        this.isWhite = isWhite;
        this.scope = new Square[9][9];
        startingSquare.setPiece(this);
        this.updateScope(startingSquare, 4, 4);
    }

    @Override
    public int getType() {
        return 4;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public ArrayList<Square> getMoves() {
        return getMoves(4, 4, new ArrayList<Square>());
    }

    private void updateScope(Square currentSquare, int x, int y) {
        if (currentSquare == null) {
            return;
        } else if (x > 4) {
            updateScope(currentSquare.right, x + 1, y);
        } else if (x < 4) {
            updateScope(currentSquare.left, x - 1, y);
        } else if (y > 4) {
            updateScope(currentSquare.right, x, y + 1);
        } else if (y < 4) {
            updateScope(currentSquare.down, x, y - 1);
        }
        scope[y][x] = currentSquare;
    }

    public ArrayList<Square> getMoves(int x, int y, ArrayList<Square> squares) {
        if (scope[y][x] == null) {
            return squares;
        } else if (x > 4) {
            if (scope[y][x].isEmpty()) {
                getMoves(x + 1, y, squares);
            }
        } else if (x < 4) {
            if (scope[y][x].isEmpty()) {
                getMoves(x - 1, y, squares);
            }
        } else if (y > 4) {
            if (scope[y][x].isEmpty()) {
                getMoves(x, y + 1, squares);
            }
        } else if (y < 4) {
            if (scope[y][x].isEmpty()) {
                getMoves(x, y - 1, squares);
            }
        } else {
            getMoves(x + 1, y, squares);
            getMoves(x - 1, y, squares);
            getMoves(x, y + 1, squares);
            getMoves(x, y - 1, squares);
        }

        if (scope[y][x].isEmpty() | scope[y][x].piece().isWhite() != this.isWhite) {
            squares.add(scope[y][x]);
        }
        return squares;
    }

    public void makeMove(Square endSquare) {
        endSquare.setPiece(scope[4][4].piece());
        scope[4][4].setPiece(null);
        updateScope(endSquare, 4, 4);
    }
}