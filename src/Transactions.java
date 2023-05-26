import java.text.DecimalFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Transactions {
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
    private boolean successful;

    private DecimalFormat df = new DecimalFormat("#.00");

    public Transactions(TransactionType type, String accountNumber, double amount, String currency, boolean successful) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = new Date();
        this.successful = successful;
    }

    @Override
    public String toString() {
        String status = successful ? "Successful" : "Unsuccessful";
        return "Transaction: " + type +
                ", Amount: " + formatAmount(amount) +
                ", Currency: " + currency +
                ", Timestamp: " + timestamp +
                ", Status: " + status;
    }

    private String formatAmount(double amount) {
        return df.format(amount) + " " + currency;
    }
}
