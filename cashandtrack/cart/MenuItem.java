package cashandtrack.cart;

import cashandtrack.menu.Menu;

public class MenuItem {

    private Menu menu;
    private int quantity;

    public MenuItem(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public int getQuantity() {
        return this.quantity;
    }


}