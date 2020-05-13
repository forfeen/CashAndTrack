package cashandtrack.payment;

/** The strategy for the payment method */
public interface PaymentStrategy {

    /**
     * To calculate the discount cost.
     * @param amount total cost of order
     */
    double payment(double amount);
}