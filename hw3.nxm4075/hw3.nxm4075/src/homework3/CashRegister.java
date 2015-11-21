package homework3;

/**
 *
 * @author Neil
 */

public class CashRegister {

    //This is an attribute in cashRegister.
    double ShopMoney;
    //Created a transction class to store the pennies, nickels, dimes, quaters, ones, fives, tens and twenties.
    Transaction t = new Transaction();

    //This is the constructor. Constructors are called every time an object
    //is instantiated from a class.
    public CashRegister(){
        
        //Current Shop Money
        ShopMoney = 100;
        //Current number of coins.
        t.pennies = 50;
        t.nickels = 40;
        t.dimes = 50;
        t.quaters = 40;
        t.ones = 10;
        t.fives = 2;
        t.tens = 1;
        t.twenties = 1;
    }

    //This function, when called, will print a report to the screen.
    public String display(){

        String result="";
        result=result+String.format("Current Shop Money: %d", ShopMoney);
        result=result+String.format("\n");
        return result;
    }

    //This function is a setter, it updates a private attribute.
    public void setMoney(Double currentShopMoney){

        this.ShopMoney = currentShopMoney;
    }
    
    public void addCoins(Transaction t){
        //Added to coins from the customer to the cashRegister.
        this.t.pennies += t.pennies;
        this.t.nickels += t.nickels;
        this.t.dimes += t.dimes;
        this.t.quaters += t.quaters;
        this.t.ones += t.ones;
        this.t.fives += t.fives;
        this.t.tens += t.tens;
        this.t.twenties += t.twenties;
    }
    
    public void fillCashRegister(){
        ShopMoney = 100.00;
        t.pennies = 50;
        t.nickels = 40;
        t.dimes = 50;
        t.quaters = 40;
        t.ones = 10;
        t.fives = 2;
        t.tens = 1;
        t.twenties = 1;
    }

}
