package cashandtrack.payment;

/**
 * A PremiumPayment class is the strategy for payment method.
 */
public class PremiumPayment implements PaymentStrategy {

    /**
     * To calculate the discount cost.
     * @param amount total cost of order
     * @return net cost
     */
    @Override
    public double payment(double amount) {
        return amount * 93 / 100 ;
    }
}