import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CashRegister {
    private HashMap<Double, Integer> change;
    private HashMap<Double, Integer> earnings;

    public CashRegister() {
        change = new HashMap<Double, Integer>();
        earnings = new HashMap<Double, Integer>();
        initializeMoney();
    }

    private void initializeMoney() {
        Double denominations[] = { 0.25, 0.50, 1.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0, 1000.0 };
        int values[] = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };

        for (int i = 0; i < denominations.length; i++) {
            change.put(denominations[i], values[i]);
            earnings.put(denominations[i], 0);
        }
    }

    /**
     * This method is called when the user buys an item from the vending machine. It
     * completes three steps:
     * Calculating the double total payment from the user, compares that to the
     * price of the item,
     * if the payment is enough, it calculates the change to give back to the user.
     * If the payment isn't enough OR the machine doesn't have enough exact change,
     * the machine returns the original payment to the user.
     * 
     * @param itemPrice the price of the item the user wants to buy
     * @param payment   is the Hashmap of the payment given by the user
     */
    public HashMap<Double, Integer> calculatePaymentTask(double itemPrice, HashMap<Double, Integer> payment) {
        // Calculate the total payment from the user based on the payment HashMap
        double totalPayment = calculateTotalPaymentFromUser(payment);
        // Check if totalPayment is enough
        if (isInputEnough(itemPrice, totalPayment)) {
            //if input is enough, check if the machine has enough exact change
            boolean machineEnoughCheck = doesMachineHaveEnoughChange(totalPayment);

            if (machineEnoughCheck) {
                // Calculate the change to give back based on the calculated payment
                double changeNeeded = totalPayment - itemPrice;
                //change within machine is imported into calculateChange
                HashMap<Double, Integer> changeToGive = calculateChange(change, changeNeeded);
                // Put the money from payment into earnings
                putUserPaymentInEarnings(payment);

                // Rearrange the change to give in the original order
                return changeToGive;
            } else {
                // If the machine doesn't have enough exact change, return the original payment
                return payment;
            }
        } else {
            // If payment is not enough, return the original payment to the user
            return payment;
        }
    }

    public double calculateTotalPaymentFromUser(HashMap<Double, Integer> payment) {
        double totalPayment = 0.0;
        for (Double pay : payment.keySet()) {
            totalPayment += pay * payment.get(pay); // multiplies the key with the value
        }
        return totalPayment;
    }

    public HashMap<Double, Integer> calculateChange(HashMap<Double, Integer> changeInMachine, double changeNeeded) {
        HashMap<Double, Integer> changeToGive = new HashMap<Double, Integer>();

        // get all available denominations and sort them from biggest to smallest
        ArrayList<Double> denominations = new ArrayList<>(changeInMachine.keySet());
        Collections.sort(denominations, Collections.reverseOrder());

        // iterate through change until nothing left
        double changeLeft = changeNeeded;
        for (Double denomination : denominations) {
            int howMuchToGiveBack = 0;

        // keep adding coins from denomination until we run out or adding this
        // denomination doesn't end up giving more change
        while (howMuchToGiveBack + 1 < changeInMachine.get(denomination) && 0 <= changeLeft - denomination) {
            // keep adding it
            changeLeft -= denomination;
            howMuchToGiveBack++;

            // whether zero or not, repopulate changeToGive Hashmap with how much
            // denomination we're giving back
            changeToGive.put(denomination, howMuchToGiveBack);
        }
    }
            if (0 < changeLeft)
            for (Double denomination : changeToGive.keySet())
                changeToGive.put(denomination, -1);

        HashMap<Double, Integer> rearrangedChange = rearrangeToOriginalOrder(changeInMachine, changeToGive);

        return rearrangedChange;
    }

    /**
     * This method is called within calculateChange.
     * @param originalOrder A copy of the previous order to use as a reference
     * @param change the change to rearrange
     * @return the rearranged change
     */
    private HashMap<Double, Integer> rearrangeToOriginalOrder(HashMap<Double, Integer> originalOrder,
            HashMap<Double, Integer> change) {
        // Initialize a new HashMap to store the rearranged change
        HashMap<Double, Integer> rearrangedChange = new LinkedHashMap<>();

        // Iterate through the denominations in the original payment order
        for (Double denomination : originalOrder.keySet()) {
            // If the change HashMap contains the current denomination
            if (change.containsKey(denomination)) {
                // Put the denomination and its count into the rearrangedChange HashMap
                rearrangedChange.put(denomination, change.get(denomination));
            }
        }

        // Return the rearranged change HashMap
        return rearrangedChange;
    }

    public void putUserPaymentInEarnings(HashMap<Double, Integer> payment) {
        for (Double pay : payment.keySet()) {
            // Iterate through the denominations in earnings and update their values
            for (Double denomination : earnings.keySet()) {
                if (denomination.equals(pay)) { // if keys match
                    earnings.put(denomination, earnings.get(denomination) + payment.get(pay));
                }
            }
        }
    }

    /**
     * This method is called within calculatePaymentTask. It checks if the payment is enough for the price of the item/s they're paying for
     * @param totalPrice total price of the item
     * @param payment double payment given by the user
     * @return true if payment is enough, false if payment is not enough
     */
    public boolean isInputEnough(double totalPrice, double payment) {
        if (payment >= totalPrice) {
            return true; // less than or equal to price
        } else {
            return false; // payment is not enough
        }
    }

    public boolean doesMachineHaveEnoughChange(double totalPrice) {
        // check if the machine has enough change to give back to the user
        double machineTotal = 0.0;
        for (Double machine : change.keySet()) {
            machineTotal += machine * change.get(machine); // multiplies the key with the value
        }

        if (machineTotal >= totalPrice) {
            return true; // less than or equal to price
        } else {
            return false; // payment is not enough
        }
    }

    public HashMap<Double, Integer> emptyCashRegister() {
        HashMap<Double, Integer> earningsToTransfer = new HashMap<>();
        for (Double denomination : earnings.keySet()) {
            earningsToTransfer.put(denomination, earnings.get(denomination)); // transfer earnings values
            earnings.put(denomination, 0); // empty earnings
        }
        return earningsToTransfer;
    }

    public void replenishChange(double denomination, int amount) {
        Double wrapped = Double.valueOf(denomination); // change the primitive denomination into a Double object

        for (Double key : change.keySet()) {
            if (key.equals(wrapped)) { // if keys match
                change.put(key, change.get(key) + amount);
            }
        }
    }

    public HashMap<Double, Integer> getChange() {
        return change;
    }

    public HashMap<Double, Integer> getEarnings() {
        return earnings;
    }
}
