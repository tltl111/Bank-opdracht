public class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(String accountNumber, String currency, double balance, double interestRate, Person accountOwner) {
        super(accountNumber, currency, balance, accountOwner);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double rate) {
        this.interestRate = interestRate;
    }

    @Override
    public void addInterest() {
        double interestAmount = getBalance() * (interestRate / 100);
        deposit(interestAmount, getCurrency());

        String transaction = "Applied interest of: " + formatAmount(interestAmount, getCurrency()) + 
                             " on account: " + getAccountNumber();
        transactions.logTransaction(getAccountNumber(), transaction);

        System.out.println("\nApplied interest of " + formatAmount(interestAmount, getCurrency()) +
                " to account: " + getAccountNumber());
        System.out.println("New balance: " + formatAmount(getBalance(), getCurrency()) +
                " on account: " + getAccountNumber());
    }
    
}
