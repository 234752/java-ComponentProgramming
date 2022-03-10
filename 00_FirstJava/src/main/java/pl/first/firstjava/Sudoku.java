package pl.first.firstjava;

public class Sudoku {

    public static void main(String[] args) {
        SudokuBoard sb = new SudokuBoard();
        sb.fillBoard();
        sb.print();
        System.out.println("--------------");
        sb.fillBoard();
        sb.print();
    }
}
