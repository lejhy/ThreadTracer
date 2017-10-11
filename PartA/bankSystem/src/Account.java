public class Account {

    private double balance;
    private double interest;

    public Account(){
        balance = 0.0;
        interest = 1.0;
    }

    public boolean deposit(double amount){
        balance = balance + amount;
        return true;
    }

    public boolean withdraw(double amount){
        balance = balance - amount;
        return true;
    }

}
