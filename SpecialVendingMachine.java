import java.util.ArrayList;
import java.util.HashMap;
public class SpecialVendingMachine extends VendingMachine {
    private ArrayList<Item> cart; //combo items are put in here

    public SpecialVendingMachine(String name) {
        super(name);
        cart = new ArrayList<Item>();
    }

    public void addSlot(int amount){
        //TODO
    }

    public HashMap<Double, Integer> buyItemTask(int slotIndex, HashMap<Double, Integer> payment){
        //TODO
        return payment;
    }

    //getters
    public ArrayList<Item> getCart() {
        return cart;
    }
}
