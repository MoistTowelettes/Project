package homework3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 *
 * @author Neil
 */


public class Shop {

    //Creating a static variable so that we can keep track of the order number with the order ArrayList.
    public static int orderN = 0;
    /**
     * Creating an ArrayList for iceCream, Serving, Customer, Workers and Orders to have multiple
     * objects of each.
     */
    ArrayList <IceCreamMain> iceCreamList = new ArrayList<>();
    ArrayList <Serving> servingList = new ArrayList<>();
    ArrayList <Customer> customerList = new ArrayList<>();
    ArrayList <Worker> workerList = new ArrayList<>();
    ArrayList <Order> orderList = new ArrayList<>();

    IceCreamMain ic = new IceCreamMain();
    Worker wor = new Worker();
    Customer cus = new Customer();
    
    IceCreamCone icc;
    IceCreamSundae ics;
    BananaSplit bs;
    RootBeerFloat rbf;
    IceCreamSoda cs;
    
    Serving serv;
    
    Scanner input = new Scanner(System.in);
    
    //We need only one cash register and hence no ArrayList for this.
    CashRegister cr = new CashRegister();
    
    //Fills the data in the ArrayList of the Customer Class.
    public void createCustomerArrayList(File file){
        customerList = cus.getCustomerList(file);
    } 
    
    //Fills the data in the ArrayList of the Worker Class.
    public void createWorkerArrayList(File file){
        workerList = wor.getWorkerList(file);
    }
    
    //Fills the data in the ArrayList of the Ice Cream Class.
    public void createIceCreamArrayList(File file){
        iceCreamList = ic.getIceCreamList(file);
    }
    
    //Creates an ArrayList for the order.
    public void createOrderArrayList(int workerOption, int customerOption){
        
        //Initialization of a new object.
        Order or = new Order();
        
        or.servings = (ArrayList<Serving>) servingList.clone();
        
        servingList.clear();

        //Incrementing the orderNumber to get next in line in the OrderArrayList.
        or.orderNumber = ++orderN;
        
        //Referencing to the Customer in the CustomerArrayList.
        or.c = customerList.get(customerOption);
        //Referencing to the Worker in the WorkerArrayList.
        or.w = workerList.get(workerOption);
        
        //Adding object to the ArrayList.
        orderList.add(or);
        
    }
    
    //Calculating the total money for that particular order.
    public double getTotalMoney(int orderSelect){
    
        //orderList.get(orderSelect);
        double totalPrice = 0;
        
        for(int i = 0; i < orderList.get(orderSelect).servings.size(); i++){
        
            totalPrice += orderList.get(orderSelect).servings.get(i).getOverAllPrice();
        }
        
        return totalPrice;
    }
    
    //updating the customer and worker class after they have paid for their order.
    public void changeCandW(int orderSelect, double price) throws IOException{
        
        orderList.get(orderSelect).w.moneyTaken += price;
        orderList.get(orderSelect).w.scoopsServed += orderList.get(orderSelect).servings.size();
        orderList.get(orderSelect).w.customerServed +=1;
        //orderList.get(orderSelect).w.updateFile();
        cr.ShopMoney += price;
        orderList.get(orderSelect).status = true;
        orderList.get(orderSelect).c.levelOfHappiness +=1;
        orderList.get(orderSelect).c.wallet -=price;
        //orderList.get(orderSelect).c.updateFile();
        
        /*
        
        */
        
        for(int i = 0; i < workerList.size(); i++){
        
            if((workerList.get(i).typeOfWorker.equals("Cashier"))){
                    
                if(workerList.get(i).getOnBreak()){
            
                    workerList.get(i).setPatience(1);
                }
                else{
                
                    workerList.get(i).setPatience(-1);
                    workerList.get(i).scoopsServed +=1;
                }
            }
        }
        
        for(int i = 0; i < orderList.get(orderSelect).servings.size();i++){
        
            for(int j = 0; j < orderList.get(orderSelect).servings.get(i).iceCreamNumber.size(); j++){
            
                iceCreamList.get((int) orderList.get(orderSelect).servings.get(i).iceCreamNumber.get(j)).gallonsIC--;
            }
        }
    }
        
    
    //Making a transaction b/w the Customer and the Cash Register.
    public void makeTransactions(Transaction t, double totalAmount, int orderSelect){
        
        double amountUserPaid = t.calAmountPaid();
        cr.addCoins(t);
        double amountReturned = amountUserPaid - totalAmount;
        if(amountReturned < cr.ShopMoney){

            orderList.get(orderSelect).c.t.pennies -= t.pennies;
            orderList.get(orderSelect).c.t.nickels -= t.nickels;
            orderList.get(orderSelect).c.t.dimes -= t.dimes;
            orderList.get(orderSelect).c.t.quarters -= t.quarters;
            orderList.get(orderSelect).c.t.ones -= t.ones;
            orderList.get(orderSelect).c.t.fives -= t.fives;
            orderList.get(orderSelect).c.t.tens -= t.tens;
            orderList.get(orderSelect).c.t.twenties -= t.twenties;
            while(amountReturned >= 20){

                --cr.t.twenties; 
                ++orderList.get(orderSelect).c.t.twenties;
                amountReturned -= 20;
            }
            while(amountReturned >= 10){

                --cr.t.tens;
                ++orderList.get(orderSelect).c.t.tens;
                amountReturned -= 10;
            }
            while(amountReturned >= 5){

                --cr.t.fives;
                ++orderList.get(orderSelect).c.t.fives;
                amountReturned -= 5;
            }
            while(amountReturned >= 1){

                --cr.t.ones;
                ++orderList.get(orderSelect).c.t.ones;
                amountReturned -= 1;
            }
            while(amountReturned >= 0.25){

                --cr.t.quarters;
                ++orderList.get(orderSelect).c.t.quarters;
                amountReturned -= 0.25;
            }
            while(amountReturned >= 0.1){

                --cr.t.dimes;
                ++orderList.get(orderSelect).c.t.dimes;
                amountReturned -= 0.1;
            }
            while(amountReturned >= 0.05){

                --cr.t.nickels;
                --orderList.get(orderSelect).c.t.nickels;
                amountReturned -= 0.05;
            }
            while(amountReturned >= 0.01){


                --cr.t.pennies;
                ++orderList.get(orderSelect).c.t.pennies;
                amountReturned -= 0.01;
            }
        }else{
        
            try {
                cr.fillCashRegister();
                if(checkCashierState()){
                
                    for(int i = 0; i < workerList.size(); i++){
        
                        if((workerList.get(i).typeOfWorker.equals("Cashier"))){
                    
                            if(!workerList.get(i).getOnBreak()){
                                
                                workerList.get(i).setPatience(-2);
                            }
                        }
                    }
                }
                throw new MoneyException();
            } catch (MoneyException ex) {
                System.out.println("filling the cash register with some money");
            }
        }
        
    }
    
    //Displaying all the coins that the customer has. 
    public void customerCoins(int orderSelect){
    
        System.out.printf("\nPennies: %d" ,orderList.get(orderSelect).c.t.pennies);
        System.out.printf("\nNickels: %d" ,orderList.get(orderSelect).c.t.nickels);
        System.out.printf("\nDimes: %d" ,orderList.get(orderSelect).c.t.dimes);
        System.out.printf("\nQuaters: %d" ,orderList.get(orderSelect).c.t.quarters);
        System.out.printf("\nOnes: %d" ,orderList.get(orderSelect).c.t.ones);
        System.out.printf("\nFives: %d" ,orderList.get(orderSelect).c.t.fives);
        System.out.printf("\nTens: %d" ,orderList.get(orderSelect).c.t.tens);
        System.out.printf("\nTwenties: %d" ,orderList.get(orderSelect).c.t.twenties);
    }
    
    //Displaying all the coins that are their in the cash register.
    public void cashRegisterCoins(){
    
        System.out.printf("\nPennies: %d" ,cr.t.pennies);
        System.out.printf("\nNickels: %d" ,cr.t.nickels);
        System.out.printf("\nDimes: %d" ,cr.t.dimes);
        System.out.printf("\nQuaters: %d" ,cr.t.quarters);
        System.out.printf("\nOnes: %d" ,cr.t.ones);
        System.out.printf("\nFives: %d" ,cr.t.fives);
        System.out.printf("\nTens: %d" ,cr.t.tens);
        System.out.printf("\nTwenties: %d" ,cr.t.twenties);
    }
    
    public void makeStockerActive(int option){
    
        workerList.get(option).setActive();
        workerList.get(option).setOnBreak();
    }
    
    public void refillIceCream(int option){
    
        int n = 0;
        for(int i = 0; i < workerList.size(); i++){
        
            if((workerList.get(i).typeOfWorker.equals("Stocker")) & !(workerList.get(i).getOnBreak())){
                
                iceCreamList.get(option).gallonsIC = 80;
                workerList.get(i).setStamina(-1);
                if(workerList.get(i).getStamina() == 0){
                
                    workerList.get(i).setActive();
                    workerList.get(i).setOnBreak();
            }
                n++;
            }
            else if((workerList.get(i).typeOfWorker.equals("Stocker")) & (workerList.get(i).getOnBreak())){
            
                workerList.get(i).setStamina(1);
            }
        
            if(n==0){
            
                System.out.print("Please Select a Stocker and then refill");
            }
        }
    }
    
    public void makeCashierActive(int option){
    
        workerList.get(option).setActive();
        workerList.get(option).setOnBreak();
    }
    
    public boolean getFlavors(Serving serv){
        
        boolean loop = true;
        System.out.println("Please choose your " + serv.getNumberOfScoops() + " flavors");
    
        for(int j = 0; j < serv.getNumberOfScoops(); j++){
        
            System.out.println("Which flavor do you want for your first serving? ");
            for(int i = 0; i < iceCreamList.size(); i++){
                
            
                System.out.printf("%d. %s\n", i+1,iceCreamList.get(i).name);
            }
            int iceCreamOption = input.nextInt();
            iceCreamOption--;
            if(iceCreamList.get(iceCreamOption).gallonsIC > 0 ){
                serv.flavors[j] = iceCreamList.get(iceCreamOption);
                serv.iceCreamNumber.add(iceCreamOption);
            }
            else{
            
                try {
                    throw new IceCreamException();
                } catch (IceCreamException ex) {
                    System.out.println("Not enough Ice Cream");
                }
                finally{
                
                    return false;
                }
            }
        
        }
        return true;
    }
    
    public boolean checkCashierState(){
        
        int n = 0;
        for(int i = 0; i < workerList.size(); i++){
        
            if((workerList.get(i).typeOfWorker.equals("Cashier")) & (workerList.get(i).getActive())){
            
                n++;
            }
        }
        
        if(n==0) return false;
        else return true;
    }

    //saves all files
    public void saveAllFiles() {
        ic.saveIceCream(iceCreamList);
        wor.saveWorker(workerList);
        cus.saveCustomer(customerList);
    }
}
