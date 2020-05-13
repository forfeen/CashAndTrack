package cashandtrack.payment;

/**
 * A NormalPayment class is the strategy for payment method.
 */
public class NormalPayment implements PaymentStrategy {

    /**
     * To calculate the discount cost.
     * @param amount total cost of order
     * @return net cost
     */
    @Override
    public double payment(double amount) {
        return amount;
    }
}