import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Transactions {
    private Map<String, List<String>> transactionHistoryMap;

    public Transactions() {
        transactionHistoryMap = new HashMap<>();
    }

    public void logTransaction(String accountNumber, String transaction) {
        List<String> transactionHistory = transactionHistoryMap.getOrDefault(accountNumber, new ArrayList<>());
        transactionHistory.add(transaction);
        transactionHistoryMap.put(accountNumber, transactionHistory);
    }

    public List<String> getTransactionHistory(String accountNumber) {
        return transactionHistoryMap.getOrDefault(accountNumber, new ArrayList<>());
    }

    public void printTransactionHistory(String accountNumber) {
        List<String> transactionHistory = getTransactionHistory(accountNumber);
        System.out.println("\nTransaction history for account " + accountNumber + ":");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
