package pl.first.firstjava;
import java.util.Collections;
import java.util.Random;

public class SudokuBoard
{
    private int[][] board = new int[9][9];
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

    public boolean fillBoard()
    {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int iR = 0; iR < 9; iR++)
        {
            for (int iC = 0; iC < 9; iC++)
            {
                if (board[iR][iC] == 0)
                {
                    row = iR;
                    col = iC;

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
        for (int num = 1; num <= 9; num++)
        {
            if (isValid(board, row, col, num))
            {
                board[row][col] = num;
                if (fillBoard())
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

    public void print()
    {
        for (int iR = 0; iR < 9; iR++)
        {
            for (int iC = 0; iC < 9; iC++)
            {
                System.out.print(board[iR][iC]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args)
    {
        SudokuBoard sb = new SudokuBoard();

        sb.fillBoard();
        sb.print();
    }

}
