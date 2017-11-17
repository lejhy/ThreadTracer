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


    public boolean addCustomer(Customer customer){
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


    public boolean openAccount(Customer customer, Account.Type accountType, String accountName){
        // TODO synchronize
        int accountNumber = generateAccountNumber();
        Account account;
        switch (accountType) {
            case CHECKING:
                account = new CheckingAccount(accountNumber, customer, accountName);
                break;
            case FIXED_INTEREST:
                account = new FixedInterestAccount(accountNumber, customer, accountName);
                break;
            case SAVINGS:
                account = new SavingsAccount(accountNumber, customer, accountName);
                break;
            default:
                System.out.println("Could not create an account, unexpected Account.Type: " + accountType);
                return false;
        }

        if (!customerDB.containsKey(customer)) {
            addCustomer(customer);
        }

        customerDB.get(customer).add(account);
        accountDB.put(accountNumber, account);
        System.out.println("That's the new Current Account opened for you! - NO OF ACCOUNTS: "+accountDB.size());
        System.out.println("Account Number:\t" + accountNumber);

        return true;
    }

    public Account getAccount(int accountNumber){
        System.out.println("ACCOUNT DATABASE:");
        System.out.println(accountDB.keySet());
        if(accountDB.keySet().contains(accountNumber)){
            return accountDB.get(accountNumber);
        }
        System.out.println("An account has not been found with the number: "+accountNumber);
        return null;
    }

    public Customer getCustomer(String ID){
        for (Customer customer : customerDB.keySet()) {
            if (customer.getID().equals(ID)) {
                return customer;
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

        int number = rand.nextInt(1000000);

        for(Integer account : accountDB.keySet()){
            if(number == account){ //If the account number is already taken
                number = generateAccountNumber(); //Try to generate again.
            }
        }

        return number;
    }

}
