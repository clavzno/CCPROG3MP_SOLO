
public abstract class Item {
    private String itemName;
    private double itemPrice;
    private int itemCals;

    public Item(String itemName, double itemPrice, int itemCals) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCals = itemCals;
    }

    public void setNewPrice(double newPrice) {
        this.itemPrice = newPrice;
    }

    public String getItemName() {
        return this.itemName;
    }

    public double getItemPrice() {
        return this.itemPrice;
    }

    public double getItemCals() {
        return this.itemCals;
    }
}
