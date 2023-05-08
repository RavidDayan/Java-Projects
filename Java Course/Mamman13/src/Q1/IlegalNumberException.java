package Q1;

public class IlegalNumberException extends Exception{
    public IlegalNumberException(){
        super("The Number is not between 1-9");
    }
}
