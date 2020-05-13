package cashandtrack.payment;

/**
 * A GoldPayment class is the strategy for payment method.
 */
public class GoldPayment implements PaymentStrategy {

    /**
     * To calculate the discount cost.
     * @param amount total cost of order
     * @return net cost
     */
    @Override
    public double payment(double amount) {
        return amount * 95 / 100 ;
    }
}