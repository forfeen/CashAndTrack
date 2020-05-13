package cashandtrack;
import java.lang.String;

/**
 * A Menu with a name and price.
 * @author Chananya Photan
 */
public class Menu {
    /** create an object of StoreSingleton */
    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();
    /** initial menu's name */
    private String menuName;
    /** initial menu's price */
    private double menuPrice;

    /** Menu has a name and price */
    public Menu(String menu, double price) {
        this.menuName = menu;
        this.menuPrice = price;
    }

    /**
     * To get the price of Menu
     * @return the price of menu
     * */
    public double getMenuPrice() {
        return this.menuPrice;
    }

    /**
     * To get the name of menu
     * @return the name of menu
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * To compare the menu
     * @param other
     * @return true if the menu is similarly
     */
    public boolean equalsTo(Menu other) {
        if (other == null) return false;
        for (Menu menu : storeSingleton.getAllMenu()) {
            if (menu.getMenuName().toLowerCase().equals(other.getMenuName().toLowerCase())) {
                if (menu.getMenuPrice() == other.getMenuPrice()) {
                    return true;
                }
            }
        }
        return false;
    }
}




