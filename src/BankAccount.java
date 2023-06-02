import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BankAccount {

    private String accountNumber;
    private String currency;
    private double balance;
    protected Transactions transactions;
    private Person accountOwner;

    private Map<String, Double> conversionRates;

    public BankAccount(String accountNumber, String currency, double balance, Person accountOwner) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.balance = balance;
        currencyConverter();
        transactions = new Transactions();
        this.accountOwner = accountOwner;
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

    protected Transactions getTransactions() {
        return transactions;
    }

    public Person getAccountOwner() {
        return accountOwner;
    }

    public void deposit(double amount, String currency) {
        if (this.currency.equals(currency)) {
            this.balance += amount;
            String transaction = "Deposited " + formatAmount(amount, currency) +
                    " on account: " + this.accountNumber;
            transactions.logTransaction(accountNumber, transaction);

            System.out.println("\nDeposited " + formatAmount(amount, currency) +
                    " on account: " + this.accountNumber);
            System.out.println("New balance of : " + formatAmount(getBalance(), currency) +
                    " on account: " + this.accountNumber);
        } else {
            double convertedAmount = convert(amount, currency, this.currency);
            this.balance += convertedAmount;
            String transaction = "Deposited " + formatAmount(amount, currency) +
                    " on account: " + this.accountNumber;
            transactions.logTransaction(accountNumber, transaction);

            System.out.println("\nDeposited " + formatAmount(amount, currency) +
                    " (converted to " + formatAmount(convertedAmount, this.currency) +
                    ") on account: " + this.accountNumber);
            System.out.println("New balance of : " + formatAmount(getBalance(), currency) +
                    " on account: " + this.accountNumber);
        }
    }

    public void withdraw(double amount, String currency) {
        if (this.currency.equals(currency)) {
            if (amount > this.balance) {
                String transaction = "Failed to withdraw " + formatAmount(amount, currency) +
                        " from acount: " + this.accountNumber;
                transactions.logTransaction(accountNumber, transaction);

                System.out.println("\nCan't withdraw more then what is on the account.");
                System.out.println("Current balance of: " + formatAmount(getBalance(), currency) +
                        " on account: " + this.accountNumber);
            } else {
                this.balance -= amount;
                String transaction = "Withdrew " + formatAmount(amount, currency) +
                        " from account: " + this.accountNumber;
                transactions.logTransaction(accountNumber, transaction);

                System.out.println("\nWithdrew " + formatAmount(amount, currency) +
                        " from account: " + this.accountNumber);
                System.out.println("New balance of : " + formatAmount(getBalance(), currency) +
                        " on account: " + this.accountNumber);
            }
        } else {
            double convertedAmount = convert(amount, currency, this.currency);
            if (convertedAmount > this.balance) {
                String transaction = "Failed to withdraw " + formatAmount(amount, currency) +
                        " from acount: " + this.accountNumber;
                transactions.logTransaction(accountNumber, transaction);

                System.out.println("\nCan't withdraw more then what is on the account.");
                System.out.println("Current balance of: " + formatAmount(getBalance(), currency) +
                        " on account: " + this.accountNumber);
            } else {
                this.balance -= convertedAmount;
                String transaction = "Withdrew " + formatAmount(amount, currency) +
                        " from account: " + this.accountNumber;
                transactions.logTransaction(accountNumber, transaction);

                System.out.println("\nWithdrew " + formatAmount(amount, currency) +
                        " from account: " + this.accountNumber);
                System.out.println("New balance of : " + formatAmount(getBalance(), currency) +
                        " on account: " + this.accountNumber);
            }
        }
    }

    public void takeOutLoan(double amount, String currency) {
        this.balance -= amount;
        String transaction = "Withdrew " + formatAmount(amount, currency) +
                " from account: " + this.accountNumber;
        transactions.logTransaction(accountNumber, transaction);

        System.out.println("\nWithdrew " + formatAmount(amount, currency) +
                " from account: " + this.accountNumber);
        System.out.println("New balance of : " + formatAmount(getBalance(), currency) +
                " on account: " + this.accountNumber);
    }

    public String formatAmount(double amount, String currency) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(amount) + " " + currency;
    }

    @Override
    public String toString() {
        return "Account number: " + this.accountNumber + ", " + getBalanceWithCurrency();
    }

    public void currencyConverter() {
        conversionRates = new HashMap<>();

        conversionRates.put("EUR", 1.0);
        conversionRates.put("USD", 1.12);
        conversionRates.put("GBP", 0.89);
        conversionRates.put("AFN", 93.21);
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        double conversionRateFrom = conversionRates.get(fromCurrency);
        double conversionRateTo = conversionRates.get(toCurrency);
        return amount * (conversionRateTo / conversionRateFrom);
    }

    public String printBalanceInDifferentCurrency(String targetCurrency) {
        double convertedBalance = convert(this.balance, this.currency, targetCurrency);
        return "Balance for account " + this.accountNumber + " in " + targetCurrency +
                ": " + formatAmount(convertedBalance, targetCurrency);
    }

    public String getBalanceWithCurrency() {
        return "Balance: " + formatAmount(this.balance, this.currency);
    }

    public void addInterest() {
    }

    public void accrueInterest() {
    }

}
