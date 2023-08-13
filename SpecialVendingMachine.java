import java.util.ArrayList;
import java.util.HashMap;

public class SpecialVendingMachine extends VendingMachine {
    private ArrayList<Item> cart; // combo items are put in here

    public SpecialVendingMachine(String name) {
        super(name);
        cart = new ArrayList<Item>();
    }

    public void addSlot(int amount) {
        for (int i = 0; i < amount; i++) { // add slots
            getSlots().add(new Slot());
        }
    }
    

    public void addToCart(Item item) {
        cart.add(item);
    }

    public HashMap<Double, Integer> buyItemTask(ArrayList<Item> cart, HashMap<Double, Integer> payment) {

        return payment;
    }

    // getters
    public ArrayList<Item> getCart() {
        return cart;
    }
}
