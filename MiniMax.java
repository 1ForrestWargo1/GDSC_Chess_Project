
public class MiniMax {
	
	chessBoard initial; //intial board
	int depth; //depth of the tree
	int turns; //number of turns
	
	
	

	public int evaluateBoardMax(chessBoard initial) {
		chessBoard[] nextb = initial.getBoards();
		int max = -999;
		if (nextb == null) {
			return initial.evaluateScore();
		}
		for(chessBoard b: nextb) {
			int holder = evaluateBoardMin(b);
			if (max <= holder) {
				max = holder;
			}
		}
		return max;
		
	}
	
	public int evaluateBoardMin(chessBoard initial) {
		chessBoard[] nextb = initial.getBoards();
		int min = 999;
		if (nextb == null) {
			return initial.evaluateScore();
		}
		for(chessBoard b: nextb) {
			int holder = evaluateBoardMax(b);
			if (min >= holder) {
				min = holder;
			}
		}
		return min;
	}
	

}
