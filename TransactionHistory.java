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
     * @param Transaction The transaction to add to this TransactionHistory!
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

    public void setLastRestockDateTime(Date lastRestockDateTime) {
        this.lastRestockDateTime = lastRestockDateTime;
    }

    public void exportSummaryToFile() {
        //TODO:
        /* FORMAT
         * VENDING MACHINE (Name) SUMMARY
         * Last Restock Date: (Date) 
         * 1. Transaction 1 - Item name (Amount) PHP (Total)
         * 2. Transaction 2 - Item name (Amount) PHP (Total)
         * ---
         * Total Sales: PHP (Total Sales)
         */
    }

    //getters
}
