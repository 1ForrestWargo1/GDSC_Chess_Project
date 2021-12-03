import java.util.ArrayList;

public class MiniMax {

	Board initial; // intial board
	int depth; // depth of the tree
	int turns; // number of turns
	int depthLimit = 4; // limit of the depth

	public int evaluateBoardMax(Board initial, int depth) {
		ArrayList<Board> nextb = initial.getAllBoards();
		int max = -999;
		if (nextb == null || depth >= depthLimit) {
			return initial.evaluateBoard();
		}
		for (Board b : nextb) {
			int holder = evaluateBoardMin(b, depth + 1);
			if (max <= holder) {
				max = holder;
			}
		}
		return max;

	}

	public int evaluateBoardMin(Board initial, int depth) {
		ArrayList<Board> nextb = initial.getAllBoards();
		int min = 999;
		if (nextb == null || depth >= depthLimit) {
			return initial.evaluateBoard();
		}
		for (Board b : nextb) {
			int holder = evaluateBoardMax(b, depth + 1);
			if (min >= holder) {
				min = holder;
			}
		}
		return min;
	}

}
