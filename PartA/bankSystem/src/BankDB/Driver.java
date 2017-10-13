package BankDB;

import java.util.Scanner;

public class Driver {

    private Scanner userInput = new Scanner(System.in);

    public static void main(String[] args){
        new Driver();
    }

    public Driver(){
        BankDB.Bank bank = new BankDB.Bank();
        bank.joiningCustomer("Conner", "G21");
        bank.openAccount(bank.customerSearch("Conner", "G21"), 1);
        bank.openAccount(bank.customerSearch("Conner", "G21"), 2);
        bank.openAccount(bank.customerSearch("Conner", "G21"), 3);
        System.out.println("Checking first account interest...");
        System.out.println(bank.getCustomerDB().get(bank.customerSearch("Conner", "G21")).get(0).getInterest());

        System.out.print("Search account: ");
        int num = userInput.nextInt();
        Account acc = bank.accountSearch(num);
        if(acc != null) {
            System.out.println("Account balance: " + acc.getBalance());
        }

        System.out.print("Make a deposit: ");
        double val = userInput.nextDouble();
        acc.deposit(val);
        System.out.println("Balance: "+acc.getBalance());

        System.out.print("Make a withdrawal: ");
        val = userInput.nextDouble();
        System.out.println("Withdrawn: "+acc.withdraw(val));
        System.out.println("Balance: "+acc.getBalance());

    }

}
