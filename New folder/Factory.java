import java.util.ArrayList;
import java.util.HashMap;

public class Factory {
    private String name;
    private ArrayList<VendingMachine> vendingMachines;
    private HashMap<Double, Integer> factoryFunds;

    public Factory(String name) {
        this.name = name;
        vendingMachines = new ArrayList<VendingMachine>();
        factoryFunds = new HashMap<Double, Integer>();
        initializeFactoryFunds();
    }

    private void initializeFactoryFunds() {
        Double denominations[] = { 0.25, 0.50, 1.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0, 1000.0 };
        for (int i = 0; i < denominations.length; i++) {
            factoryFunds.put(denominations[i], 0);
        }
    }

    public void collectFunds(VendingMachine vendingMachine) {
        //TODO: Add values from Arraylist into the values part of the hashmap assuming the order is correct
        ArrayList<Integer> funds = vendingMachine.getFunds().emptyCashRegister();
        for (int i = 0; i < funds.size(); i++) {
            Integer denomination = funds.get(i);
            if (factoryFunds.containsKey(denomination)) {
                int currentCount = factoryFunds.get(denomination);
                factoryFunds.put(denomination, currentCount + 1);
            }
        }
    }
    

    public boolean createRegularVendingMachine(String name, int slotAmount) {
        return true;
    }

    public boolean createSpecialVendingMachine(String name, int slotAmount) {
        return true;
    }

    public boolean addItemToMachine(Item item, VendingMachine vendingmachine) {
        //return false if item cannot be added aka max capacity reached
        return true;
    }

    public Transaction buyItem(VendingMachine vendingMachine, int slotIndex, int amount) {
        //return null if item cannot be bought aka not enough items in slot
        return null;
    }

    public void setNewItemPrice(VendingMachine vendingMachine, int slotIndex, double newPrice) {
        //TODO
    }

    //getters
    public String getName() {
        return name;
    }

    public ArrayList<VendingMachine> getVendingMachines() {
        return vendingMachines;
    }

    public CashRegister getFactoryFunds() {
        return factoryFunds;
    }

}
