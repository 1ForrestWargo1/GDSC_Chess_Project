
public class Test {
	public static void main(String[] args) {
		chessBoard board = new TesterClass(5);
		MiniMax test = new MiniMax();
		
		int result = test.evaluateBoardMax(board);
		int result2 = test.evaluateBoardMin(board);
		
		System.out.println(result);
		System.out.println(result2);
	}

}
