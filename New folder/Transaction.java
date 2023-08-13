//DONE
import java.util.ArrayList;

public class Transaction {
    ArrayList<Item> itemsSold;
    int amountSold;
    double totalPrice;

    public Transaction(ArrayList<Item> items) {
        this.itemsSold = items;
        this.amountSold = items.size();
        double _totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.getPrice();
        }
        this.totalPrice = _totalPrice;
    }

    //getters
    public ArrayList<Item> getitemsSold() {
        return itemsSold;
    }

    public int getAmountSold() {
        return amountSold;
    }
}
