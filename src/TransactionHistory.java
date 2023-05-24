import java.util.ArrayList;
import java.util.List;


public class TransactionHistory {
    private List<Transaction> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void displayTransactionHistoy() {
        System.out.println("\nTransaction History: ");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }
}
