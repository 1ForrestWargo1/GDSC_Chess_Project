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

    public promote() {
        directions[2] = null;
        directions[3] = null;
    }
}
