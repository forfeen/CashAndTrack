package cashandtrack.cart;

import cashandtrack.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static List<MenuItem> menuItem= new ArrayList<>();
    private static double totalCost = 0.0;

    public Cart(MenuItem item) {
        menuItem.add(item);
    }

    public static List<MenuItem> getMenuItem() {
        return menuItem;
    }

    public static double getCost() {
        for (MenuItem menuItem : menuItem){
           totalCost += (menuItem.getMenu().getMenuPrice() * menuItem.getQuantity());
        }
        return totalCost;
    }




}