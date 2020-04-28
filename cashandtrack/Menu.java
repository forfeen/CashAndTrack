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
        return String.format("Menu : %s Price : %.2f", getMenuName(), getMenuPrice() );
    }

    public void showAllMenu(List<Menu> allMenu) {
        if (allMenu.size() == 0){
            System.out.println("No Menu");
        } else {
            int count = 0;
            for (int i = 0; i <= allMenu.size() - 1; i++){
                count ++ ;
                String menu = String.format(count + ". " +allMenu.get(i).toString());
                System.out.println(menu);
            }
        }
    }



    public void deleteMenu(int index, List<Menu> menu) {
        if (index > menu.size()) {
            System.out.println("Out of index.");
        }
        else {
            menu.remove(index-1);
            showAllMenu(menu);
        }
    }


}




