package Q1;

public class NumberExistsInSquareException extends Exception {
    public NumberExistsInSquareException(int row,int col)
    {
        super("The number already exists in square at row:"+row+" col:"+col);
    }
    public NumberExistsInSquareException()
    {
        super("The number already exists in square");
    }
}
