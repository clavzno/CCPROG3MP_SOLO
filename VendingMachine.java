import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

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

    public abstract void addSlot(int amount);
    //overridden in RegularVendingMachine and SpecialVendingMachine

    public void addItemToSlot(int slotIndex, Item item) {
        slots.get(slotIndex).addItem(item);
    }

    public boolean checkIfSlotIsFull(int slotIndex) {
        Slot selectedSlot = slots.get(slotIndex);
        if (selectedSlot.getItemsInSlot().size() == selectedSlot.getMaxCapacity()) {
            return false; 
        }
        return true; //slot is not full
    }

    public abstract HashMap<Double, Integer> buyItemTask(int slotIndex, HashMap<Double, Integer> payment);

    public void setLastRestockDateTime(Date newDate) {
        history.setLastRestockDateTime(newDate);
    }

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

    public TransactionHistory getTransactionHistory() {
        return history;
    }

}