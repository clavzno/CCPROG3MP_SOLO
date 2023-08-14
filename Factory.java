import java.util.ArrayList;
import java.util.Date;
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

    // for collecting vendingmachine object's earnings
    public void collectFunds(VendingMachine vendingMachine) {
        HashMap<Double, Integer> earningsTransferred = vendingMachine.getFunds().emptyCashRegister();
        for (Double denomination : earningsTransferred.keySet()) {
            factoryFunds.put(denomination, factoryFunds.get(denomination) + earningsTransferred.get(denomination));
        }
    }

    /********* VENDING MACHINE CREATION ************/

    public boolean createRegularVendingMachine(String name, int slotAmount) {
        // create new regular vending machine object
        RegularVendingMachine regVend = new RegularVendingMachine(name);
        vendingMachines.add(regVend); // added vending machine without slots to list
        int vendingMachineIndex = vendingMachines.indexOf(regVend);
        // add slots

        // check if slotamount is valid
        boolean slotAmountCheck = regVend.checkIfSlotAmountIsValid(slotAmount);

        if (!slotAmountCheck) {
            // delete vending machine object from list
            vendingMachines.remove(vendingMachineIndex);
            return false; // something went wrong and vending machine was deleted
        } else {
            // add slots
            for (int i = 0; i < slotAmount; i++) {
                regVend.addSlot(1);
            }
            return true; // when done adding slots
        }
    }

    public void createSpecialVendingMachine(String name, int slotAmount) {
        // create new SpecialVendingMachine object and add to list
        SpecialVendingMachine specVend = new SpecialVendingMachine(name);
        vendingMachines.add(specVend);
        specVend.addSlot(slotAmount); // add slots, will adjust to minimum if less than minimum
    }

    public Item createItem(String name, double calories, double price) {
        // for regular vending machine
        return new Item(name, calories, price);
    }

    public Item createAddonItem(String name, double calories, double price) {
        // for special vending machine
        return new AddonItem(name, calories, price);
    }

    public Item createComboItem(String name, double calories, double price, String preparationMessage) {
        // for specialvendingmachine
        return new ComboItem(name, calories, price, preparationMessage);
    }

    /**
     * Adds an item to the chosen vending machine
     * 
     * @param item           the item to be added
     * @param vendingmachine the vending machine to add the item to
     * @param slotIndex      the slot within the vending machine to add the item to
     * @return true if the item was added successfully, false if the item could not
     *         be added
     */
    public boolean addItemToMachine(Item item, VendingMachine vendingmachine, int slotIndex) {
        // calls public void addItemToSlot(int slotIndex, Item item) {
        boolean addItemCheck = getVendingMachines().get(vendingMachines.indexOf(vendingmachine))
                .checkIfSlotIsFull(slotIndex);
        if (addItemCheck) { // if true, proceed with adding item
            getVendingMachines().get(vendingMachines.indexOf(vendingmachine)).addItemToSlot(slotIndex, item);
        } else {
            return false; // return false if item cannot be added aka max capacity reached
        }
        return true;
    }

    /********* BUYING ITEMS ************/

    /**
     * A task that handles buying an item from a regular vending machine
     * 
     * @param vendingMachine the vending machine to buy from
     * @param slotIndex      the slot to buy from
     * @param amountToBuy    the amount to buy
     * @param payment        the payment which the user is paying with
     * @return the change that is returned from buyItemTask of
     *         RegularVendingMachines
     */
    public HashMap<Double, Integer> buyItemFromRegularVendingMachineTask(RegularVendingMachine vendingMachine,
            int slotIndex, int amountToBuy, HashMap<Double, Integer> payment) {
        //get totalPrice
        double totalPrice = vendingMachine.getSlots().get(slotIndex).getItemsInSlot().get(0).getPrice() * amountToBuy;
        HashMap<Double, Integer> change = vendingMachine.buyItemTask(payment, totalPrice);
        return change;
    }

    /**
     * A task that handles dispensing an item from a regular vending machine. Only
     * called when the user has paid the full amount.
     * 
     * @param vendingMachine the vending machine to dispense from
     * @param amountToBuy    the amount of items to get/dispense
     * @param slotIndex      the slot to get items from
     * @return the items that were dispensed
     */
    public ArrayList<Item> dispenseItemFromRegularVendingMachineTask(RegularVendingMachine vendingMachine,
            int amountToBuy, int slotIndex) {
        //slot index is fixed in main
        ArrayList<Item> itemsBought = vendingMachine.dispenseItemTask(amountToBuy, slotIndex);
        return itemsBought;
    }

    public HashMap<Double, Integer> buyItemFromSpecialVendingMachineTask(SpecialVendingMachine vendingMachine,
            ArrayList<Item> cart, HashMap<Double, Integer> payment) {
        //public HashMap<Double, Integer> buyItemTask(ArrayList<Item> cart, HashMap<Double, Integer> payment)
        HashMap<Double, Integer> change = vendingMachine.buyItemTask(cart, payment);
        return change;
    }

    public Item dispenseItemFromSpecialVendingMachineTask(SpecialVendingMachine vendingMachine,
            ArrayList<Item> cart) {
        Item combinedItem = vendingMachine.dispenseItemTask(cart);
        return combinedItem;
    }

    /********* MAINTENANCE ************/

    /**
     * A method that handles setting the new price of an item in a specific slot.
     * 
     * @param vendingMachine the chosen vending machine
     * @param slotIndex      the slot within the vending machine that holds the
     *                       items
     * @param newPrice       the new price to set the items to
     */
    public void setNewItemPrice(VendingMachine vendingMachine, int slotIndex, double newPrice) {
        Slot selectedSlot = vendingMachine.getSlots().get(slotIndex);

        // Get the items in the selected slot
        ArrayList<Item> itemsInSlot = selectedSlot.getItemsInSlot();

        // Iterate through the items and set the new price
        for (Item item : itemsInSlot) {
            item.setPrice(newPrice);
        }
    }

    /**
     * A method that handles restocking an item in a specific slot.
     * 
     * @param vendingMachine  the chosen vending machine
     * @param slotIndex       the slot within the vending machine that holds the
     *                        items
     * @param amountToRestock the amount to restock
     */
    public void restockItemTask(VendingMachine vendingMachine, int slotIndex, int amountToRestock) {
        Slot selectedSlot = vendingMachine.getSlots().get(slotIndex);

        // Get the items in the selected slot
        ArrayList<Item> itemsInSlot = selectedSlot.getItemsInSlot();
        // Add old amount to Transaction History
        vendingMachine.setLastRestockDateTime(new Date());

        // Check if amount exceeds max capacity
        boolean exceedAmountCheck = doesRestockAmountExceedCapacity(amountToRestock, selectedSlot);

        // if true, adjust amount to max capacity
        if (exceedAmountCheck == true) {
            amountToRestock = restockAdjuster(amountToRestock, selectedSlot);
        }

        // create Copy of item
        Item itemCopy = new Item(itemsInSlot.get(0));

        // Add the items to the slot
        for (int i = 0; i < amountToRestock; i++) {
            vendingMachine.addItemToSlot(slotIndex, itemCopy);
        }

        // add new date to transaction history
        vendingMachine.setLastRestockDateTime(new Date());
    }

    /**
     * A method that checks if the intended restock amount is more than the slot's
     * capacity. All slots are set to a maximum of 10.
     * 
     * @param amountToRestock the amount intended to restock
     * @param selectedSlot    the slot to restock
     * @return true if the amount exceeds the capacity, false if it does not
     */
    private boolean doesRestockAmountExceedCapacity(int amountToRestock, Slot selectedSlot) {
        if (amountToRestock > selectedSlot.getMaxCapacity()) {
            return true; // amount exceeds capacity
        } else {
            return false;
        }
    }

    /**
     * A method that adjusts the amount to restock to the max capacity of the slot.
     * 
     * @param amountToRestock the amount intended to restock
     * @param selectedSlot    the slot to restock
     * @return the new amount to restock that doesn't exceed the max capacity
     */
    private int restockAdjuster(int amountToRestock, Slot selectedSlot) {
        // adjust amount to max capacity
        int amountExceeded = selectedSlot.getMaxCapacity();
        return amountExceeded;
    }

    /**
     * A method that handles viewing the Transaction History of a vending machine.
     * Exports the summary to a file.
     * 
     * @param vendingMachine the vending machine to view the transaction history of
     */
    public void printTransactionHistory(VendingMachine vendingMachine) {
        vendingMachine.getTransactionHistory().viewTransactionHistory(vendingMachine);
        vendingMachine.getTransactionHistory().exportSummaryToFile(vendingMachine);
    }

    /********* GETTERS ************/
    public String getName() {
        return name;
    }

    public ArrayList<VendingMachine> getVendingMachines() {
        return vendingMachines;
    }

    public HashMap<Double, Integer> getFactoryFunds() {
        return factoryFunds;
    }

    public void replenishMoneyInVendingMachine(VendingMachine vendingMachine, double denomination, int amount) {
        vendingMachine.getFunds().replenishChange(denomination, amount);
    }

    /**
     * A method that selects a vending machine from the list of vending machines. To
     * be used in main for ease.
     * 
     * @param vendingMachineIndex the index of the vending machine to select
     * @return the selected vending machine
     */
    public VendingMachine selectVendingMachine(int vendingMachineIndex) {
        return vendingMachines.get(vendingMachineIndex);
    }

}
