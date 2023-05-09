package Q2;

public class MenuItem {
    //variables
    private String name;
    private String category;
    private double price;

    //constructor
    public MenuItem(String name, String category, double price) throws IlegalCategoryException, IlegalPriceException {
        if (!isLegalCategory(category)) {
            throw new IlegalCategoryException(category);
        } else if (!isLegalPrice(price)) {
            throw new IlegalPriceException();
        } else {
            this.category = category;
            this.price = price;
            this.name = name;
        }

    }

    private boolean isLegalCategory(String category) {
        return category.equals("Main") || category.equals("Opening") || category.equals("Dessert") || category.equals("Drink");
    }

    private boolean isLegalPrice(double price) {
        return price >= 0;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return name;
    }
}
