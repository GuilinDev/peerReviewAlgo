package main.java.ningSL.Questions;

public class TicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;

    public static void main(String[] args) {
        TicTacToe tc = new TicTacToe(3);
        tc.move(0,0,1);

    }

//["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
// [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
//[null, 0, 0, 0, 0, 0, 0, 1]

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
    }

    public int move(int row, int col, int player) {
        int toAdd = player == 1 ? 1 : -1;
        int size = rows.length;
        rows[row] += toAdd;
        cols[col] += toAdd;
        if (row == col)
        {
            diagonal += toAdd;
        }

        if ((row+col == size-1))
        {
            antiDiagonal += toAdd;
        }


        if (Math.abs(rows[row]) == size ||
                Math.abs(cols[col]) == size ||
                Math.abs(diagonal) == size  ||
                Math.abs(antiDiagonal) == size)
        {
            return player;
        }

        return 0;
    }
}