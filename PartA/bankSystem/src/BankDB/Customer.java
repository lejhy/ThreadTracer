package BankDB;

public class Customer extends User{

    String ID;
    String name;
    String postcode;

    public Customer(String ID, String name, String postcode){
        this.ID = ID;
        this.name = name;
        this.postcode = postcode;
    }

    public String getID() { return ID; }

    public String getName() {
        return name;
    }

    public String getPostcode() { return postcode; }
}
