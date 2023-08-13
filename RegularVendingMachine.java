import java.util.HashMap;
import java.util.ArrayList;

public class RegularVendingMachine extends VendingMachine {
    private int maxSlots;

    /**
     * Constructor for RegularVendingMachine. Sets maxSlots to 8.
     * 
     * @param name name of the vending machine
     */
    public RegularVendingMachine(String name) {
        super(name);
        this.maxSlots = 8;
    }

    /**
     * Adds a slot to the Regular Vending Machine. Different conditions apply
     * because Regular Vending Machines have a maximum Slot capacity of 8, while
     * Special Vending Machines have a minimum Slot capacity of 8.
     * 
     * @param amount amount of slots to be added
     */
    public void addSlot(int amount) {
        if (getSlots().size() < maxSlots) { // if there are less than maxSlots, add a slot
            getSlots().add(new Slot());
        }
    }

    /**
     * Checks if the intended amount of slots to be added is less than 0 or greater
     * than the maximum number of slots.
     * Called in Factory wghen adding slots to a Regular Vending Machine.
     * 
     * @param amount amount of slots to be added
     * @return true if amount is valid, false if amount is invalid
     */
    public boolean checkIfSlotAmountIsValid(int amount) {
        if (amount <= 0 || amount > maxSlots) {
            return false;
        }
        return true;
    }

    /**
     * Task that handles buying an Item from a Regular Vending Machine. First checks
     * if the slot is invalid then checks if the slot is empty.
     * If both one of or both are false, the transaction is not processed and the
     * change is returned.
     * If both are true, the transaction is processed and the change is returned.
     * Dispense of Item is to be called afterwards.
     * 
     * @param slotIndex index of the slot
     * @param payment   payment given by the user
     * @return change to be returned to the user
     */
    /* public HashMap<Double, Integer> buyItemTask(int slotIndex, HashMap<Double, Integer> payment) {
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
    } */

    /**
     * Task that handles buying an Item from a Regular Vending Machine. First checks
     * if the slot is invalid then checks if the slot is empty.
     * If both one of or both are false, the transaction is not processed and the
     * change is returned.
     * If both are true, the transaction is processed and the change is returned.
     * Dispense of Item is to be called afterwards.
     * 
     * @param slotIndex index of the slot
     * @param payment   payment given by the user
     * @return change to be returned to the user
     */
    public HashMap<Double, Integer> buyItemTask(int slotIndex, HashMap<Double, Integer> payment) {
        // check if invalid slot
        boolean invalidSlotCheck = checkIfInvalidSlot(slotIndex);
        // check if slot is empty
        boolean emptySlotCheck = checkIfSlotIsFull(slotIndex);

        if (!invalidSlotCheck || !emptySlotCheck) {
            return payment; // Return payment if slot is invalid or empty
        } else {
            // Proceed with transaction
            double itemPrice = getSlots().get(slotIndex - 1).getItemsInSlot().get(0).getPrice();
            HashMap<Double, Integer> returnChange = super.getFunds().calculateChangeToGive(itemPrice, payment);
            return returnChange;
        }
    }

    /**
     * Dispenses an Item from a Regular Vending Machine. To be called after
     * buyItemTask.
     * 
     * @param amount    amount of items to be dispensed
     * @param slotIndex index of the slot
     * @return ArrayList of Items dispensed
     */
    public ArrayList<Item> dispenseItemTask(int amount, int slotIndex) {
        Slot selectedSlot = getSlots().get(slotIndex - 1); // slots are 0-8 but user inputs 1-9
        ArrayList<Item> itemsDispensed = selectedSlot.dispenseItem(amount);
        // add transaction to history when item is dispensed
        super.getTransactionHistory().addTransactionToHistory(itemsDispensed);
        return itemsDispensed;
    }

    /**
     * Checks if the intended slotIndex is less than 0 or greater than the size of
     * the slots ArrayList.
     * If it is, it returns false. If it isn't, it returns true.
     * 
     * @param slotIndex
     * @return
     */
    private boolean checkIfInvalidSlot(int slotIndex) {
        if (slotIndex < 0 || slotIndex > getSlots().size()) {
            return false; // Invalid slot index
        }
        return true;
    }

}
