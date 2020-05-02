package cashandtrack.customer;
import cashandtrack.menu.Menu;
import strategy.NormalPayment;
import strategy.PaymentStrategy;

import java.util.*;
import java.lang.String;

public class Customer {

    private List<Menu> order = new ArrayList<>();
    private String customerName;
    private PaymentStrategy strategy = new NormalPayment();

    public Customer(String name, double cost) {
        this.customerName = name;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public double getCost() {
        double cost = 0 ;
        for (Menu menuCost : order) {
            cost += menuCost.getMenuPrice();
        }
        return cost;
    }

    //ราคาสุทธิ
    public double netCost() {
        return strategy.payment( getCost() );
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }


    /** */
    public void addOrder(Menu  menu){
        order.add(menu);
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