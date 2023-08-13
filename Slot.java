import java.util.ArrayList;

public class Slot {
    private ArrayList<Item> itemsInSlot;
    private static final int maxCapacity = 10; 
    TransactionHistory history;

    public Slot(){
        itemsInSlot = new ArrayList<Item>();
        this.history = new TransactionHistory();
    }

    public void addItem(Item item){
        itemsInSlot.add(item);
    }

    //this is only for upon creation and adding of items, not for restock
    public void makeAllSlotsFullOfItem() {
        int currentSize = itemsInSlot.size();
        int itemsToAdd = maxCapacity - currentSize;
    
        for (int i = 0; i < itemsToAdd; i++) {
            Item item = new Item(itemsInSlot.get(0)); // Copy item instance
            itemsInSlot.add(item);
        }
    }
    

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
