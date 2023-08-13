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
        
        return payment;
    }

    // getters
    public ArrayList<Item> getCart() {
        return cart;
    }
}
