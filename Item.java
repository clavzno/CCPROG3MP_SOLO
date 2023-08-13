//DONE
public class Item {
    private String name;
    private double calories;
    private double price; //all items have different prices

    public Item(String name, double calories, double price) {
        this.name = name;
        this.calories = calories;
        this.price = price; // Set the initial price
    }

    //for restock, copy item instances
    public Item(Item item) {
        this.name = item.getName();
        this.calories = item.getCalories();
        this.price = item.getPrice();
    }

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

    //when printing ArrayList of items
    @Override
    public String toString() {
        return "Item[name=" + name + ", calories=" + calories + ", price=" + price + "]";
    }
}
