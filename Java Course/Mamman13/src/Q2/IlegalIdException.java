package Q2;

public class IlegalIdException extends Exception{
    public IlegalIdException(){
        super("The Id is not valid, please make sure the id is made up by 9 digits");
    }
}
