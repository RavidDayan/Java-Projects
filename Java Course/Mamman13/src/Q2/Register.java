package Q2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Register {
    //attributes
    private String customerName;
    private int id;
    private List<CheckLine> purchaseList = new LinkedList<>();
    private static List<MenuItem> storeItemList = new LinkedList<>();

    //constructors
    public Register() {

    }

    //methods
    //add check line item
    public void addItemToPurchase(String itemName, double quantity) {
        int index = existCheckline(itemName);
        if (index == -1) {
            CheckLine new_checkline = new CheckLine(storeItemList.get(getItemPlacement(itemName)), quantity);
            this.purchaseList.add(new_checkline);
        } else {
            this.purchaseList.get(index).updateCheckLine(quantity);

        }
    }

    private int existCheckline(String itemName) {
        int place = -1;
        for (CheckLine checkline : this.purchaseList) {
            if (checkline.getItem().getName().equals(itemName)) {
                place = this.purchaseList.indexOf(checkline);
                break;
            }
        }
        return place;
    }

    //prints current purchase list
    public String printPurchase() {
        String purchase = "";
        for (CheckLine checkline : this.purchaseList) {
            purchase += ChecklineOrganizer(checkline.getItem().getName(), checkline.getQuantity(), checkline.getCost());
        }
        return purchase;
    }

    private String ChecklineOrganizer(String name, double quantity, double cost) {
        return "item name: " + name + " quantity: " + quantity + " cost: " + cost + System.lineSeparator();
    }

    public static void printStoreItems() {
        for (MenuItem item : storeItemList) {
            printItemDetails(item);
        }
    }

    private static void printItemDetails(MenuItem item) {
        System.out.println(item.getName() + " price:" + item.getPrice());
    }

    private static int getItemPlacement(String itemName) {
        int itemPlace = -1;
        for (MenuItem item : storeItemList) {
            if (item.getName().equals(itemName)) {
                itemPlace = storeItemList.indexOf(item);
                break;
            }
        }
        return itemPlace;
    }

    public void uploadMenu() throws FileNotFoundException {
        Scanner input = new Scanner(new File("src/Q2/menu.txt"));
        while (input.hasNext()) {
            String st = input.next();
            System.out.println(st);
        }
    }
}

