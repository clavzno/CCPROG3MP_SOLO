//DONE
public class Item {
    private String name;
    private double calories;
    private double price;

    public Item(String name, double calories, double price) {
        this.name = name;
        this.calories = calories;
        this.price = price;
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
}
