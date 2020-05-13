package cashandtrack.payment;

/**
 * A SilverPayment class is the strategy for payment method.
 */
public class SilverPayment implements PaymentStrategy {

    /**
     * To calculate the discount cost.
     * @param amount total cost of order
     * @return net cost
     */
    @Override
    public double payment(double amount) {
        return amount * 98 / 100 ;
    }
}