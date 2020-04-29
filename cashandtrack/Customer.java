package cashandtrack;
import java.util.*;
import java.lang.String;



public class Customer {

    private List<Menu> order = new ArrayList<>();
    private String customerName;


    public Customer(String name) {
        this.customerName = name;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    /** */
    public void addOrder(Menu  menu){
        order.add( menu);

    }

    public void deleteOrder(int index) {
        if (index > order.size()) {
            System.out.println("Out of index");
        } else {
            order.remove(index);
        }
    }

    public void showOrder() {
        if (order.size() == 0) {
            System.out.println("No order");
        } else {
            for (Menu allOrder : order) {
                System.out.println("Order : " + allOrder);
            }
        }
    }

    public String toString() {
        return String.format("Customer : ", getCustomerName() );
    }




}