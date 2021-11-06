import java.util.ArrayList;

public interface Board {

    // returns a list of all possible subsequent board states
    public ArrayList<Board> getAllBoards(); 

    public int evaluateBoard();
    
}
