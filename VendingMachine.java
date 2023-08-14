import java.util.ArrayList;
//import java.util.HashMap;
import java.util.Date;

public abstract class VendingMachine {
    private String name;
    private ArrayList<Slot> slots;
    private CashRegister funds;
    private TransactionHistory history;
    private int minSlots;

    /**
     * SuperConstructor for VendingMachine
     * @param name name of the vending machine that is set upon creation
     */
    public VendingMachine(String name) {
        this.name = name;
        slots = new ArrayList<Slot>();
        funds = new CashRegister();
        history = new TransactionHistory();
        minSlots = 8;
    }

    public abstract void addSlot(int amount);
    //overridden in RegularVendingMachine and SpecialVendingMachine

    /**
     * Adds an item to a slot
     * @param slotIndex index of the slot
     * @param item item to be added
     */
    public void addItemToSlot(int slotIndex, Item item) {
        slots.get(slotIndex).addItem(item);
    }

    /**
     * Checks if the slot is full.
     * @param slotIndex determines which slot to check
     * @return true if the slot is not full, false if it is full
     */
    public boolean checkIfSlotIsFull(int slotIndex) {
        Slot selectedSlot = slots.get(slotIndex);
        if (selectedSlot.getItemsInSlot().size() == selectedSlot.getMaxCapacity()) {
            return false; 
        }
        return true; //slot is not full
    }

    /**
     * During restock, resets the date in the Transaction History of the vending machine
     * @param newDate creates a new Date object on the spot, used to set the date in the Transaction History
     */
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