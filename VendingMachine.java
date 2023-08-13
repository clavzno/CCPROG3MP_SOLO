import java.util.ArrayList;

public abstract class VendingMachine {
    private String name;
    private ArrayList<Slot> slots;
    private CashRegister funds;
    private TransactionHistory history;

    public VendingMachine(String name) {
        this.name = name;
        slots = new ArrayList<Slot>();
        funds = new CashRegister();
        history = new TransactionHistory();
    }

    public abstract boolean addSlot(int amount);
    //overridden in RegularVendingMachine and SpecialVendingMachine

    public void addItemToSlot(int slotIndex, Item item) {
        slots.get(slotIndex).addItemToSlot(item);
    }

    //getters
    public String getName() {
        return name;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public CashRegister getFunds() {
        return funds;
    }

    public TransactionHistory getHistory() {
        return history;
    }

}