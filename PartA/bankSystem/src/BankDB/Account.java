package BankDB;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {

    public static final double DEF_BALANCE = 0.0;
    public static final double DEF_INTEREST = 1.01; //Adding interest will be done by amount*interest, so this adds 1% interest.

    protected int accountNumber;
    protected String accountName;
    protected double balance;
    protected double interest;
    protected Customer owner;
    protected boolean locked = false;

    private Lock lock = new ReentrantLock();
    private Condition awaitFunds = lock.newCondition();


    protected Account(int acNum, Customer ow, String n){
        accountNumber = acNum;
        owner = ow;
        accountName = n;
        balance = DEF_BALANCE;
        interest = DEF_INTEREST;
    }

    protected boolean deposit(double amount){
        System.out.println(Thread.currentThread().getName() + "thread with ID: " + Thread.currentThread().getId() + "is attempting to make a deposit...");
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                this.balance += amount;
            }
        }
        catch(InterruptedException e){
            System.out.println("Deposit was interrupted by another thread");
            return false;
            }
        finally {
            lock.unlock();
            return true;
        }
    }

    protected synchronized double withdraw(double amount){
        System.out.println(Thread.currentThread().getName() + "thread with ID: " + Thread.currentThread().getId() + "is attempting to make a withdrawal...");


        if(!locked) {
            locked = true;
            if (balance - amount > 0) {
                balance = balance - amount;
                locked = false;
                return amount * interest;
            } else { //Not enough funds. Overdraught not available.
                System.out.println("There are not enough funds to withdraw:\t" + amount);
                System.out.println("\t\t\tBalance:\t" + getBalance());
                locked = false;
                return 0.0;
            }
        }else{
            System.out.println("This account is locked and already being edited.");
            return 0.0;
        }
    }

    public Customer getOwner(){
        return owner;
    }

    public double getInterest(){
        return interest;
    }

    public double getBalance(){
        return balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public boolean lockAccount(Customer customer){
        locked = true;
        customer.setLockedAccount(this);
        return true;
    }

    public boolean unlockAccount(Customer customer){
        locked = false;
        customer.setLockedAccount(null);
        return true;
    }


}
