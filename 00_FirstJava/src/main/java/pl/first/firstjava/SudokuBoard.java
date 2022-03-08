package pl.first.firstjava;
import java.util.concurrent.ThreadLocalRandom;

public class SudokuBoard
{
    private int[][] board = new int[10][10];

    private boolean checkRow(int rowIndex, int number)
    {
        for(int i = 1; i<=9; i++)
        {
            if(board[i][rowIndex] == number) return false;
        }
        return true;
    }
    private boolean checkColumn(int columnIndex, int number)
    {
        for(int i = 1; i<=9; i++)
        {
            if(board[columnIndex][i] == number) return false;
        }
        return true;
    }
    private boolean checkSquare(int startX, int startY, int number)
    {
        for(int iX = startX; iX<=startX+2; iX++)
        {
            for(int iY = startY; iY<=startY+2; iY++)
            {
                if(board[iX][iY]==number) return false;
            }
        }
        return true;
    }
    private int findCoordinates(int index)
    {
        if(index<=3) return 1;
        else if(index<=6) return 4;
        else return 7;
    }
    private boolean checkNumber(int column, int row, int n)
    {
        return (checkColumn(column, n) && checkRow(row, n) && checkSquare(findCoordinates(column), findCoordinates(row), n));
        //&& checkSquare(findCoordinates(column), findCoordinates(row), n)
    }
    public void fillBoard()
    {
        for(int row=1; row<=9;row++)
        {
            for(int column=1; column<=9; column++)
            {
                board[column][row] = ThreadLocalRandom.current().nextInt(1, 10);
            }
        }
    }
    public boolean verifyBoard()
    {
        for(int row=1; row<=9;row++)
        {
            for(int column=1; column<=9; column++)
            {
                if(!checkNumber(column, row, board[column][row])) return false;
            }
        }return true;
    }
    public void displayBoard()
    {
        for(int column=1; column<=9;column++)
        {
            for(int row=1; row<=9; row++)
            {
                System.out.print(board[column][row]);
            }
            System.out.print("\n");
        }
    }


    public static void main(String [] args)
    {
        SudokuBoard sb = new SudokuBoard();
        int i =0;
        while(true)
        {
            sb.fillBoard();
            if(sb.verifyBoard()) break;
            i++;
            if(i%1000000==0) System.out.println(i);
        }
        sb.displayBoard();

    }
}
