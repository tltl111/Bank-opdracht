import java.text.DecimalFormat;


public class Account {
    private String accountNumber;
    private double balance;
    private String currency;
    private TransactionHistory transactionHistory;

    DecimalFormat df = new DecimalFormat("#.00");

    public Account(String accountNumber, double initalBalance, String currency) {
        this.accountNumber = accountNumber;
        this.balance = initalBalance;
        this.currency = currency;
        this.transactionHistory = new TransactionHistory();
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public double getBalance() {
        return balance;
    }
    public String getCurrency() {
        return currency;
    }

    public void deposit(double amount, String currency) {
        if (this.currency.equals(currency)) {
            balance += amount;
            System.out.println("Succesfully deposited " + formatAmount(amount, currency) +
            " into account: " + accountNumber);
            System.out.println("New balance of " + accountNumber + ": " + formatAmount(balance, this.currency) + "\n");
        } else {
        CurrencyConverter converter = new CurrencyConverter();
        double convertedAmount = converter.convert(amount, currency, this.currency);
        balance += convertedAmount;
        System.out.println("Successfully deposited " + formatAmount(amount, currency) +
                    " (converted to " + formatAmount(convertedAmount, this.currency) +
                    ") into account: " + accountNumber);
        System.out.println("New balance of " + accountNumber + ": " + formatAmount(balance, this.currency) + "\n");
        }
        Transactions depositTransaction = new Transactions(Transactions.TransactionType.DEPOSIT, accountNumber, amount, currency, true);
        transactionHistory.addTransaction(depositTransaction);
    }

    public void withdraw(double amount, String currency) {
        boolean successful = false;
        
        if (this.currency.equals(currency)) {
            if (balance >= amount) {
                balance -= amount;
                successful = true;
                System.out.println("Successfully withdrew " + formatAmount(amount, currency) +
                        " from account: " + accountNumber);
                System.out.println("New balance of " + accountNumber + ": " + formatAmount(balance, this.currency) + "\n");
            } else {
                System.out.println("Unsuccessful attempt to withdraw " + formatAmount(amount, currency) +
                        " from account: " + accountNumber);
                System.out.println("Insufficient balance on account: " + accountNumber + "\n");
            }
        } else {
            CurrencyConverter converter = new CurrencyConverter();
            double convertedAmount = converter.convert(amount, currency, this.currency);
            if (balance >= convertedAmount) {
                balance -= convertedAmount;
                successful = true;
                System.out.println("Successfully withdrew " + formatAmount(amount, currency) +
                        " (converted to " + formatAmount(convertedAmount, this.currency) +
                        ") from account: " + accountNumber);
                System.out.println("New balance of " + accountNumber + ": " + formatAmount(balance, this.currency) + "\n");
            } else {
                System.out.println("Unsuccessful attempt to withdraw " + formatAmount(amount, currency) +
                        " (converted to " + formatAmount(convertedAmount, this.currency) +
                        ") from account: " + accountNumber);
                System.out.println("Insufficient balance on account: " + accountNumber + "\n");
            }
        }
        Transactions withdrawalTransaction = new Transactions(Transactions.TransactionType.WITHDRAWAL, accountNumber, amount, currency, successful);
        transactionHistory.addTransaction(withdrawalTransaction);
    }

    public String printBalanceInCurrency(String targetCurrency) {
        CurrencyConverter converter = new CurrencyConverter();
        double convertedBalance = converter.convert(balance, currency, targetCurrency);
        return "Balance for account " + accountNumber + " in " + targetCurrency + ": " + formatAmount(convertedBalance, targetCurrency);
    }

    public String getBalanceWithCurrency() {
        return "Balance: " + formatAmount(balance, currency);
    }

    public String formatAmount(double amount, String currency) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(amount) + " " + currency;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", " + getBalanceWithCurrency();
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }
}
