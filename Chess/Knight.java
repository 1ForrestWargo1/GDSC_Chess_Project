package Chess;

import java.util.ArrayList;

public class Knight implements Piece{
	private boolean isWhite;
	private Square[][] scope;

	// constructor
	public Knight(boolean isWhite, Square startingSquare) {
		this.isWhite = isWhite;
		this.scope = new Square[5][5];
		startingSquare.setPiece(this);
		this.updateScope(startingSquare, 2, 2);
	}

	// updates the scope of variable the square and x and y coordinates of the
	// piece as paraemters
	private void updateScope(Square startingSquare, int x, int y) { // each call represents the path the Queen can take
		if (startingSquare == null) { // if out of bounds of the board
			return;
		} else { // if the starting square is the piece's starting position
			updateScope(startingSquare.right.right.up, x + 2, y - 1);
			updateScope(startingSquare.right.up.up, x + 1, y - 2);
			updateScope(startingSquare.left.left.up, x - 2, y - 1);
			updateScope(startingSquare.left.up.up, x - 1, y - 2);
			updateScope(startingSquare.right.right.down, x + 2, y + 1);
			updateScope(startingSquare.right.down.down, x + 1, y + 2);
			updateScope(startingSquare.left.left.down, x - 2, y - 1);
			updateScope(startingSquare.left.down.down, x - 1, y - 2);
		}
		scope[y][x] = startingSquare; // set the current square to the corresponding square on the board and scope
	}

	// Method returns an array of all the squares at which the piece can
	// potentially move. Uses the same recursive logic as update scope
	public ArrayList<Square> getMoves() { // method arranged as such to comply with interface
		return getMoves(2, 2, new ArrayList<Square>()); // returns squares piece can move to (including illegal moves)
	}

	// Method returns an array of all the squares at which the piece can
	// potentially move. Uses the same recursive logic as update scope
	private ArrayList<Square> getMoves(int x, int y, ArrayList<Square> squares) {
		if (scope[y][x] == null) {
			return squares;
		} else {
			getMoves(x + 2, y - 1, squares);
			getMoves(x + 1, y - 2, squares);
			getMoves(x - 2, y - 1, squares);
			getMoves(x - 1, y - 2, squares);
			getMoves(x + 2, y + 1, squares);
			getMoves(x + 1, y + 2, squares);
			getMoves(x - 2, y + 1, squares);
			getMoves(x - 1, y + 2, squares);
		}
		if (scope[y][x].isEmpty() | scope[y][x].piece().isWhite() != this.isWhite) { // if the square does not
																						// contain an ally piece
			squares.add(scope[y][x]);
		}
		return squares;
	}

	// moves the piece from the starting square to end square
	// assumes move is valid
	public void makeMove(Square endSquare) {
		endSquare.setPiece(scope[2][2].piece());
		scope[2][2].setPiece(null);
		updateScope(endSquare, 2, 2); // or getMoves(2, 2, endSquare)
	}

	// getters and setters
	public boolean isWhite() {
		return isWhite;
	}

	public int getType() {
		// FIXME
		return 3;
	}

	// public Piece createCopy() {

	// }

	public void printScope() {
		for (int i = 0; i < scope.length; i++) {
			for (int j = 0; j < scope[i].length; j++) {
				Square currentSquare = scope[i][j];
				if (currentSquare == null | currentSquare.isEmpty()) {
					System.out.print("p ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}
}
