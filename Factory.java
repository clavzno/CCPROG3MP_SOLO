import java.util.*; //ArrayList

public class Factory {
    private final String factoryName; //provided upon instantiation and cannot be changed
    private CashRegister factoryFunds;
    private ArrayList<VendingMachine> machines;


    Factory (String newFactoryName) {
        this.name = newFactoryName;
        this.factoryFunds = new CashRegister();
        this.machines = new ArrayList<VendingMachine>();
    }

    public void createRegularVendingMachine(String vendingMachineName, ArrayList<Item> ItemList){
        VendingMachine newMachine = new VendingMachine();
        this.machines.add(newMachine);
    }
}
