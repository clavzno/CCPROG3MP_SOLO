
public abstract class Item {
    private String itemName;
    private double itemCals;
    private double itemPrice;

    public Item(String itemName, double itemCals, double itemPrice) {
        this.itemName = itemName;
        this.itemCals = itemCals;
        this.itemPrice = itemPrice;
    }
}
