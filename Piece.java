
import java.util.ArrayList;

public class Piece {
    // members
    private boolean isBlack;
    private int type;
    private boolean hasMoved;
    private int[][] moves; // array of possible future moves, size based on piece type

    // constructors
    public Piece(boolean isBlack, int type) {
        this.isBlack = isBlack;
    }


    // methods 
    // getters
    public boolean isBlack() {
        return isBlack;
    }

    public int[][] getDirections() {
        return moves;
    }

    public int whatType() {
        return type;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    // setter

    // others

    // when a piece moves, this will be called to re-update the future moves array
    public void generatePossibleMoves(int x, int y) {
        // pass in current location => get moves based on rules of piece
    }

    // when an array of moves exist, eliminate non-legal moves 
    public void updatePossibleMoves(Board chessBoard, int x, int y) {
        // generate possible moves based on piece type & current position on board 
        // different rules based on chess piece type 

        // update the legal directions array
        for (list_x: possibleDirections[]) {
            for (next_y: list_x) {
                
                if (!isLegalMove(chessBoard, x, y, next_x, next_y)) {
                    // delete
                } 
            }
        }
    }

    // check if one move is legal or not
    public boolean isLegalMove(Board chessBoard, int curr_x, int curr_y, int next_x, int next_y) {
        boolean isLegal = false;
        // check if move is legal based on rules 

        return isLegal;
    }



}
