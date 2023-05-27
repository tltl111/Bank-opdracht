public class Main {
    public static void main(String[] args) {
        
        Bank bank = new Bank();
        
        // Nieuwe persoonen aanmaken
        Person accountHolder1 = new Person("Dennis", "01-01-1900", "Green", "Dead");
        Person accountHolder2 = new Person("Danielle", "01-01-1999", "Green", "Single");
        Person accountHolder3 = new Person("Mike", "01-01-1999", "Green", "Single");
        Person accountHolder4 = new Person("Amber", "01-01-1999", "Green", "Single");
        Person accountHolder5 = new Person("Monica", "01-01-1999", "Green", "Married");
        Person accountHolder6 = new Person("Karen", "01-01-1999", "Green", "Karen");
        Person accountHolder7 = new Person("Johan", "01-01-1999", "Green", "Single");
        Person accountHolder8 = new Person("Henk", "01-01-1999", "Green", "Single");
        Person accountHolder9 = new Person("Piet", "01-01-1999", "Green", "Single");
        Person accountHolder10 = new Person("Rose", "01-01-1999", "Green", "Single");
        Person accountHolder11 = new Person("Lilly", "01-01-1999", "Green", "Single");
        Person accountHolder12 = new Person("Sharon", "01-01-1999", "Green", "Single");

        // Nieuwe rekeningen aanmaken
        bank.createAccount("NL", 1000.0, accountHolder1);
        bank.createAccount("NL", 500.0, accountHolder2);
        bank.createAccount("UK", 10000.0, accountHolder3);
        bank.createAccount("UK", 10000.0, accountHolder4);
        bank.createAccount("UK", 10000.0, accountHolder5);
        bank.createAccount("US", 10000.0, accountHolder6);
        bank.createAccount("US", 10000.0, accountHolder7);
        bank.createAccount("US", 10000.0, accountHolder8);
        bank.createAccount("AF", 10000.0, accountHolder9);
        bank.createAccount("AF", 10000.0, accountHolder10);
        bank.createAccount("AF", 10000.0, accountHolder11);
        bank.createAccount("AF", 10000.0, accountHolder12);

        
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
        bank.printHistory("NL01ABCD1234567890");
        bank.printHistory("NL02ABCD1234567890");
        bank.printHistory("UK01ABCD1234567890");
        bank.printHistory("US02ABCD1234567890");
        bank.printHistory("AF01ABCD1234567890");

        // Leeftijd checken van een persoon
        System.out.println("\nThe age of " + accountHolder1.getName() + " is " +
                           accountHolder1.getAge(accountHolder1.getBirthDate()));
        System.out.println("\nThe age of " + accountHolder2.getName() + " is " +
                           accountHolder2.getAge(accountHolder2.getBirthDate()));

        // Account details opvragen aan de hand van een persoon
        bank.printBalanceForPerson("Dennis");
        bank.printBalanceForPerson("Amber");
        bank.printBalanceForPerson("Sharon");

        // Naam van de persoon met het meeste geld op hun account
        bank.findRichestPerson();

        // Check of het werkt
        bank.deposit("AF04ABCD1234567890", 11250.0);
        bank.findRichestPerson();
    }
}
