import java.util.ArrayList;
import java.util.HashMap;

public abstract class VendingMachine {
    private String name;
    private ArrayList<Slot> slots;
    private CashRegister funds;
    private TransactionHistory history;
    private int minSlots;

    public VendingMachine(String name) {
        this.name = name;
        slots = new ArrayList<Slot>();
        funds = new CashRegister();
        history = new TransactionHistory();
        minSlots = 8;
    }

    public abstract boolean addSlot(int amount);
    //overridden in RegularVendingMachine and SpecialVendingMachine

    public void addItemToSlot(int slotIndex, Item item) {
        slots.get(slotIndex).addItem(item);
    }

    public abstract HashMap<Double, Integer> buyItemTask(int slotIndex, HashMap<Double, Integer> payment);

    //getters
    public String getName() {
        return name;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public CashRegister getFunds() {
        return funds;
    }

    public TransactionHistory getHistory() {
        return history;
    }

    public int getMinSlots() {
        return minSlots;
    }

}