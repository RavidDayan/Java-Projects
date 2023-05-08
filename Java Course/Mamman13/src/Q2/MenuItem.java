package Q2;

public class MenuItem {
    //variables
    private String name;
    private String category;
    private double price;
    //constructor
    public MenuItem(String name,String category,double price) throws IlegalCategoryException, IlegalPriceException {
        if(!isLegalCategory(category)){
            throw new IlegalCategoryException(category);
        }
        else if (!isLegalPrice(price)){
            throw new IlegalPriceException();
        }
        else {
            this.category=category;
            this.price=price;
            this.name=name;
        }

    }
    private boolean isLegalCategory(String category){
        if(category.equals("main dish") || category.equals("first dish") || category.equals("last dish") || category.equals("drink")){
            return true;
        }
        return false;
    }
    private boolean isLegalPrice(double price){
        if(price>=0){
            return true;
        }
        return false;
    }

    public double getPrice() {
        return this.price;
    }
    public String geCategory() {
        return this.category;
    }
    public String getName() {
        return name;
    }
}
