package BankDB;

class CurrentAccount extends Account{

    public CurrentAccount(int acNum, Customer owner, String accountName){
        super(acNum, owner, accountName); //sets accountNumber to acNum and balance and interest to def values of 0 and 1.01 respectively.
        System.out.println("CurrentAccount");
        System.out.println("Interest for Account is set to 1.01, no override. : "+interest);
        System.out.println("Account Number: "+accountNumber);
    }

}
