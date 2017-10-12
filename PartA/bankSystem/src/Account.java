public class Account {

    private double balance;
    private double interest;

    public Account(){
        balance = 0.0;
        interest = 1.01; //Adding interest will be done by amount*interest, so this adds 1% interest.
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
