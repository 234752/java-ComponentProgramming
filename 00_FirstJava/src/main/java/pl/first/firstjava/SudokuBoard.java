package pl.first.firstjava;
import java.util.Collections;
import java.util.Random;

public class SudokuBoard
{
    public static boolean isValid(int[][] board, int row, int col, int n)
    {
        for (int i = 0; i < 9; i++)
        {
            if (board[row][i] == n) { return false; }
        }

        for (int i = 0; i < 9; i++)
        {
            if (board[i][col] == n) { return false; }
        }

        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int iR = boxRowStart; iR < boxRowStart + 3; iR++)
        {
            for (int iC = boxColStart; iC < boxColStart + 3; iC++)
            {
                if (board[iR][iC] == n)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean fillBoard(int[][] board, int n)
    {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == 0)
                {
                    row = i;
                    col = j;

                    // We still have some remaining
                    // missing values in Sudoku
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        // No empty space left
        if (isEmpty)
        {
            return true;
        }

        // Else for each-row backtrack
        for (int num = 1; num <= n; num++)
        {
            if (isValid(board, row, col, num))
            {
                board[row][col] = num;
                if (fillBoard(board, n))
                {
                    // print(board, n);
                    return true;
                }
                else
                {
                    // replace it
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    public static void print(
            int[][] board, int N)
    {

        // We got the answer, just print it
        for (int r = 0; r < N; r++)
        {
            for (int d = 0; d < N; d++)
            {
                System.out.print(board[r][d]);
                System.out.print(" ");
            }
            System.out.print("\n");

            if ((r + 1) % (int)Math.sqrt(N) == 0)
            {
                System.out.print("");
            }
        }
    }

    // Driver Code
    public static void main(String args[])
    {

        int[][] board = new int[9][9];

        for(int i=0; i<9;i++)   for(int ii=0; ii<9;ii++)    board[i][ii] = 0;

        int N = board.length;

        if (fillBoard(board, N))
        {
            // print solution
            print(board, N);
        }
        else {
            System.out.println("No solution");
        }
    }

}
