package cashandtrack.menu;

import cashandtrack.CashAndTrack;
import cashandtrack.cart.StoreSingleton;

import java.lang.String;

public class Menu {

    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();
    private String menuName;
    private double menuPrice;

    public Menu(String menu, double price) {
        this.menuName = menu;
        this.menuPrice = price;
    }

    public double getMenuPrice() {
        return this.menuPrice;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public boolean equalsTo(Menu object) {
        if (object == null) return false;
        for (Menu menu : storeSingleton.getAllMenu()) {
            if (menu.getMenuName().toLowerCase().equals(object.getMenuName().toLowerCase())) {
                if (menu.getMenuPrice() == object.getMenuPrice()) {
                    return true;
                }
            }
        }
        return false;
    }
}




