import java.util.ArrayList;
import java.util.List;


public class TransactionHistory {
    private List<Transactions> transactions;

    public TransactionHistory() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transactions transaction) {
        transactions.add(transaction);
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void displayTransactionHistory() {
        System.out.println("\nTransaction History: ");
        for (Transactions transaction : transactions) {
            System.out.println(transaction.toString());
        }
    }
}
