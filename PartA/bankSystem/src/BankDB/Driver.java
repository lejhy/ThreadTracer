package BankDB;

import java.util.InputMismatchException;
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

    public int getUserInputInt(){
        int val = 0;
        try {
            val = Integer.parseInt(userInput.nextLine());
        }
        catch(InputMismatchException e){
            getUserInputInt();
        }
        if (Integer.toString(val).matches("\\d{6}$")){
            return val;
        }
        return getUserInputInt();
    }

    public double getUserInputDouble(){
        double val = 0;
        try {
            val = Double.parseDouble(userInput.nextLine());
        }
        catch(InputMismatchException e){
            getUserInputInt();
        }
        if (Double.toString(val).matches("^\\d+\\.\\d{2}$")){
            return val;
        }
        return getUserInputDouble();
    }

}
