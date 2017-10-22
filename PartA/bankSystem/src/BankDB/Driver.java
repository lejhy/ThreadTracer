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


        //Create Thread Groups.
        //Create Runnable.
        //Create Thread with runnable target, also assign Threads to Groups.
        //Start ThreadGroups.

        String temp; //A placeholder for any string value we take from the user. Values generally aren't used more than once anyway.
        String name;
        String postcode;
        boolean validCategory = false;
        BankDB.Bank bank = new BankDB.Bank();
        ThreadGroup clerkThreads = new ThreadGroup(Thread.currentThread().getThreadGroup(), "clerks"); //main ThreadGroup set as parent.
        ThreadGroup customersThreads = new ThreadGroup(Thread.currentThread().getThreadGroup(), "customers"); //main ThreadGroup set as parent.
        Clerk clerk1 = new Clerk(); //A clerk wouldn't sign up for an account, so would need create the Clerk along with the Bank.
        Customer customer1 = new Customer("Conner", "G21");
        Customer customer2 = new Customer("Kuba", "G1");

        new Thread(clerkThreads, new UserRunnable(clerk1), "clerk1").start();
        new Thread(customersThreads, new UserRunnable(customer1), "customer1").start();
        new Thread(customersThreads, new UserRunnable(customer2), "customer2").start();


        //Threads are created and are all started.
        //At the moment does not do much.




//        bank.joiningCustomer("Conner", "G21");
//        bank.openAccount(bank.customerSearch("Conner", "G21"), 1);
//        bank.openAccount(bank.customerSearch("Conner", "G21"), 2);
//        bank.openAccount(bank.customerSearch("Conner", "G21"), 3);
//        System.out.println("Checking first account interest...");
//        System.out.println(bank.getCustomerDB().get(bank.customerSearch("Conner", "G21")).get(0).getInterest());
//
//        System.out.print("Search account: ");
//        int num = userInput.nextInt();
//        Account acc = bank.accountSearch(num);
//        if(acc != null) {
//            System.out.println("Account balance: " + acc.getBalance());
//        }
//
//        System.out.print("Make a deposit: ");
//        double val = userInput.nextDouble();
//        acc.deposit(val);
//        System.out.println("Balance: "+acc.getBalance());
//
//        System.out.print("Make a withdrawal: ");
//        val = userInput.nextDouble();
//        System.out.println("Withdrawn: "+acc.withdraw(val));
//        System.out.println("Balance: "+acc.getBalance());

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
