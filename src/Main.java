public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Nieuwe rekeningen aanmaken
        bank.createAccount("NL", 1000.0);
        bank.createAccount("NL", 500.0);
        bank.createAccount("UK", 10000.0);
        bank.createAccount("UK", 10000.0);
        bank.createAccount("UK", 10000.0);
        bank.createAccount("US", 10000.0);
        bank.createAccount("US", 10000.0);
        bank.createAccount("US", 10000.0);
        bank.createAccount("AF", 10000.0);
        bank.createAccount("AF", 10000.0);
        bank.createAccount("AF", 10000.0);
        bank.createAccount("AF", 10000.0);

        // Geld storten
        bank.deposit("NL01ABCD1234567890", 250.0);
        bank.deposit("US01ABCD1234567890", 910.0);
        bank.deposit("AF01ABCD1234567890", 910.0);
        
        // Geld opnemen
        bank.withdraw("NL02ABCD1234567890", 200.0);
        // Nogmaals geld opnemen, maar meer dan er op de rekening staat.
        bank.withdraw("NL02ABCD1234567890", 2200.0);
    
        // Geld overmaken
        bank.transfer("NL01ABCD1234567890", 10, "US02ABCD1234567890");
        bank.transfer("NL01ABCD1234567890", 10000000, "NL02ABCD1234567890");
        System.out.println(" ");

        // Controle of het juist gewerkt heeft.
        System.out.println(bank.getAccount("NL02ABCD1234567890").getBalance());
        // Saldo controleren
        System.out.println(bank.getAccount("NL01ABCD1234567890").getBalance());
        System.out.println(bank.getAccount("NL02ABCD1234567890").getBalance());
        System.out.println(" ");
        // Accounts toString controleren
        System.out.println(bank.getAccount("NL01ABCD1234567890").toString());
        System.out.println(bank.getAccount("NL02ABCD1234567890").toString());
        System.out.println(bank.getAccount("UK01ABCD1234567890").toString());
        System.out.println(bank.getAccount("US02ABCD1234567890").toString());
        System.out.println(bank.getAccount("AF01ABCD1234567890").toString());
        // Currency converter testen
        System.out.println(bank.getAccount("NL01ABCD1234567890").printBalanceInCurrency("USD"));
        System.out.println(bank.getAccount("NL02ABCD1234567890").printBalanceInCurrency("USD"));
        System.out.println(bank.getAccount("UK01ABCD1234567890").printBalanceInCurrency("GBP"));
        System.out.println(bank.getAccount("US02ABCD1234567890").printBalanceInCurrency("AFN"));
        System.out.println(bank.getAccount("AF01ABCD1234567890").printBalanceInCurrency("EUR"));

        // Transactie geschiedenis weergeven
        Account NL01 = bank.getAccount("NL01ABCD1234567890");
        NL01.getTransactionHistory().displayTransactionHistoy();
        Account NL02 = bank.getAccount("NL02ABCD1234567890");
        NL02.getTransactionHistory().displayTransactionHistoy();
        Account US01 = bank.getAccount("US01ABCD1234567890");
        US01.getTransactionHistory().displayTransactionHistoy();
    }
}
