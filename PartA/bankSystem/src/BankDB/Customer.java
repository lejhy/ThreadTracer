package BankDB;

class Customer {

    String name;
    String postcode;
    Account lockedAccount;

    public Customer(String n, String p){
        name = n;
        postcode = p;
    }

    public String getName() {
        return name;
    }

    public String getPostcode() {
        return postcode;
    }

    public Account getLockedAccount() {
        return lockedAccount;
    }

    public void setLockedAccount(Account account){
        lockedAccount = account;
    }
}
