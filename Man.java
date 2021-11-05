public class Man {
    public final boolean isBlack; // black or red
    public int[][] directions;
    
    public Man(boolean isBlack) {
        this.isBlack = isBlack;

        directions = new int[4][2];
        directions[0] = new int[]{1, 1};
        directions[1] = new int[]{-1, 1};
        directions[2] = null;
        directions[3] = null;
    }

    public void promote() {
        directions[2] = new int[]{1, -1};
        directions[3] = new int[]{-1, -1};
    }
    
    public boolean isBlack() {
    	return isBlack;
    }
}
