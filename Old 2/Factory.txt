import java.util.ArrayList;

public class Factory {
    private String factoryName;
    private ArrayList<VendingMachine> vendingMachineList;
    private CashRegister factoryFunds;

    public Factory(String factoryName) {
        this.factoryName = factoryName;
        this.vendingMachineList = new ArrayList<VendingMachine>();
        this.factoryFunds = new CashRegister();
    }

    public VendingMachine chooseVendingMachine(String name) {
        for (VendingMachine vm : this.vendingMachineList) {
            if (vm.getVendingName().equals(name)) {
                return vm;
            }
        }
        
        return null;
    }

    public Boolean createRegularVendingMachine(String name) {
        if (chooseVendingMachine(name) != null) {
            return false;
        }

        RegularVendingMachine rvm = new RegularVendingMachine(name);
        this.vendingMachineList.add(rvm);
        return true;
    }

    public String getName() {
        return this.factoryName;
    }
}
