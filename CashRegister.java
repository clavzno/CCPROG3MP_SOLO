import java.util.HashMap;

public class CashRegister {
    private HashMap<Double, Double> cashInventory;

    public CashRegister() {
        this.cashInventory = new HashMap<Double, Double>(0, 0);
        initializeMoney();
    }

    private void initializeMoney() {
        double denominations[] = { 0.25, 0.50, 1.00, 5.00, 10.00, 20.00, 50.00, 100.00, 1000.00 };
        double amount[] = { 10, 10, 10, 10, 10, 10, 10, 10, 10 };

        for (int i = 0; i < denominations.length; i++) {
            this.cashInventory.put(denominations[i], amount[i]);
        }
    }

    private void receivePayment(HashMap<Double, Double> payment, HashMap<Double, Double> price) {
        checkIfEnoughMoney(payment, price);

        for (Double denomination : payment.keySet()) {
            this.cashInventory.put(denomination, this.cashInventory.get(denomination) + payment.get(denomination));
        }
    }

    // TODO: set conditions if payment is not enough and if payment is enough. If the payment is enough return true, which will trigger the change method.
    public boolean checkIfEnoughMoney(HashMap<Double, Double> payment, HashMap<Double, Double> price) {
        if (payment.size() != price.size() || payment.size() == 0 || price.size() == 0 || or payment.size < price.size()) {
            return false;
        }
        if (payment.size >= price.size()) {
            for (Double denomination : payment.keySet()) {
                if (payment.get(denomination) < price.get(denomination)) {
                    return false;
                }
            }
        }
    }

    public boolean returnChange() {
        return true; //TODO: return true if change is returned successfully, false if not
    }
}
