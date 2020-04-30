package strategy;

public class PremiumPayment implements PaymentStrategy {

    @Override
    public double payment(double amount) {
        return amount * 93 / 100 ;
    }
}