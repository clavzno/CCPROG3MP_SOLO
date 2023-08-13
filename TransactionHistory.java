import java.io.BufferedWriter;
//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class TransactionHistory {
    ArrayList<Transaction> transactions;
    Date lastRestockDateTime;
    double totalSalesEarnings;

    public TransactionHistory() {
        transactions = new ArrayList<Transaction>();
        lastRestockDateTime = new Date();
        totalSalesEarnings = 0;
    }

    /**
     * Return the transactions sales.
     * 
     * @return The total sales earnings
     */
    public double getTotalSalesEarnings() {
        double totalSalesEarnings = 0;
        for (Transaction transaction : transactions) {
            totalSalesEarnings += transaction.totalPrice;
        }
        return totalSalesEarnings;
    }

    /**
     * Add a transaction to history.
     * 
     * @param Transaction The transaction to add to this TransactionHistory.
     */
    public void addTransactionToHistory(Transaction transaction) {
        transactions.add(transaction);
        totalSalesEarnings += transaction.totalPrice;
    }

    /**
     * Add a transaction to history.
     * 
     * @param items [ArrayList<Item>] The items associated with this transaction.
     */
    public void addTransactionToHistory(ArrayList<Item> items) {
        Transaction transaction = new Transaction(items);
        this.addTransactionToHistory(transaction);
    }

    /**
     * During restock, the last restock date is reset to the current date and time. Clears old transaction history.
     * @param lastRestockDateTime the date and time of the last restock
     */
    public void setLastRestockDateTime(Date lastRestockDateTime) {
        this.lastRestockDateTime = lastRestockDateTime;
        // clear old transactions because date has been reset
        transactions.clear();
    }

    /**
     * View the transaction history of the vending machine. Shows the Transaction History within the terminal.
     * @param vendingMachine 
     */
    public void viewTransactionHistory(VendingMachine vendingMachine){
        System.out.println("VENDING MACHINE (" + vendingMachine.getName() + ") SUMMARY");
        System.out.println("Last Restock Date: " + lastRestockDateTime.toString());

        int transactionNumber = 1;
        for (Transaction transaction : transactions) {
            System.out.print(transactionNumber + ". Transaction - ");

            for (Item item : transaction.getitemsSold()) {
                System.out.print(item.getName() + " (" + item.getPrice() + " PHP) ");
            }

            System.out.println("- Total: " + transaction.totalPrice + " PHP");
            transactionNumber++;
        }

        System.out.println("---");
        System.out.println("Total Sales: PHP " + getTotalSalesEarnings());
    }

    /**
     * Exports the transaction History to a file called Summary.txt within Summaries folder.
     * @param vendingMachine vending machine to export the transaction history from
     */
    public void exportSummaryToFile(VendingMachine vendingMachine) {
        viewTransactionHistory(vendingMachine);
        System.out.println("Exporting to file...");
        String filePath = "Summaries\\Summary.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("VENDING MACHINE (" + vendingMachine.getName() + ") SUMMARY");
            writer.newLine();
            writer.write("Last Restock Date: " + lastRestockDateTime.toString());
            writer.newLine();

            int transactionNumber = 1;
            for (Transaction transaction : transactions) {
                writer.write(transactionNumber + ". Transaction - ");

                for (Item item : transaction.getitemsSold()) {
                    writer.write(item.getName() + " (" + item.getPrice() + " PHP) ");
                }

                writer.write("- Total: " + transaction.totalPrice + " PHP");
                writer.newLine();
                transactionNumber++;
            }

            writer.write("---");
            writer.newLine();
            writer.write("Total Sales: PHP " + getTotalSalesEarnings());
            System.out.println("Summary exported to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
