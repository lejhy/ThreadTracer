package BankDB;

import java.util.*;

class Bank {

    private static final String CLERK_PASS = "1234"; //Need some sort of verification for a clerk to 'log in'

    private HashMap<Customer, List<Account>> customerDB;
    private HashMap<Integer, Account> accountDB;
    private static Random rand = new Random();

    public Bank(){
        customerDB = new HashMap<Customer, List<Account>>();
        accountDB = new HashMap<Integer, Account>();
    }


    public synchronized boolean addCustomer(Customer customer){
        // TODO synchronize
        System.out.println("Adding new customer");
        if (customerDB.containsKey(customer)) {
            System.out.println("This customer already exists!");
            return false;
        } else {
            customerDB.put(customer, new ArrayList<Account>());
            System.out.println("You're signed up!");
            return true;
        }
    }


    public synchronized boolean openAccount(Customer customer, Account.Type accountType, String accountName){

        int accountNumber;
        accountNumber = generateAccountNumber();
        Account account;
        switch (accountType){
            case CHECKING :
                if(isNewAccountAvailable(customer, Account.Type.CHECKING)) {
                    account = new CurrentAccount(accountNumber, customer, accountName);
                    System.out.println("Adding a new current account with no: "+ accountNumber);
                    customerDB.get(customer).add(account);
                    accountDB.put(accountNumber, account);
                    System.out.println("That's the new Current Account opened for you! - NO OF ACCOUNTS: "+accountDB.size());
                    System.out.println("Account Number:\t" + accountNumber);
                }else{
                    System.out.println("The account could not be created.");
                    return false;
                }
                break;
            case FIXED_INTEREST :
                if(isNewAccountAvailable(customer, Account.Type.FIXED_INTEREST)) {
                    account = new FixedInterestAccount(accountNumber, customer, accountName);
                    System.out.println("Adding a new FI account with no: "+ accountNumber);
                    customerDB.get(customer).add(account);
                    accountDB.put(accountNumber, account);
                    System.out.println("That's the new Fixed Interest Account opened for you! - NO OF ACCOUNTS: "+accountDB.size());
                    System.out.println("Account Number:\t" + accountNumber);
                }else{
                    System.out.println("The account could not be created.");
                    return false;
                }
                break;
            case SAVINGS :
                if(isNewAccountAvailable(customer, Account.Type.SAVINGS)) {
                    account = new SavingsAccount(accountNumber, customer, accountName);
                    System.out.println("Adding a new Savings account with no: "+ accountNumber);
                    customerDB.get(customer).add(account);
                    accountDB.put(accountNumber, account);
                    System.out.println("That's the new Savings Account opened for you! - NO OF ACCOUNTS: "+accountDB.size());
                    System.out.println("Account Number:\t" + accountNumber);
                }else{
                    System.out.println("The account could not be created.");
                    return false;
                }
                break;
            default :
                System.out.println("Invalid selection, please try again.");
        }

        return true;
    }

    public Account accountSearch(int accountNumber){
        System.out.println("ACCOUNT DATABASE:");
        System.out.println(accountDB.keySet());
        if(accountDB.keySet().contains(accountNumber)){
            return accountDB.get(accountNumber);
        }
        System.out.println("An account has not been found with the number: "+accountNumber);
        return null;
    }


    public boolean isNewAccountAvailable(Customer customer, Account.Type accType){
        int c = 0;
        int f = 0;
        int s = 0;

        for(Account account : customerDB.get(customer)){
            if(account instanceof CurrentAccount){
                c++;
            }else if(account instanceof FixedInterestAccount){
                f++;
            }else if(account instanceof SavingsAccount){
                s++;
            }
        }

        if(accType == Account.Type.CHECKING && c >= 2){
            System.out.println("You cannot open any more than two Current Accounts.");
            return false;
        }else if(accType == Account.Type.FIXED_INTEREST && f >= 2){
            System.out.println("You cannot open any more than two Fixed Interest Accounts.");
            return false;
        }else if(accType == Account.Type.SAVINGS && s >= 2){
            System.out.println("You cannot open any more than two Savings Accounts.");
            return false;
        }
        System.out.println("You have "+c+" Current Account(s), "+f+" Fixed Interest Account(s) and "+s+" Savings Accounts currently open.");
        return true;
    }

    public Customer customerSearch(String name, String postcode){
        if(customerDB.keySet() != null) {
            for (Customer customer : customerDB.keySet()) {
                if (customer.getName().equals(name) && customer.getPostcode().equals(postcode)) {
                    return customer;
                }
            }
        }
        System.out.println("BankDB.Customer not found.");
        return null;
    }

    HashMap<Customer, List<Account>> getCustomerDB() {
        return customerDB;
    }

    HashMap<Integer, Account> getAccountDB() {
        return accountDB;
    }

    public int generateAccountNumber(){

        int number;

        number = rand.nextInt();
        if(number < 0){
            number = number * -1;
        }
        number = Integer.parseInt((""+number).toString().substring(0, 6));
        //Generate a (pseudo)random integer and clip it to 6 digits.

        //There are more sophisticated ways to do this - but it is not the task at hand...

        if(accountDB.keySet() != null){
            for(Integer account : accountDB.keySet()){
                if(number == account){ //If the account number is already taken
                    number = generateAccountNumber(); //Try to generate again.
                }
            }
        }

        return number;
    }

    public boolean clerkLogin(String pass){
        if(pass.equals(CLERK_PASS)){
            return true;
        }else{
            return false;
        }
    }

}
