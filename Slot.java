import java.util.ArrayList;

public class Slot {
    private ArrayList<Item> itemsInSlot;
    private static final int maxCapacity = 10; 

    /**
     * Constructor for Slot. Creates an ArrayList of Items and a TransactionHistory instance.
     */
    public Slot(){
        itemsInSlot = new ArrayList<Item>();
    }

    /**
     * Adds an item to the slot.
     * @param item item to be added
     */
    public void addItem(Item item){
        itemsInSlot.add(item);
    }

    /**
     * During creation of a VendingMachine, this method is called to fill the Slot with items so that it is full.
     */
    public void makeAllSlotsFullOfItem() {
            //this is only for upon creation and adding of items, not for restock
        int currentSize = itemsInSlot.size();
        int itemsToAdd = maxCapacity - currentSize;
    
        for (int i = 0; i < itemsToAdd; i++) {
            Item item = new Item(itemsInSlot.get(0)); // Copy item instance
            itemsInSlot.add(item);
        }
    }
    
    /**
     * Handles dispensing of items from the slot.
     * @param amount amount of items to be dispensed
     * @return ArrayList of items dispensed
     */
    public ArrayList<Item> dispenseItem(int amount) {
        ArrayList<Item> itemsDispensed = new ArrayList<Item>();
        for (int i = 0; i < itemsInSlot.size(); i++) {
            if (itemsInSlot.size() == amount) { 
                Item item = itemsInSlot.get(i); //item is retrieved from arraylist
                itemsInSlot.remove(i); //item is removed from arraylist
                itemsDispensed.add(item); //item is added to arraylist of items dispensed
            }
        }
        return itemsDispensed;
    }

    /**
     * Changes the price of the item in the slot.
     * @param newPrice new price of the item
     */
    public void changeItemPrice(double newPrice){
        for (int i = 0; i < itemsInSlot.size(); i++) {
            itemsInSlot.get(i).setPrice(newPrice);
        }
    }

    //getters
    public ArrayList<Item> getItemsInSlot() {
        return itemsInSlot;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
