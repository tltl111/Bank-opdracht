import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class Bank {

    DecimalFormat accountNumberdf = new DecimalFormat("0000000000");
    DecimalFormat balanceFormat = new DecimalFormat("0.00");

    private Map<String, String> personAccountMap;
    private Map<String, Integer> countryCodeCounters;
    private List<BankAccount> accounts;
    
public Bank() {
    personAccountMap = new HashMap<>();
    countryCodeCounters = new HashMap<>();
    accounts = new ArrayList<>();
}

    public void createAccount(String countryCode, double initialBalance, Person accountOwner) {
        int accountCounter = countryCodeCounters.getOrDefault(countryCode, 1);
        countryCodeCounters.put(countryCode, accountCounter + 1);

        String accountNumber = generateAccountNumber(countryCode, accountCounter);
        String currency = getCurrency(countryCode);
        
        BankAccount account = new BankAccount(accountNumber, currency, initialBalance, accountOwner);
        accounts.add(account);
        personAccountMap.put(accountOwner.getName(), accountNumber);

        String transaction = "Account created: " + account.getAccountNumber() +
                             " with balance: " + balanceFormat.format(account.getBalance()) +
                             " " + account.getCurrency();
        account.getTransactions().logTransaction(accountNumber, transaction);
        System.out.println(transaction);
    }

    public String generateAccountNumber(String countryCode, int accountCounter) {
        String counterString = accountNumberdf.format(accountCounter);
        return countryCode + counterString;
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
    
    public BankAccount getAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String getAccountNumberForPerson(String personName) {
        return personAccountMap.get(personName);
    }

    public void printBalanceForPerson(String personName) {
        String accountNumber = getAccountNumberForPerson(personName);
        BankAccount account = getAccount(accountNumber);
        System.out.println("\nBalance for " + personName + ": " + balanceFormat.format(account.getBalance()) +
                           " " + account.getCurrency());
    }

    public void deposit(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount, account.getCurrency());
        } else {
            System.out.println("Account not found.");
        }
    }
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount, account.getCurrency());
        } else {
            System.out.println("Account not found");
        }
    }
    public void transfer(String accountNumberFrom, double amount, String accountNumberTo) {
        BankAccount accountFrom = getAccount(accountNumberFrom);
        BankAccount accountTo = getAccount(accountNumberTo);
        if (accountFrom != null && accountTo != null) {
            String currency = accountFrom.getCurrency();

            if (amount > accountFrom.getBalance()) {
                System.out.println("\nCan't withdraw more then what is on the account.");
                System.out.println("Current balance of: " + accountFrom.getBalance() + " on account: " + accountNumberFrom);
            } else {
                accountFrom.withdraw(amount, currency);
                accountTo.deposit(amount, currency);
            }
    
        } else {
            System.out.println("Account not found");
        }
    }

    public void printHistory(String accountNumber) {
        BankAccount account = getAccount(accountNumber);
        Transactions transactions = account.getTransactions();
        transactions.printTransactionHistory(accountNumber);
    }

    public void findRichestPerson() {
        String richestPerson = null;
        double maxBalance = Double.MIN_VALUE;
        String currency = null;

        for (BankAccount account : accounts) {
            double balance = account.getBalance();
            if (balance > maxBalance) {
                maxBalance = balance;
                richestPerson = account.getAccountOwner().getName();
                currency = account.getCurrency();
            }
        }
        System.out.println("\nThe person with the highest balance on their account is " + richestPerson);
        System.out.println("Balance: " + balanceFormat.format(maxBalance) + " "  + currency);
    }
}
