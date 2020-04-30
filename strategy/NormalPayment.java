package strategy;

public class NormalPayment implements PaymentStrategy {

    @Override
    public double payment(double amount) {
        return amount;
    }
}