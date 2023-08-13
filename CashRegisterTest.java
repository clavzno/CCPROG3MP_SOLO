import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CashRegisterTest {
    private HashMap<Double, Integer> change;
    private HashMap<Double, Integer> earnings;

    public CashRegisterTest() {
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

    /*
     * This method is called when the user buys an item from the vending machine. It
     * completes three steps:
     * Calculating the double total payment from the user, compares that to the
     * price of the item,
     * if the payment is enough, it calculates the change to give back to the user.
     * If the payment isn't enough OR the machine doesn't have enough exact change,
     * the machine returns the original payment to the user.
     * 
     * @param itemPrice the price of the item the user wants to buy
     * 
     * @param payment is the Hashmap of the payment given by the user
     */
    
   /* public HashMap<Double, Integer> calculatePaymentTask(double itemPrice, HashMap<Double, Integer> payment) {
        // payment is accepted in different denominations and change is given in
        // different denominations too

        // calculate total payment from user
        double totalPayment = calculateTotalPaymentFromUser(payment);

        // call isInputEnough to check if totalPayment is enough
        isInputEnough(itemPrice, totalPayment);

        if (isInputEnough(itemPrice, totalPayment) == true) {
            // if true, proceed to checking if the machine has enough change
            boolean machineEnoughCheck = doesMachineHaveEnoughChange(totalPayment); // import HashMap payment from User
            // if true, being calculating the change to give back to the user
            if (machineEnoughCheck == true) {
                HashMap<Double, Integer> changeToGive = calculateChangeToGive(itemPrice, payment);
                putUserPaymentInEarnings(payment); // put the money from payment in earnings
                HashMap<Double, Integer> originalOrderChangeToGive = rearrangeToOriginalOrder(payment, changeToGive);
                return originalOrderChangeToGive;
            } else {
                // if false, meaning payment is not enough OR the machine doesn't have enough
                // exact change, return the original payment to the user
                return payment;
            }
        }
    } */

    public HashMap<Double, Integer> calculatePaymentTask(double itemPrice, HashMap<Double, Integer> payment) {
        // Calculate the total payment from the user based on the payment HashMap
        double totalPayment = calculateTotalPaymentFromUser(payment);
    
        // Check if totalPayment is enough
        if (isInputEnough(itemPrice, totalPayment)) {
            // Check if the machine has enough change
            boolean machineEnoughCheck = doesMachineHaveEnoughChange(totalPayment);
    
            // Calculate the change to give back to the user
            if (machineEnoughCheck) {
                // Calculate the change to give back based on the calculated payment
                HashMap<Double, Integer> changeToGive = calculateChangeToGive(itemPrice, payment);
    
                // Put the money from payment into earnings
                putUserPaymentInEarnings(payment);
    
                // Rearrange the change to give in the original order
                HashMap<Double, Integer> originalOrderChangeToGive = rearrangeToOriginalOrder(payment, changeToGive);
                
                // Return the rearranged change to give
                return originalOrderChangeToGive;
            } else {
                // If the machine doesn't have enough exact change, return the original payment to the user
                return payment;
            }
        } else {
            // If payment is not enough, return the original payment to the user
            return payment;
        }
    }
    

    public Double calculateTotalPaymentFromUser(HashMap<Double, Integer> payment) {
        double totalPayment = 0.0;
        for (Double pay : payment.keySet()) {
            totalPayment += pay * payment.get(pay); // multiplies the key with the value
        }
        return totalPayment;
    }

    /*
     * This method is called within calculatePaymentTask. It calculates the change
     * to give back to the user. This is only called assuming the HashMap total
     * payment given by the user is enough and the machine has enough exact change
     * to give back to the user.
     */
    // This method calculates the change to give back to the user after a purchase.
    // It ensures the correct denominations are used and handles the count of each
    // denomination.
    public HashMap<Double, Integer> calculateChangeToGive(double itemPrice, HashMap<Double, Integer> payment) {
        // Initialize a HashMap to store the change to give back to the user
        HashMap<Double, Integer> changeToGive = new HashMap<>();

        // Calculate the total payment received from the user
        double totalPayment = calculateTotalPaymentFromUser(payment);
        // Calculate the total change amount to give back to the user
        double changeAmount = totalPayment - itemPrice;

        // Get the available denominations for change in descending order (largest
        // first)
        Double[] denominations = change.keySet().toArray(new Double[0]);
        Arrays.sort(denominations, Collections.reverseOrder());

        // Iterate through the denominations to calculate and assign the change to give
        for (Double denomination : denominations) {
            // Get the count of available coins/bills of the current denomination
            int availableCount = change.get(denomination);
            // Calculate the required count of coins/bills for the change
            int requiredCount = (int) (changeAmount / denomination);

            // If change of the current denomination is needed
            if (requiredCount > 0) {
                // Calculate the actual count of coins/bills to give,
                // considering availability and required count
                int countToGive = Math.min(availableCount, requiredCount);
                // Update the changeToGive HashMap with the denomination and count
                changeToGive.put(denomination, countToGive);
                // Update the remaining change amount
                changeAmount -= denomination * countToGive;
            }
        }

        // Return the HashMap containing the calculated change to give
        return changeToGive;
    }

    // This method rearranges the calculated change in the original order of
    // denominations.
    // It ensures the user receives the change in the same order they provided
    // payment.
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

    public ArrayList<Integer> emptyCashRegister() {
        ArrayList<Integer> transferredValues = new ArrayList<>(earnings.values());
        earnings.replaceAll((k, v) -> 0);
        return transferredValues;
    }

    public void replenishChange(double denomination, int amount) {
        Double wrapped = Double.valueOf(denomination); // change the primitive denomination into a Double object

        for (Double key : change.keySet()) {
            if (key.equals(wrapped)) { // if keys match
                change.put(key, change.get(key) + amount);
            }
        }
    }

}
