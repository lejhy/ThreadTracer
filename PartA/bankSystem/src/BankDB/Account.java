package BankDB;

class Account {

    public static final double DEF_BALANCE = 0.0;
    public static final double DEF_INTEREST = 1.01; //Adding interest will be done by amount*interest, so this adds 1% interest.

    protected int accountNumber;
    protected double balance;
    protected double interest;
    protected Customer owner;
    protected boolean locked = false;


    protected Account(int acNum, Customer ow){
        accountNumber = acNum;
        owner = ow;
        balance = DEF_BALANCE;
        interest = DEF_INTEREST;

    }

    protected boolean deposit(double amount){

        if(!locked) {
            locked = true;
            this.balance = balance + amount;
            locked = false;
            return true;
        }else {
            System.out.println("This account is locked and already being edited.");
            return false;
        }
    }

    protected double withdraw(double amount){
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
                return -1;
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
