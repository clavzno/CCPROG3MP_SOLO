import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CashRegister {
    private HashMap<Double, Integer> change;
    private HashMap<Double, Integer> earnings;

    public CashRegister() {
        change = new HashMap<Double, Integer>();
        earnings = new HashMap<Double, Integer>();
        initializeMoney();
    }

    private void initializeMoney() {
        Double denominations[] = { 0.25, 0.50, 1.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0, 1000.0 };
        int values[] = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };

        for (int i = 0; i < denominations.length; i++) {
            change.put(denominations[i], values[i]);
            earnings.put(denominations[i], 0);
        }
    }

    public ArrayList<Integer> emptyCashRegister() {
        ArrayList<Integer> transferredValues = new ArrayList<>(earnings.values());
        earnings.replaceAll((k, v) -> 0);
        return transferredValues;
    }

    public void replenishChange(double denomination, int amount) {
        change.put(denomination, change.get(denomination) + amount);
    }

}
