package Q1;

public class IlegalGradeException extends Exception{
    public IlegalGradeException(){
        super("Grade is illegal, please enter grade between 0-100");
    }
}
