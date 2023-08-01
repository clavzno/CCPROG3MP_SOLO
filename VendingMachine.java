import java.util.ArrayList;
import java.io.File;

public abstract class VendingMachine {
    private String vendingName;
    private CashRegister vendingFunds;
    //private ArrayList<Slots> vendingSlots; //default 8 slots for RegularVendingMachine 
    private File vendingHistory;

    public VendingMachine(String vendingName) {
        this.vendingName = vendingName;
        this.vendingFunds = new CashRegister();
        //this.vendingSlots = new ArrayList<Slots>();
        this.vendingHistory = new File(vendingName + ".txt");
    }

    public String getVendingName() {
        return this.vendingName;
    }
}
