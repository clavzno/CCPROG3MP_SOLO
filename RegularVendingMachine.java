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
     * Task that handles buying an item. To be called before dispenseItemTask. 
     * Conditions were removed due to bugs.
     * Called by factory.buyItemFromRegularVendingMachineTask and calls CashRegister.calculatePaymentTask.
     * @param slotIndex 
     * @param payment
     * @param itemPrice
     * @return
     */
    public HashMap<Double, Integer> buyItemTask(HashMap<Double, Integer> payment, double itemPrice) {
        /*  // check if invalid slot
        boolean invalidSlotCheck = checkIfInvalidSlot(slotIndex);
        // check if slot is empty
        boolean emptySlotCheck = checkIfSlotIsFull(slotIndex);

        if (!invalidSlotCheck && emptySlotCheck) {
            return payment; // Return the payment back to the user
        } else if (invalidSlotCheck && !emptySlotCheck) {
            return payment; // Return the payment back to the user
        } else { */
            //(invalidSlotCheck && emptySlotCheck)
            // if both are true, Proceed with transaction
            HashMap<Double, Integer> changeToGive = super.getFunds().calculatePaymentTask(itemPrice, payment);
            return changeToGive; // Return the change to give back to the user
        // } 
    }

    /**
     * Dispenses an Item from a Regular Vending Machine. To be called after
     * buyItemTask.
     * 
     * @param amount    amount of items to be dispensed
     * @param slotIndex index of the slot
     * @return ArrayList of Items dispensed
     */
    public ArrayList<Item> dispenseItemTask(int amount, int fixedSlotIndex) {
        // slot Index is already fixed in main
        Slot selectedSlot = getSlots().get(fixedSlotIndex); // Adjust user input index to match 0-based array index
        ArrayList<Item> itemsDispensed = selectedSlot.dispenseItem(amount);
        // add transaction to history when item is dispensed
        Transaction newTransaction = new Transaction(itemsDispensed);
        super.getTransactionHistory().addTransactionToHistory(newTransaction);
        
        for (Item item : itemsDispensed) {
            System.out.println("REGULAR VENDING MACHINE");
            System.out.println(item); // Calls the toString method of the Item class
        }
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
    /* private boolean checkIfInvalidSlot(int slotIndex) {
        if (slotIndex < 0 || slotIndex > getSlots().size()) {
            return false; // Invalid slot index
        }
        return true;
    } */

    //checkIfSlotIsFull removed

}
