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
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
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

    protected double withdraw(double amount){
        System.out.println(Thread.currentThread().getName() + "thread with ID: " + Thread.currentThread().getId() + "is attempting to make a withdrawal...");
        try {
            while (amount > getBalance()) {
                System.out.println("Insufficient funds waiting for a deposit");
                awaitFunds.await();
            }
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)){
                balance -= amount;
            }else{
                System.out.println("Waited for too long, moving on...");
                return 0.0;
            }
        }
        catch(InterruptedException e){
            System.out.println("Withdraw was interrupted by another thread");
        }
        finally {
            lock.unlock();
            return amount * interest;
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
