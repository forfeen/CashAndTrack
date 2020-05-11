package cashandtrack.customer;
import cashandtrack.CashAndTrack;
import cashandtrack.menu.Menu;
import payment.*;
import java.util.*;
import java.lang.String;

public class Customer {

    private List<Menu> order = new ArrayList<>();
    private String customerName;
    private String member;

    private PaymentStrategy strategy = new NormalPayment();

    public Customer(String name,String member, double cost) {
        this.customerName = name;
        this.member = member;
    }

    public double getTotalCost() {
        double cost = 0;
        for (Menu menuCost : order) {
            cost += menuCost.getMenuPrice();
        } return cost;
    }

    public List<Menu> getOrder() {
        return this.order;
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

//    //ราคาสุทธิ
//    public double netCost() {
//        return strategy.payment( getCost() );
//    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean equalsTo(Customer object) {
        if (object == null) return false;
        for (Customer customer: CashAndTrack.allCustomer) {
            if (customer.getCustomerName().toLowerCase().equals(object.getCustomerName().toLowerCase())) {
                if (customer.getMember().equals(object.getMember())) {
                    return true;
                }
            }
        } return false;
    }

}