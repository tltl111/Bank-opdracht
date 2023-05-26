import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;


public class BankAccount {
    private String accountNumber;
    private String currency;
    private double balance;
    private Transactions transactions;

    private Map<String, Double> conversionRates;

    public BankAccount(String accountNumber, String currency, double balance) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
        currencyConverter();
        transactions = new Transactions();
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public String getCurrency() {
        return currency;
    }
    public double getBalance() {
        return balance;
    }
    public Transactions getTransactions() {
        return transactions;
    }

    public void deposit(double amount, String currency) {
        if (this.currency.equals(currency)) {
            balance += amount;
            String transaction = "Deposited " + formatAmount(amount, currency) +
                    " into account: " + accountNumber;
            transactions.logTransaction(accountNumber, transaction);
            System.out.println("Succesfully deposited " + formatAmount(amount, currency) +
                    " into account: " + accountNumber);
            System.out.println("New balance of " + accountNumber + ": " + 
                    formatAmount(balance, this.currency) + "\n");
    } else {
        double convertedAmount = convert(amount, currency, this.currency);
        balance += convertedAmount;
        String transaction = "Deposited " + formatAmount(amount, currency) +
                    " (converted to " + formatAmount(convertedAmount, this.currency) +
                    ") into account: " + accountNumber;
        transactions.logTransaction(accountNumber, transaction);
        System.out.println("Successfully deposited " + formatAmount(amount, currency) +
                    " (converted to " + formatAmount(convertedAmount, this.currency) +
                    ") into account: " + accountNumber);
        System.out.println("New balance of " + accountNumber + ": " + 
                    formatAmount(balance, this.currency) + "\n");
        }
    }

    public void withdraw(double amount, String currency) {
        if (this.currency.equals(currency)) {
            if (balance >= amount) {
                balance -= amount;
                String transaction = "Withdrawn " + formatAmount(amount, currency) +
                        " from account: " + accountNumber;
                transactions.logTransaction(accountNumber, transaction);
                System.out.println("Successfully withdrew " + formatAmount(amount, currency) +
                        " from account: " + accountNumber);
                System.out.println("New balance of " + accountNumber + ": " + 
                        formatAmount(balance, this.currency) + "\n");
            } else {
                System.out.println("Unsuccessful attempt to withdraw " + formatAmount(amount, currency) +
                        " from account: " + accountNumber);
                System.out.println("Insufficient balance on account: " + accountNumber + "\n");
            }
        } else {
            double convertedAmount = convert(amount, currency, this.currency);
            if (balance >= convertedAmount) {
                balance -= convertedAmount;
                String transaction = "Withdrawn " + formatAmount(amount, currency) +
                        " (converted to " + formatAmount(convertedAmount, this.currency) +
                        ") from account: " + accountNumber;
                transactions.logTransaction(accountNumber, transaction);
                System.out.println("Successfully withdrew " + formatAmount(amount, currency) +
                        " (converted to " + formatAmount(convertedAmount, this.currency) +
                        ") from account: " + accountNumber);
                System.out.println("New balance of " + accountNumber + ": " + 
                        formatAmount(balance, this.currency) + "\n");
            } else {
                System.out.println("Unsuccessful attempt to withdraw " + formatAmount(amount, currency) +
                        " (converted to " + formatAmount(convertedAmount, this.currency) +
                        ") from account: " + accountNumber);
                System.out.println("Insufficient balance on account: " + accountNumber + "\n");
            }
        }
    }

    public String printBalanceInCurrency(String targetCurrency) {
        double convertedBalance = convert(balance, currency, targetCurrency);
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

    public void currencyConverter() {
        conversionRates = new HashMap<>();

        conversionRates.put("EUR", 1.0);
        conversionRates.put("USD", 1.12);
        conversionRates.put("GBP", 0.89);
        conversionRates.put("AFN", 93.21);
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        double conversionRateFrom = conversionRates.getOrDefault(fromCurrency, 1.0);
        double conversionRateTo = conversionRates.getOrDefault(toCurrency, 1.0);
        return amount * (conversionRateTo / conversionRateFrom);
    }
}
