//DONE
public class Item {
    private String name;
    private double calories;
    private double price; //all items have different prices

    /**
     * Constructor for Item
     * @param name name of the item
     * @param calories calories of the item
     * @param price price of the item
     */
    public Item(String name, double calories, double price) {
        this.name = name;
        this.calories = calories;
        this.price = price; // Set the initial price
    }

    /**
     * Copy constructor for Item. Used in maintenance - restock.
     * @param item item to be copied
     */
    public Item(Item item) {
        //for restock, copy item instances
        this.name = item.getName();
        this.calories = item.getCalories();
        this.price = item.getPrice();
    }

    /**
     * Sets the price of the item. Used in maintenance - set new price.
     * @param price new price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    //getters
    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }

    /**
     * toString method for Item when printed in ArrayList
     * @return String representation of Item
     */
    @Override
    public String toString() {
        //when printing ArrayList of items
        return "Item[name=" + name + ", calories=" + calories + ", price=" + price + "]";
    }
}
