package Q1;

public class NumberExistsInColException extends Exception{
    public NumberExistsInColException(int row){
        super("the Number already exists in column at row "+row);
    }
    public NumberExistsInColException(){
        super("the Number already exists in column at");
    }
}
