import java.util.HashMap;

public class RegularVendingMachine extends VendingMachine {
    private int maxSlots;

    public RegularVendingMachine(String name) {
        super(name);
        this.maxSlots = 8;
    }

    public boolean addSlot(int amount) {
        for (int i = 0; i < amount; i++) {
            if (getSlots().size() < maxSlots) {
                getSlots().add(new Slot());
            } else {
                return false; //if slot is full
            }
        }
        return true;
    }

    public HashMap<Double, Integer> buyItemTask(int slotIndex, HashMap<Double, Integer> payment) {
        //CHOOSING ITEM
        // check if invalid slot
        boolean invalidSlotCheck = checkIfInvalidSlot(slotIndex);
        // check if slot is empty
        boolean emptySlotCheck = checkIfSlotEmpty(slotIndex);
        //if both are true, proceed with import itemprice into cashregister to check if payment is enough and to calculate change
        if (invalidSlotCheck == true || emptySlotCheck == false) {
            return payment;
        }
        else if (invalidSlotCheck == false || emptySlotCheck == true) {
            return payment;
        }
        else {
            //proceed with transaction
            HashMap<Double, Integer> returnChange = super.getFunds().calculatePaymentTask(slotIndex, payment);
            return returnChange;
        }
    }

    private boolean checkIfInvalidSlot(int slotIndex) {
        if (slotIndex < 0 || slotIndex > getSlots().size()) {
            return true; // Invalid slot index
        }
        return false;
    }

    private boolean checkIfSlotEmpty(int slotIndex) {
        Slot selectedSlot = getSlots().get(slotIndex - 1); //slots are 0-8 but user inputs 1-9
        if (selectedSlot.getItemsInSlot().isEmpty()) { //if slot is empty
            return false; // Slot is empty
        }
        return true;
    }

}
