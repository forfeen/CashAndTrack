package cashandtrack;
import java.util.*;
import java.lang.String;

public class Menu {

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

    public String toString() {
        return String.format(" %s  %.2f", getMenuName(), getMenuPrice() );
    }


}




