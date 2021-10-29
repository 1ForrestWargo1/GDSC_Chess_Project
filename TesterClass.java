public class TesterClass implements chessBoard{
    public int val;
    public TesterClass(int val){
        this.val = val;

    }
    public chessBoard[] getBoards(){
        chessBoard[] boards = new chessBoard[2];
        if(this.val <= 0){
            return boards;
        }
        boards[0] = new TesterClass(this.val -1);
        boards[1] = new TesterClass(this.val -2);
        return boards;
    }
	public int evaluateScore(){
        return this.val;
    }
    
}
