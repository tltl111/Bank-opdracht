import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bank {

    private Map<String, Integer> countryCodeCounters;
    private List<Account> accounts;

    public Bank() {
        countryCodeCounters = new HashMap<>();
        accounts = new ArrayList<>();
    }

    public void createAccount(String countryCode, double initialBalance) {
        int accountCounter = countryCodeCounters.getOrDefault(countryCode, 1);
        String accountNumber = generateAccountNumber(countryCode, accountCounter);
        String currency = getCurrency(countryCode);
        Account account = new Account(accountNumber, initialBalance, currency);
        accounts.add(account);
        countryCodeCounters.put(countryCode, accountCounter + 1);
        printAccountCreationMessage(account);
    }

    private String getCurrency(String countryCode) {
        switch(countryCode) {
            case "NL":
                return "EUR";
            case "UK":
                return "GBP";
            case "US":
                return "USD";
            case "AF":
                return "AFN";
            default:
                return "EUR";
        }
    }
    
    private String generateAccountNumber(String countrycode, int accountCounter) {
        DecimalFormat df = new DecimalFormat("00");
        String counterString = df.format(accountCounter);
        return countrycode + counterString + "ABCD1234567890";
    }

    private void printAccountCreationMessage(Account account) {
        DecimalFormat df = new DecimalFormat("#.00");
        String message = "Account created: " + account.getAccountNumber() + 
            " with balance: " + df.format(account.getBalance()) + 
            " " + account.getCurrency();
            System.out.println(message);
    }

    public void deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount, getCurrency(accountNumber));
        } else {
            System.out.println("Account not found");
        }
    }
    public void withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount, getCurrency(accountNumber));
        } else {
            System.out.println("Account not found");
        }
    }
    public void transfer(String accountNumberFrom, double amount, String accountNumberTo) {
        Account accountFrom = getAccount(accountNumberFrom);
        Account accountTo = getAccount(accountNumberTo);

        if (accountFrom != null && accountTo != null) {
            String currency = accountFrom.getCurrency();

            if (accountFrom.getBalance() >= amount) {
                accountFrom.withdraw(amount, currency);
                accountTo.deposit(amount, currency);
                System.out.println("Succesfully transfeerd " + accountFrom.formatAmount(amount, currency) +
                    " from account " + accountNumberFrom + " to account " + accountNumberTo);
            } else {
                System.out.println("Insufficient balance in account " + accountNumberFrom +
                " to transfer " + accountFrom.formatAmount(amount, currency));
            }
        } else {
            System.out.println("One or both accounts not found");
        }
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
