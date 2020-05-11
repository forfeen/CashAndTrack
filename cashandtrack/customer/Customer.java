package cashandtrack.customer;
import cashandtrack.cart.MenuItem;
import cashandtrack.menu.Menu;
import cashandtrack.cart.Cart;
import strategy.*;

import java.util.*;
import java.lang.String;

public class Customer {

    //private List<Menu> order = new ArrayList<>();
    private Cart cart;
    private String customerName;
    private String member;
    private int countOrder ;
    private double cost;
    private PaymentStrategy strategy = new NormalPayment();

    public Customer(String name,String member, double cost) {
        this.customerName = name;
        this.member = member;
        this.cost = getAllCost();
        this.countOrder  =  getCountOrder();
    }

    public static int getCountOrder() {
        return Cart.getMenuItem().size();
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getMember() {
        switch (member) {
            case "Premium" :
                setPaymentStrategy( new PremiumPayment());
                break;
            case "Gold":
                setPaymentStrategy( new GoldPayment());
                break;
            case "Silver":
                setPaymentStrategy( new SilverPayment());
                break;
            default:
                setPaymentStrategy( new NormalPayment());
        }
        return this.member;
    }

    public static double getAllCost() {
        return Cart.getCost();
    }


//    //ราคาสุทธิ
//    public double netCost() {
//        return strategy.payment( getCost() );
//    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }


//    /** */
//    public void addOrder(Menu menu){
//        order.add(menu);
//    }

//    public void deleteOrder(int index) {
//        if (index > order.size()) {
//            System.out.println("Out of index");
//        } else {
//            order.remove(index);
//        }
//    }

//    public void showOrder() {
//        if (order.size() == 0) {
//            System.out.println("No order");
//        } else {
//            for (Menu allOrder : order) {
//                System.out.println("Order : " + allOrder);
//            }
//        }
//    }

//    public String toString() {
//        return String.format("Customer : ", getCustomerName() );
//    }


}