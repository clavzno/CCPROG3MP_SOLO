public class AddonItem extends Item {
    private String preparationMessage;

    public AddonItem(String name, double calories, double price) {
        super(name, calories, price);
        this.preparationMessage = "Adding the " + name + " to your order.";
    }

    public String getPreparationMessage() {
        return preparationMessage;
    }
}
