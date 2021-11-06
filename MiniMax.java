
public class MiniMax {
	
	chessBoard initial; //intial board
	int depth; //depth of the tree
	int turns; //number of turns
	int depthLimit = 6; //limit of the depth
	
	
	

	public int evaluateBoardMax(chessBoard initial, int depth) {
		chessBoard[] nextb = initial.getBoards();
		int max = -999;
		if (nextb == null || depth == depthLimit) {
			return initial.evaluateScore();
		}
		for(chessBoard b: nextb) {
			int holder = evaluateBoardMin(b, depth + 1);
			if (max <= holder) {
				max = holder;
			}
		}
		return max;
		
	}
	
	public int evaluateBoardMin(chessBoard initial, int depth) {
		chessBoard[] nextb = initial.getBoards();
		int min = 999;
		if (nextb == null || depth == depthLimit) {
			return initial.evaluateScore();
		}
		for(chessBoard b: nextb) {
			int holder = evaluateBoardMax(b, depth + 1);
			if (min >= holder) {
				min = holder;
			}
		}
		return min;
	}
	

}
