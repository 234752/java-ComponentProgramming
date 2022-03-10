package pl.first.firstjava;

import java.util.Random;

public class SudokuBoard {

  Random random = new Random();
  private int[][] board = new int[9][9];

  public int getNumber(int row, int col) {
      return board[row][col];
  }

  private boolean isValid(int row, int col, int n) {
      for (int i = 0; i < 9; i++) {
          if (board[row][i] == n) {
              return false;
          }
      }

      for (int i = 0; i < 9; i++) {
          if (board[i][col] == n) {
              return false;
          }
      }

      int boxRowStart = row - row % 3;
      int boxColStart = col - col % 3;

      for (int rowIterator = boxRowStart; rowIterator < boxRowStart + 3; rowIterator++) {
          for (int colIterator = boxColStart; colIterator < boxColStart + 3; colIterator++) {
              if (board[rowIterator][colIterator] == n) {
                  return false;
              }
          }
      }
      return true;
  }

  private boolean solveBoard() {
      int row = -1;
      int col = -1;
      boolean isEmpty = true;
      for (int rowIterator = 0; rowIterator < 9; rowIterator++) {
          for (int colIterator = 0; colIterator < 9; colIterator++) {
              if (board[rowIterator][colIterator] == 0) {
                  row = rowIterator;
                  col = colIterator;
                  isEmpty = false;
                  break;
              }
          }
          if (!isEmpty) {
              break;
          }
      }
      if (isEmpty) {
          return true;
      }

      for (int num = 1; num <= 9; num++) {
          if (isValid(row, col, num)) {
              board[row][col] = num;
              if (solveBoard()) {
                  return true;
              } else {
                  board[row][col] = 0;
              }
          }
      }
      return false;
  }

  public void print() {
      for (int rowIterator = 0; rowIterator < 9; rowIterator++) {
          for (int colIterator = 0; colIterator < 9; colIterator++) {
              System.out.print(board[rowIterator][colIterator]);
              System.out.print(" ");
          }
          System.out.print("\n");
      }
  }

  public void fillBoard() {
      for (int row = 0; row < 9; row++) {
          for (int col = 0; col < 9; col++) {
              board[row][col] = 0;
          }
      }

      for (int i = 1; i <= 9; i++) {
          board[random.nextInt(9)][random.nextInt(9)] = i;
      }
      this.solveBoard();
    }

}
