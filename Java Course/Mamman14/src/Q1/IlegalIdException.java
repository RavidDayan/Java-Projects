package Q1;

public class IlegalIdException extends Exception{
    public IlegalIdException(){
        super("Id is illegal, please enter an id between 0 to 999999999");
    }
}
