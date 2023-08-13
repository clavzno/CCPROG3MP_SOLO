import java.util.HashMap;
import java.util.ArrayList;

public class RegularVendingMachine extends VendingMachine {
    private int maxSlots;

    public RegularVendingMachine(String name) {
        super(name);
        this.maxSlots = 8;
    }

    public void addSlot(int amount) {
        if (getSlots().size() < maxSlots) { // if there are less than maxSlots, add a slot
            getSlots().add(new Slot());
        }
    }

    public boolean checkIfSlotAmountIsValid(int amount) {
        if (amount <= 0 || amount > maxSlots) {
            return false;
        }
        return true;
    }

    public HashMap<Double, Integer> buyItemTask(int slotIndex, HashMap<Double, Integer> payment) {
        // check if invalid slot
        boolean invalidSlotCheck = checkIfInvalidSlot(slotIndex);
        // check if slot is empty
        boolean emptySlotCheck = checkIfSlotIsFull(slotIndex);
        // if both are true, proceed with import itemprice into cashregister to check if
        // payment is enough and to calculate change
        if (invalidSlotCheck == true || emptySlotCheck == false) {
            return payment;
        } else if (invalidSlotCheck == false || emptySlotCheck == true) {
            return payment;
        } else {
            // proceed with transaction
            HashMap<Double, Integer> returnChange = super.getFunds().calculatePaymentTask(slotIndex, payment);
            return returnChange;
            // dispense Item is called separately and that's where transaction is added to
        }
    }

    public ArrayList<Item> dispenseItemTask(int amount, int slotIndex) {
        Slot selectedSlot = getSlots().get(slotIndex - 1); // slots are 0-8 but user inputs 1-9
        ArrayList<Item> itemsDispensed = selectedSlot.dispenseItem(amount);
        //add transaction to history when item is dispensed
        //TODO: MAKE SURE TO COPY THIS INTO SPECIAL VENDING MACHINE
        super.getTransactionHistory().addTransactionToHistory(itemsDispensed);
        return itemsDispensed;
    }

    private boolean checkIfInvalidSlot(int slotIndex) {
        if (slotIndex < 0 || slotIndex > getSlots().size()) {
            return true; // Invalid slot index
        }
        return false;
    }

}
