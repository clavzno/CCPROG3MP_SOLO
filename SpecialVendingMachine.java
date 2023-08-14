import java.util.ArrayList;
import java.util.HashMap;

public class SpecialVendingMachine extends VendingMachine {
    private ArrayList<Item> cart; // combo items are put in here

    /**
     * Constructor for SpecialVendingMachine. Creates a cart instance variable.
     * @param name name of the vending machine
     */
    public SpecialVendingMachine(String name) {
        super(name);
        cart = new ArrayList<Item>();
    }

    /**
     * Adds a slot to the Special Vending Machine. Different conditions apply because Regular Vending Machines have a maximum Slot capacity of 8, while Special Vending Machines have a minimum Slot capacity of 8.
     * @param amount amount of slots to be added
     */
    public void addSlot(int amount) {
        for (int i = 0; i < amount; i++) { // add slots
            getSlots().add(new Slot());
        }
    }
    
    /**
     * Adds an item to the cart.
     * @param item item to be added
     */
    public void addToCart(Item item) {
        cart.add(item);
    }

    /**
     * Task that handles buying an Item from a Special Vending Machine. 
     * @param cart
     * @param payment
     * @return
     */
    public HashMap<Double, Integer> buyItemTask(ArrayList<Item> cart, HashMap<Double, Integer> payment) {
        double itemPrice = 0;
        itemPrice = calculatePriceFromCartItems(cart); // calculate the price of the items in the cart
        HashMap<Double, Integer> changeToGive = super.getFunds().calculatePaymentTask(itemPrice, payment);
        return changeToGive; // Return the change to give back to the user
    }

    /**
     * Calculates total price of items in cart and returns double form of total price
     * @param cart cart to calculate price from
     * @return total price of items in cart
     */
    private double calculatePriceFromCartItems(ArrayList<Item> cart) {
        double price = 0;
        for (Item item : cart) {
            price += item.getPrice();
        }
        return price;
    }

    public Item dispenseItemTask(ArrayList<Item> cart) {
        String combinedItemName = "";
        double combinedItemCalories = 0.0;
        double combinedItemPrice = 0.0;
        
        // go through items in cart and add the name to a new item
        for (Item item : cart) {
            // go through preparations and add them to the new item
            if (item instanceof ComboItem) {
                String prepComboMsg = ((ComboItem) item).getPreparationMessage();
                System.out.println(prepComboMsg);
            }
            if (item instanceof AddonItem) {
                String prepAddonMsg = ((AddonItem) item).getPreparationMessage();
                System.out.println(prepAddonMsg);
            }
            
            // add names of items to new item
            String itemName = item.getName();
            combinedItemName += " with " + itemName;
    
            double itemCalories = item.getCalories();
            combinedItemCalories += itemCalories;
    
            double itemPrice = item.getPrice();
            combinedItemPrice += itemPrice;
        }
        
        // return the new item
        Item combinedItem = new Item(combinedItemName, combinedItemCalories, combinedItemPrice);
        return combinedItem;
    }

    // getters
    public ArrayList<Item> getCart() {
        return cart;
    }
}
