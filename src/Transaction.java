import java.text.DecimalFormat;
import java.util.Date;

public class Transaction {
    public enum TransactionType {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFER
    }

    private TransactionType type;
    private String accountNumber;
    private double amount;
    private String currency;
    private Date timestamp;

    private DecimalFormat df = new DecimalFormat("#.00");

    public Transaction(TransactionType type, String accountNumber, double amount, String currency) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return "Transaction: " + type +
                ", Amount: " + formatAmount(amount) +
                ", Currency: " + currency +
                ", Timestamp: " + timestamp;
    }

    private String formatAmount(double amount) {
        return df.format(amount) + " " + currency;
    }
}
