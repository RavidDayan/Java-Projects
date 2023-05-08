package Q1;

public class NumberExistsInRowException extends Exception{
    public NumberExistsInRowException(int col){
        super("the Number already exists in row at column "+col);
    }
    public NumberExistsInRowException(){
        super("the Number already exists in row");
    }
}
