package cashandtrack.menu;

import cashandtrack.CashAndTrack;
import java.lang.String;

public class Menu {

    private  String menuName;
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
        for (Menu menu : CashAndTrack.allMenu) {
            if (menu.menuName.toLowerCase().equals(object.menuName.toLowerCase())) {
                if (menu.menuPrice == object.menuPrice) {
                    System.out.println(menu);
                    return true;
                }
            }
        } return false;
    }

//    public String toString() {
//        return String.format(" %s  %.2f", getMenuName(), getMenuPrice() );
//    }

}




