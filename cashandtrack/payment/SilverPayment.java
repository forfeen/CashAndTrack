package cashandtrack.payment;

public class SilverPayment implements PaymentStrategy {

    @Override
    public double payment(double amount) {
        return amount * 98 / 100 ;
    }
}