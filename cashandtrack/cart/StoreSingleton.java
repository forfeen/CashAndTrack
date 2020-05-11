package cashandtrack.cart;

import cashandtrack.customer.Customer;
import cashandtrack.menu.Menu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreSingleton {

    private static List<Customer> allCustomer = new ArrayList<>();
    private static List<Menu> allMenu = new ArrayList<Menu>();
    private static StoreSingleton instance = new StoreSingleton();

    private StoreSingleton() { }

    public static StoreSingleton getInstance() {
        if (instance == null) {
            instance = new StoreSingleton();
        }
        return instance;
    }

    public List<Customer> getAllCustomer() {
        String line = "";
        try {
            if (allCustomer.isEmpty()) {
                BufferedReader customer = new BufferedReader(new FileReader("txt/customer.csv"));
                while ((line = customer.readLine()) != null) {
                    String[] x = line.split(",");
                    if (x[0].startsWith("#")) continue;
                    if (x[0].isEmpty()) continue;
                    Customer newCustomer = new Customer(x[0], x[1], Double.parseDouble(x[2]));
                    allCustomer.add(newCustomer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace() ;
        }
        return allCustomer;
    }

    public List<Menu> getAllMenu() {
        String line = "" ;
        try {
            if (allMenu.isEmpty()) {
                BufferedReader x = new BufferedReader(new FileReader("txt/menu.csv"));
                while ((line = x.readLine()) != null) {

                    String[] y = line.split(",");
                    if (y[0].startsWith("#")) continue;
                    if (y[0].isEmpty()) continue;
                    Menu newMenu = new Menu(y[0], Double.parseDouble(y[1]));
                    allMenu.add(newMenu);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMenu;
    }

}