package Q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Register {
    //attributes
    private static List<MenuItem> menu = new LinkedList<>();

    //constructors
    public Register() {

    }

    //methods
    public List<MenuItem> getMenu(){return this.menu;}

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

