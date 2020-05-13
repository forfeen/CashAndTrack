package cashandtrack;
import cashandtrack.payment.*;
import java.util.*;
import java.lang.String;

/**
 * A Customer with a name, membership levels and total cost.
 * @author Chananya Photan
 */
public class Customer {
    /** create an object of StoreSingleton */
    private static StoreSingleton storeSingleton = StoreSingleton.getInstance();
    /** initial list of order */
    private List<Menu> order = new ArrayList<>();
    /** initial customer's name */
    private String customerName;
    /** initial membership levels */
    private String member;
    /** initial total cost */
    private double cost;
    /** initial discount percent */
    private String discount;
    /** initial payment strategy */
    private PaymentStrategy strategy = new NormalPayment();

    /** Customer has a name , membership levels and total cost. */
    public Customer(String name,String member, double cost) {
        this.customerName = name;
        this.member = member;
        this.cost = getTotalCost();
    }

    /**
     * To get the total cost of order.
     * @return the total cost
     */
    public double getTotalCost() {
        double cost = 0;
        for (Menu menuCost : order) {
            cost += menuCost.getMenuPrice();
        } return cost;
    }

    /**
     * To get the list of order.
     * @return the list of order
     */
    public List<Menu> getOrder() {
        return this.order;
    }

    /**
     * To get the name of customer.
     * @return the name of customer
     */
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * To get the memberships levels
     * and set the payment strategy.
     * @return the memberships levels
     */
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

    /**
     * To get the discount text.
     * @return the discount text
     */
    public String getDiscount() {
        switch (member) {
            case "Premium" :
                discount = "Discount 7%";
                break;
            case "Gold":
                discount = "Discount 5%";
                break;
            case "Silver":
                discount = "Discount 2%";
                break;
            default:
                discount = "No Discount";
        }
        return discount;
    }

    /**
     * To get the net cost.
     * @return the net cost
     */
    public double netCost() {
        return strategy.payment(getTotalCost());
    }

    /**
     * To set the payment strategy.
     * @param strategy
     */
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * To compare the customer
     * @param other
     * @return true if the customer is similarly
     */
    public boolean equalsTo(Customer other) {
        if (other == null) return false;
        for (Customer customer: storeSingleton.getAllCustomer()) {
            if (customer.getCustomerName().toLowerCase().equals(other.getCustomerName().toLowerCase())) {
                if (customer.getMember().equals(other.getMember())) {
                    return true;
                }
            }
        } return false;
    }
}