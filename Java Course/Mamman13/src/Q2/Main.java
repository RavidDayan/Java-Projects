package Q2;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Register rg=new Register();
            try {
                rg.uploadMenu();
            } catch (FileNotFoundException e) {
                System.out.println("no file found");
            }
    }
}
