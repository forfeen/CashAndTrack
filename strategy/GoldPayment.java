package strategy;

public class GoldPayment implements PaymentStrategy {

    // 5%
    @Override
    public double payment(double amount) {
        return amount * 95 / 100 ;
    }
}