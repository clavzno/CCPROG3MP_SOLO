public class SimpleCashRegister {
    private double change;
    private double earnings;

    public SimpleCashRegister() {
        this.change = 5000;
        this.earnings = 0;
    }

    public double calculatePaymentTask(double itemPrice, double payment) {
        // Calculate the total payment from the user based on the payment HashMap
        double totalPayment = payment;

        // Check if totalPayment is enough
        if (isInputEnough(itemPrice, totalPayment)) {
            // Check if the machine has enough change
            boolean machineEnoughCheck = doesMachineHaveEnoughChange(totalPayment);

            // Calculate the change to give back to the user
            if (machineEnoughCheck) {
                // Calculate the change to give back based on the calculated payment
                double changeToGive = calculateChangeToGive(itemPrice, payment);

                // Put the money from payment into earnings
                putUserPaymentInEarnings(payment);

                // Return the rearranged change to give
                return changeToGive;
            } else {
                // If the machine doesn't have enough exact change, return the original payment
                // to the user
                return payment;
            }
        } else {
            // If payment is not enough, return the original payment to the user
            return payment;
        }
    }

    private boolean doesMachineHaveEnoughChange(double totalPayment) {
        // Check if the machine has enough change to give back to the user
        if (change >= totalPayment) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isInputEnough(double itemPrice, double totalPayment) {
        // Check if the total payment is enough
        if (totalPayment >= itemPrice) {
            return true;
        } else {
            return false;
        }
    }

    private double calculateChangeToGive(double itemPrice, double payment) {
        // Calculate the total payment received from the user
        double totalPayment = payment;
        // Calculate the total change amount to give back to the user
        double changeAmount = totalPayment - itemPrice;

        // Calculate the change to give back to the user
        double changeToGive = changeAmount;

        // Update the change in the machine
        change -= changeToGive;

        // Return the change to give back to the user
        return changeToGive;
    }

    private void putUserPaymentInEarnings(double payment) {
        // Put the money from payment into earnings
        earnings += payment;
    }

    //getters
    public double getChangeInMachine() {
        return change;
    }

    public double getEarningsInMachine() {
        return earnings;
    }
}