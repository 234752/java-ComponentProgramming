public class SudokuBox extends SudokuSetOfNineFields {

    private SudokuField[][] box = new SudokuField[3][3];

    public void set(int x, int y, SudokuField field) {
        box[x][y] = field;
    }

    public boolean verify() {
        int index = 0;
        SudokuField[] flatBox = new SudokuField[9];

        for (int x=0; x<3; x++) {
            for (int y=0; y<3; y++) {
                flatBox[index] = box[x][y];
            }
        }
        return verifyFields(flatBox);
    }
}
