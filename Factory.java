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
        vendingMachines.add(regVend); //added vending machine without slots to list
        int vendingMachineIndex = vendingMachines.indexOf(regVend);
        // add slots

        //check if slotamount is valid
        boolean slotAmountCheck = regVend.checkIfSlotAmountIsValid(slotAmount);

        if (!slotAmountCheck) {
            // delete vending machine object from list
            vendingMachines.remove(vendingMachineIndex);
            return false; // something went wrong and vending machine was deleted
        }
        else {
            // add slots
            for (int i = 0; i < slotAmount; i++) {
                regVend.addSlot(1);
            }
            return true; //when done adding slots
        }
    }
    

    public boolean createSpecialVendingMachine(String name, int slotAmount) {
        return true;
    }

    public Item createItem(String name, double calories, double price) {
        return new Item(name, calories, price);
    }

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

    // should return the change
    public HashMap<Double, Integer> buyItemFromRegularVendingMachineTask(RegularVendingMachine vendingMachine,
            int slotIndex, int amountToBuy, HashMap<Double, Integer> payment) {
        // calls public HashMap<Double, Integer> buyItemTask(int slotIndex, HashMap<Double, Integer> payment)
        HashMap<Double, Integer> change = vendingMachine.buyItemTask(slotIndex, payment);
        return change;
    }

    // should return the items
    public ArrayList<Item> dispenseItemFromRegularVendingMachineTask(RegularVendingMachine vendingMachine,
            int amountToBuy, int slotIndex) {
        // calls public ArrayList<Item> dispenseItemTask(int amount, int slotIndex)
        ArrayList<Item> itemsBought = vendingMachine.dispenseItemTask(amountToBuy, slotIndex);
        return itemsBought;
    }

    public void buyItemFromSpecialVendingMachineTask(VendingMachine vendingMachine, int slotIndex, int amount) {
        // TODO
    }

    public void displenseItemFromSpecialVendingMachineTask(VendingMachine vendingMachine, int slotIndex, int amount) {
        // TODO
    }






    /********* MAINTENANCE ************/

    public void setNewItemPrice(VendingMachine vendingMachine, int slotIndex, double newPrice) {
        Slot selectedSlot = vendingMachine.getSlots().get(slotIndex);

        // Get the items in the selected slot
        ArrayList<Item> itemsInSlot = selectedSlot.getItemsInSlot();

        // Iterate through the items and set the new price
        for (Item item : itemsInSlot) {
            item.setPrice(newPrice);
        }
    }

    public void restockItemTask(VendingMachine vendingMachine, int slotIndex, int amountToRestock) {
        Slot selectedSlot = vendingMachine.getSlots().get(slotIndex);

        // Get the items in the selected slot
        ArrayList<Item> itemsInSlot = selectedSlot.getItemsInSlot();
        // Add old amount to Transaction History
        vendingMachine.setLastRestockDateTime(new Date());

        // Check if amount exceeds max capacity
        doesRestockAmountExceedCapacity(amountToRestock, selectedSlot);

        // if true, adjust amount to max capacity
        if (doesRestockAmountExceedCapacity(amountToRestock, selectedSlot) == true) {
            amountToRestock = restockAdjuster(amountToRestock, selectedSlot);
        }

        // create Copy of item
        Item itemCopy = new Item(itemsInSlot.get(0));

        // Add the items to the slot
        for (int i = 0; i < amountToRestock; i++) {
            vendingMachine.addItemToSlot(slotIndex, itemCopy);
        }

        //add new date to transaction history
        vendingMachine.setLastRestockDateTime(new Date());
    }

    private boolean doesRestockAmountExceedCapacity(int amountToRestock, Slot selectedSlot) {
        if (amountToRestock > selectedSlot.getMaxCapacity()) {
            return true; // adjust amount to max capacity
        } else {
            return false;
        }
    }

    private int restockAdjuster(int amountToRestock, Slot selectedSlot) {
        int amountExceeded = amountToRestock - selectedSlot.getMaxCapacity();
        return amountExceeded;
    }

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

    // additional
    public VendingMachine selectVendingMachine(int vendingMachineIndex) {
        return vendingMachines.get(vendingMachineIndex);
    }

}
