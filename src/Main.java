public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Nieuwe personen aanmaken
        Person accountHolder1 = new Person("Dennis", "01-01-1900", "Green", "Dead");
        Person accountHolder2 = new Person("Danielle", "01-01-1999", "Green", "Single");
        Person accountHolder3 = new Person("Mike", "01-01-1999", "Green", "Single");
        Person accountHolder4 = new Person("Amber", "01-01-1999", "Green", "Single");
        Person accountHolder5 = new Person("Monica", "01-01-1999", "Green", "Married");
        Person accountHolder6 = new Person("Rose", "01-01-1999", "Green", "Karen");
        Person accountHolder7 = new Person("Lilly", "01-01-1999", "Green", "Single");
        Person accountHolder8 = new Person("Sharon", "01-01-1999", "Green", "Single");
        
        // Nieuwe rekeningen aanmaken
        bank.createAccount("NL", 10000.0, accountHolder1);
        bank.createAccount("NL", 10000.0, accountHolder2);
        bank.createAccount("UK", 10000.0, accountHolder3);
        bank.createAccount("UK", 10000.0, accountHolder4);
        bank.createAccount("US", 10000.0, accountHolder5);
        bank.createAccount("US", 10000.0, accountHolder6);
        bank.createAccount("AF", 10000.0, accountHolder7);
        bank.createAccount("AF", 10000.0, accountHolder8);
        
        // Geld storten
        bank.deposit("NL0000000001", 250.0);
        
        // Geld opnemen
        bank.withdraw("NL0000000002", 200.0);
        bank.withdraw("NL0000000002", 120000.0);

        // Overboeking tussen rekening
        bank.transfer("NL0000000001", 100000, "NL0000000002");
        bank.transfer("NL0000000001", 1000, "NL0000000002");
        bank.transfer("NL0000000001", 1000, "US0000000001");
        bank.transfer("NL0000000001", 1000, "AF0000000001");
        bank.transfer("AF0000000001", 10000, "US0000000001");
    
        // // Currency converter testen
        // System.out.println();
        System.out.println(bank.getAccount("NL0000000001").printBalanceInDifferentCurrency("GBP"));
        System.out.println(bank.getAccount("NL0000000001").printBalanceInDifferentCurrency("USD"));
        System.out.println(bank.getAccount("NL0000000001").printBalanceInDifferentCurrency("AFN"));

        // // Transactie geschiedenis weergeven
        bank.printHistory("NL0000000001");
        bank.printHistory("NL0000000002");
        bank.printHistory("AF0000000001");
        bank.printHistory("US0000000001");

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
        bank.deposit("NL0000000001", 112500.0);
        bank.findRichestPerson();
    }
}
