package cashandtrack;

import javafx.scene.control.Alert;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** the store of all customer and menu */
public class StoreSingleton {

    /** create an object of StoreSingleton */
    private static StoreSingleton instance = new StoreSingleton();
    /** crete a list of all customer */
    private static List<Customer> allCustomer = new ArrayList<>();
    /** crete a list of all menu */
    private static List<Menu> allMenu = new ArrayList<Menu>();
    /** the alert box */
    private static Alert alert = new Alert(Alert.AlertType.ERROR);

    /** make the constructor private so that
     * this class cannot be instantiated */
    private StoreSingleton() { }

    /**
     * to get the store singleton
     * @return  the store
     * */
    public static StoreSingleton getInstance() {
        if (instance == null) {
            instance = new StoreSingleton();
        }
        return instance;
    }

    /**
     * to get the customer from CSV file.
     * @return the list of all customer
     * */
    public List<Customer> getAllCustomer() {
        //Alert alert = new Alert(Alert.AlertType.ERROR);
        String line = "";
        try {
            if (allCustomer.isEmpty()) {
                BufferedReader customerReader = new BufferedReader(new FileReader("txt/customer.csv"));
                while ((line = customerReader.readLine()) != null) {
                    String[] customerList = line.split(",");
                    if (customerList[0].startsWith("#")) continue;
                    if (customerList[0].isEmpty()) continue;
                    Customer newCustomer = new Customer(customerList[0], customerList[1], Double.parseDouble(customerList[2]));
                    allCustomer.add(newCustomer);
                }
            }
        } catch (IOException e) {
            alert.setContentText("File isn't exist.");
            alert.show();
        } return allCustomer;
    }

    /**
     * to get the menu from CSV file.
     * @return the list of all menu
     * */
    public List<Menu> getAllMenu() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String line = "" ;
        try {
            if (allMenu.isEmpty()) {
                BufferedReader menuReader = new BufferedReader(new FileReader("txt/menu.csv"));
                while ((line = menuReader.readLine()) != null) {

                    String[] y = line.split(",");
                    if (y[0].startsWith("#")) continue;
                    if (y[0].isEmpty()) continue;
                    Menu newMenu = new Menu(y[0], Double.parseDouble(y[1]));
                    allMenu.add(newMenu);
                }
            }
        } catch (IOException e) {
            alert.setContentText("File isn't exist.");
            alert.show();
        } return allMenu;
    }
}