package BankDB;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    private Scanner userInput = new Scanner(System.in);

    public static void main(String[] args){
        new Driver();
        Runnable user1 = new UserRunnable(new Clerk());
        Runnable user2 = new UserRunnable(new Customer("Conner", "G21"));
    }


    public Driver(){
        String temp; //A placeholder for any string value we take from the user. Values generally aren't used more than once anyway.
        String name;
        String postcode;
        boolean validCategory = false;
        BankDB.Bank bank = new BankDB.Bank();
        Clerk clerk1 = new Clerk(); //A clerk wouldn't sign up for an account, so would need create the Clerk along with the Bank.

        //As we're hoping to have a GUI, we don't need proper input checking for 'Commands' i.e log in, or create new account etc etc

        //Loops until the user inputs valid option in the console - there are internal loops that do the same within each of the choices.
        do{
            boolean validChoice = false;
            System.out.println("Clerk or Customer?");
            temp = userInput.next();

            if (temp.toLowerCase().equals("clerk")) {
                do { //Internal loop ensures the whole process does not need to be run again in case an incorrect password was entered - In the GUI we can implement a cancel function.
                    System.out.println("Clerk password: ");
                    temp = userInput.next();
                    if (bank.clerkLogin(temp)) { //clerkLogin just checked the String given against the CLERK_PASS for that bank object and returns true if they match, false if not.
                        validChoice = true;
                        new UserRunnable(new Clerk()); //Created a new 'thread' that is ready to be started. This is essentially a new login session for the Clerk.
                    } else {
                        validChoice = false;
                        System.out.println("Incorrect password.");
                    }
                }while(!validChoice);

            } else if (temp.toLowerCase().equals("customer")) {
                validCategory = true;

                do {
                    System.out.println("Login or Create account?");
                    temp = userInput.next();

                    if (temp.toLowerCase().equals("login")) {
                        validChoice = true;
                        System.out.print("Name: ");
                        name = userInput.next();
                        System.out.print("Postcode: ");
                        postcode = userInput.next();

                    } else if (temp.toLowerCase().equals("create")) {
                        validChoice = true;

                    } else {
                        validChoice = false;
                        System.out.println("Invalid option.");
                    }
                }while(!validChoice);

            } else {
                System.out.println("Invalid option.");
                validCategory = false;
            }
        }while(!validCategory);




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

    private int getUserInputInt(){
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

    private double getUserInputDouble(){
        double val = 0;
        try {
            val = Double.parseDouble(userInput.nextLine());
        }
        catch(InputMismatchException e){
            getUserInputDouble();
        }
        if (Double.toString(val).matches("^\\d+\\.\\d{2}$")){
            return val;
        }
        return getUserInputDouble();
    }

}
