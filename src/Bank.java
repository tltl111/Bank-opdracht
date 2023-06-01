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
    private List<SavingsAccount> accountsSave;
    private List<LoanAccount> accountsLoan;
    
public Bank() {
    personAccountMap = new HashMap<>();
    countryCodeCounters = new HashMap<>();
    accounts = new ArrayList<>();
    accountsSave = new ArrayList<>();
    accountsLoan = new ArrayList<>();
}

    public void createAccount(String countryCode, double initialBalance, Person accountOwner) {
        int accountCounter = countryCodeCounters.getOrDefault(countryCode, 1);
        countryCodeCounters.put(countryCode, accountCounter + 1);

        String accountNumber = generateAccountNumber(countryCode, accountCounter, "B");
        String accountNumberSavings = generateAccountNumber(countryCode, accountCounter, "S");
        String accountNumberLoan = generateAccountNumber(countryCode, accountCounter, "L");
        String currency = getCurrency(countryCode);

        BankAccount account = new BankAccount(accountNumber, currency, initialBalance, accountOwner);
        SavingsAccount accountSave = new SavingsAccount(accountNumberSavings, currency, 0.0, accountCounter, accountOwner);
        LoanAccount accountLoan = new LoanAccount(accountNumberLoan, currency, 0.0, accountCounter, accountOwner);

        accounts.add(account);
        accountsSave.add(accountSave);
        accountsLoan.add(accountLoan);
        personAccountMap.put(accountOwner.getName(), accountNumber);

        String transaction = "Account created: " + account.getAccountNumber() +
                             " with balance: " + balanceFormat.format(account.getBalance()) +
                             " " + account.getCurrency();
        account.getTransactions().logTransaction(accountNumber, transaction);
        System.out.println(transaction);
    }

    public String generateAccountNumber(String countryCode, int accountCounter, String accountType) {
        String counterString = accountNumberdf.format(accountCounter);
        return countryCode + counterString + accountType;
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
    
    public BankAccount getBankAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    public SavingsAccount getSavingsAccount(String accountNumber) {
        for (SavingsAccount account : accountsSave) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    public LoanAccount getLoanAccount(String accountNumber) {
        for (LoanAccount account : accountsLoan) {
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
        BankAccount account = getBankAccount(accountNumber);
        System.out.println("\nBalance for " + personName + ": " + balanceFormat.format(account.getBalance()) +
                           " " + account.getCurrency());
    }

    public void deposit(String accountNumber, double amount) {
        BankAccount account = getBankAccount(accountNumber);
        if (account != null) {
            account.deposit(amount, account.getCurrency());
        } else {
            System.out.println("Account not found.");
        }
    }
    public void depositSavings(String accountNumber, double amount) {
        SavingsAccount accountSave = getSavingsAccount(accountNumber);
        if (accountSave != null) {
            accountSave.deposit(amount, accountSave.getCurrency());
        } else {
            System.out.println("Account not found.");
        }
    }
    public void depositLoan(String accountNumber, double amount) {
        LoanAccount accountLoan = getLoanAccount(accountNumber);
        if (accountLoan != null) {
            accountLoan.deposit(amount, accountLoan.getCurrency());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        BankAccount account = getBankAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount, account.getCurrency());
        } else {
            System.out.println("Account not found");
        }
    }
    public void transfer(String accountNumberFrom, double amount, String accountNumberTo) {
        BankAccount accountFrom = getBankAccount(accountNumberFrom);
        BankAccount accountTo = getBankAccount(accountNumberTo);
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
        BankAccount account = getBankAccount(accountNumber);
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
