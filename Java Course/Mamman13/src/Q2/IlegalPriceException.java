package Q2;

public class IlegalPriceException extends Exception {
    public IlegalPriceException(){
        super("please enter only prices of zero or above");
    }

}
