import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {

    private Scanner userInput = new Scanner(System.in);

    public static void main(String[] args){
        new Driver();
    }


    public Driver(){


    }

    private int getUserInputInt(){
        int val = 0;
        try {
            val = Integer.parseInt(userInput.nextLine());
        }
        catch(InputMismatchException e){
            getUserInputInt();
        }
        if (Integer.toString(val).matches("\\d{6}$")){
            return val;
        }
        return getUserInputInt();
    }

    private double getUserInputDouble(){
        double val = 0;
        try {
            val = Double.parseDouble(userInput.nextLine());
        }
        catch(InputMismatchException e){
            getUserInputDouble();
        }
        if (Double.toString(val).matches("^\\d+\\.\\d{2}$")){
            return val;
        }
        return getUserInputDouble();
    }

}
