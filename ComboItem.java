public class ComboItem extends Item {
    private String preparationMessage;

    public ComboItem(String name, double calories, double price, String preparationMessage) {
        super(name, calories, price);
        this.preparationMessage = preparationMessage;
    }

    public String getPreparationMessage() {
        return preparationMessage;
    }
}
