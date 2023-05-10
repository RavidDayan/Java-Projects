package Q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Register {
    //attributes
    private static List<MenuItem> menu = new LinkedList<>();//list of all the menus items

    //constructors
    public Register() {
    }

    //methods
    //getter
    public List<MenuItem> getMenu(){return this.menu;}
//reads the menu.txt file and converts each 3 lines into an item.assumes 1st line is the foods name
    //2nd line is a predetermined category(otherwise stops the process)
    //3rd line is the price and must be a number 0 or above(otherwise stops the process)
    public void uploadMenu() throws FileNotFoundException,IlegalCategoryException,IlegalPriceException {
        Scanner input = new Scanner(new File("src/Q2/menu.txt"));
        int counter = -1;
        String name="";
        String category="";
        double price;
        while (input.hasNext()) {
            counter++;
            if (counter == 0) {
                name = input.nextLine();
            }
            if (counter == 1) {
                category = input.nextLine();
            }
            if (counter == 2) {
                price = Double.valueOf(input.nextLine());
                counter=-1;
                menu.add(new MenuItem(name,category,price));
            }
        }
    }
}

