package Q2;

public class IlegalCategoryException  extends Exception{
    public IlegalCategoryException(String message){
        super(message+"  is not a legal category,please change to either first dish,main fish,last dish or drink");
    }
    public IlegalCategoryException(){
        super("please  change category to either first dish,main fish,last dish or drink");
    }

}
