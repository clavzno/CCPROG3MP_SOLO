import java.util.HashMap;

public class CashRegister {
    private HashMap<Double, Double> cashInventory;

    public CashRegister() {
        this.cashInventory = new HashMap<Double, Double>();
        initializeMoney();
    }

    private void initializeMoney() {
        double denominations[] = { 0.25, 0.50, 1.00, 5.00, 10.00, 20.00, 50.00, 100.00, 1000.00 };
        double amount[] = { 10, 10, 10, 10, 10, 10, 10, 10, 10 };

        for (int i = 0; i < denominations.length; i++) {
            this.cashInventory.put(denominations[i], amount[i]);
        }
    }
}
