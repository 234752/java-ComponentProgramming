package pl.first.firstjava;

import java.util.Random;
import java.util.Vector;

public class SudokuBoard {
  Random random = new Random();
  private int[][] board = new int[9][9];


    public boolean isValid(int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == n) { return false; }                                               //check columns
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == n) { return false; }                                               //check rows
        }

        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int iR = boxRowStart; iR < boxRowStart + 3; iR++) {
            for (int iC = boxColStart; iC < boxColStart + 3; iC++) {
                if (board[iR][iC] == n) {
                    return false;                                                                   //check squares
                }
            }
        }
        return true;                                                                                //if all 3 are OK
    }

    private boolean solveBoard() {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int iR = 0; iR < 9; iR++) {
            for (int iC = 0; iC < 9; iC++) {
                if (board[iR][iC] == 0) {
                    row = iR;
                    col = iC;
                    isEmpty = false;
                    break;
                }
            }if (!isEmpty) { break; }
        }
        if (isEmpty) { return true; }                                                                //end with all fields filled


        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {
                board[row][col] = num;
                if (solveBoard()) {
                    return true;
                }
                else {
                    board[row][col] = 0;
                }
            }
        }
        return false;
    }

    public void print() {
        for (int iR = 0; iR < 9; iR++) {
            for (int iC = 0; iC < 9; iC++) {
                System.out.print(board[iR][iC]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
    public void fillBoard() {
        for(int i=1; i<=9; i++) {
            board[random.nextInt(9)][random.nextInt(9)] = i;
        }

        this.solveBoard();
    }


}
