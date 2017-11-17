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
    protected Customer owner;
    protected double balance;
    protected double interest;
    protected boolean locked = false;

    private Lock lock = new ReentrantLock();
    private Condition awaitFunds = lock.newCondition();

    public enum Type {
        CHECKING, SAVINGS, FIXED_INTEREST
    }

    protected Account(int acNum, Customer ow, String n){
        accountNumber = acNum;
        accountName = n;
        owner = ow;
        balance = DEF_BALANCE;
        interest = DEF_INTEREST;
    }

    protected void deposit(double amount){
        System.out.println(Thread.currentThread().getName() + "thread with ID: " + Thread.currentThread().getId() + "is attempting to make a deposit...");
        lock.lock();
        this.balance += amount;
        lock.unlock();
    }

    protected void withdraw(double amount){
        System.out.println(Thread.currentThread().getName() + "thread with ID: " + Thread.currentThread().getId() + "is attempting to make a withdrawal...");
        boolean isWaiting = true;
        lock.lock();
        try {
            while (amount > getBalance()) {
                System.out.println("Insufficient funds waiting for a deposit");
                isWaiting = awaitFunds.await(10, TimeUnit.SECONDS);
                if (isWaiting == false) {
                    System.out.println("Waited for too long, moving on...");
                    Thread.currentThread().interrupt();
                }
            }
        }
        catch(InterruptedException e){
            System.out.println("Withdraw was interrupted...");
        }
        finally {
            lock.unlock();
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

    public void lockAccount(Customer customer){
        // TODO synchronize
        locked = true;
        customer.setLockedAccount(this);
    }

    public void unlockAccount(Customer customer){
        // TODO synchronize
        locked = false;
        customer.setLockedAccount(null);
    }

}
