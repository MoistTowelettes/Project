package homework3;

/**
 *
 * @author Neil
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuManager {

    //the shop controller
    private Shop shopper;

    //Common string usage, so use an attribute
    private int answer;
    
    //A pointer attribute for controlling the input stream from the keyboard.
    private Scanner keyboard;

   
    ArrayList <Customer> customer;
    ArrayList <Worker> worker;
    ArrayList <IceCreamMain> IceCreamList;
        
    public MenuManager(){

       
       
        keyboard = new Scanner(System.in);
    }

    //Command to set the pointer to a SubController
    //If the Menumanager has the pointer, it can call the command methods in the SubController
    public void setShopcontroller(Shop shopcontroller){
        this.shopper = shopcontroller;
    }

 //Main menu
    public void go(){
        boolean loop = true;
        //Referencing the ArrayList in the menuManger to the ones in shop (SubController)
        shopper.createCustomerArrayList();
        customer = shopper.customerList;
        shopper.createWorkerArrayList();
        worker = shopper.workerList;
        shopper.createIceCreamArrayList();
        IceCreamList = shopper.iceCreamList;
        
        while(loop){
            
            System.out.printf("\nIce Cream Main Menu");
            System.out.printf("\n*********************");
            System.out.printf("\n 1: Placing an order");
            System.out.printf("\n 2: Paying for the order");
            System.out.printf("\n 3: Stocker Information");
            System.out.printf("\n 4: Cashier Information");
            System.out.printf("\n 5: Status Report");
            System.out.printf("\n 6: Exception Handling");
            System.out.printf("\n 7: Quit");
            System.out.printf("\n**********************");
            System.out.printf("\nEnter your choice:\n");
            answer = keyboard.nextInt(); 

            switch(answer){
                case 1:
                    placingOrder();
                    break;
                case 2:
                    payingOrder();
                    break;
                case 3: 
                    stockerMenu();
                    break;
                case 4:
                    cashierMenu();
                    break;
                case 5:
                    statusReport();
                    break;
                case 6:
                    exceptionHandling();
                    break;
                case 7:
                    loop = false;
                    break;
            }
        }
    }

    //Method for the customer to place an order place an order.
    public void placingOrder(){
        boolean loop = true;
        
        
        
        
        
        
        System.out.println("Which worker is in duty?");
        for(int i = 0; i < worker.size() ; i++){
            
            System.out.printf("%d. %s\n", i+1,worker.get(i).workerName);
        }
        
        
        
        int workerOption = keyboard.nextInt();
        workerOption--;
        
        
         System.out.println("Which customer is being served?");
        for(int i = 0; i < customer.size() ; i++){
            
            System.out.printf("%d. %s\n", i+1,customer.get(i).name);
        }
        int customerOption = keyboard.nextInt();
        
        customerOption--;
        do{
            System.out.println("What kind of Ice Cream do you want?");
            System.out.println("1. Cones");
            System.out.println("2. Sundaes");
            System.out.println("3. Banana Split");
            System.out.println("4. Soda Float");
            System.out.println("5. Root Beer Float");
            System.out.println("6. Exit");

            int servingOption = keyboard.nextInt();

            switch(servingOption){

                case 1: 
                    shopper.serv = new IceCreamCone();
                    System.out.println("Please enter the type of cone: ");
                    System.out.println("1. Cake Cone");
                    System.out.println("2. Sugar Cone");
                    System.out.println("3. Waffle Cone");
                    int option = keyboard.nextInt();
                    shopper.serv.setTypeOfExtras(option);
                    System.out.println("Enter the number of scoops (Max 3): ");
                    shopper.serv.setNumberOfScoops(keyboard.nextInt());
                    if(shopper.getFlavors(shopper.serv))
                        shopper.servingList.add(shopper.serv);
                    else{
                        shopper.customerList.get(customerOption).levelOfHappiness -=2;
                    }
                    break;
                case 2:
                    shopper.serv = new IceCreamSundae();
                    System.out.println("Enter the number of scoops (Max 2): ");
                    shopper.serv.setNumberOfScoops(keyboard.nextInt());
                    if(shopper.getFlavors(shopper.serv)){
                        System.out.println("Please enter the type of Syrup: ");
                        System.out.println("1. Strawberry");
                        System.out.println("2. Chocolate");
                        System.out.println("3. Marshmallow ");
                        System.out.println("4. Pineapple");
                        System.out.println("5. Ketchup");
                        System.out.println("6. Mustard");
                        System.out.println("7. Pickle Relish");
                        int sundaeOption = keyboard.nextInt();
                        System.out.println("Do you want to add Nuts? \n 1. Yes \n 2.No: ");
                        int optionNuts = keyboard.nextInt();
                        boolean nuts = false;
                        if(optionNuts == 1) nuts = true;
                        if(optionNuts == 2) nuts = false;
                        shopper.serv.setNuts(nuts);
                        shopper.serv.setTypeOfExtras(sundaeOption);
                        shopper.servingList.add(shopper.serv);
                    }
                    else{
                        shopper.customerList.get(customerOption).levelOfHappiness -=2;
                    }
                    break;
                case 3:
                    shopper.serv = new BananaSplit();
                    if(shopper.getFlavors(shopper.serv)){
                        System.out.println("Please choose your 3 servings");
                        int[] n = new int[3];
                        for(int i = 0; i < 3; i++){

                            System.out.println("Please enter the type of Syrup: ");
                            System.out.println("1. Strawberry");
                            System.out.println("2. Chocolate");
                            System.out.println("3. Marshmallow cream");
                            System.out.println("4. Pineapple");
                            System.out.println("5. Ketchup");
                            System.out.println("6. Mustard");
                            System.out.println("7. Pickle Relish");

                            n[i] = keyboard.nextInt();
                        }
                       System.out.println("Do you want to add Nuts? \n 1. Yes \n 2.No: ");
                        int optionForNuts = keyboard.nextInt();
                        boolean nuts2 = false;
                        if(optionForNuts == 1) nuts2 = true;
                        if(optionForNuts == 2) nuts2 = false;
                        shopper.serv.setNuts(nuts2);
                        shopper.serv.setTypeOfExtras(n);
                        shopper.servingList.add(shopper.serv);
                    }
                    else{
                        shopper.customerList.get(customerOption).levelOfHappiness -=2;
                    }
                    break;
                case 4:
                    shopper.serv = new RootBeerFloat();
                    if(shopper.iceCreamList.get(0).GallonsIC > 0)
                        shopper.servingList.add(shopper.serv);
                    else{
                        shopper.customerList.get(customerOption).levelOfHappiness -=2;
                    }
                    break;
                case 5:
                    shopper.serv = new IceCreamSoda();
                    if(shopper.getFlavors(shopper.serv))
                        shopper.servingList.add(shopper.serv);
                    else{
                        shopper.customerList.get(customerOption).levelOfHappiness -=2;
                    }
                    break;
                case 6:
                    loop = false;
                    break;
            }
        }while(loop);
        
        shopper.createOrderArrayList(workerOption, customerOption);   
    }
    
    
    //Method for making the payment.
    public void payingOrder(){
        
        if(shopper.checkCashierState()){

            System.out.println("Select the order you want to pay for ");
            //creating a reference to orders which will point to the order ArrayList in the SubController.
            ArrayList<Order> orders;

            //Referencing.
            orders = shopper.orderList;
            int orderSelect;

            for(int i = 0; i < orders.size(); i++){

                //the if condition is to check if the person is paid or not.
                if(orders.get(i).status == false){
                    System.out.printf("Order Number# %s\n", i+1,orders.get(i).orderNumber);
                }
            }
            System.out.println("Enter the Order Number: ");
            orderSelect = keyboard.nextInt();
            //decrementing because the options start from 1 and not 0.
            orderSelect--;
            int paymentMode;
            boolean keepTrying = false;
            double totalAmount = shopper.getTotalMoney(orderSelect);
            do{
                System.out.println("Do you want to pay by \n1) Cash\n2) Credit");
                paymentMode = keyboard.nextInt();
                //Calculating the total amount of money the user has to pay for that particular order.

                if((totalAmount < shopper.orderList.get(orderSelect).c.wallet) | (paymentMode == 2)){
                    keepTrying = false;
                    switch(paymentMode){

                        case 1:
                            Transaction tran = new Transaction();
                            System.out.println("Your total amount is: " + totalAmount);
                            //Basically, getting the list of all the coins from the user.
                            System.out.println("Enter the number of coins for each: ");
                            System.out.printf("\nPennies : ", shopper.orderList.get(orderSelect).c.t.pennies);
                            tran.pennies = keyboard.nextInt();
                            System.out.printf("\nNickels : ", shopper.orderList.get(orderSelect).c.t.nickels);
                            tran.nickels = keyboard.nextInt();
                            System.out.printf("\nDimes : ", shopper.orderList.get(orderSelect).c.t.dimes);
                            tran.dimes = keyboard.nextInt();
                            System.out.printf("\nQuaters : ", shopper.orderList.get(orderSelect).c.t.quaters);
                            tran.quaters = keyboard.nextInt();
                            System.out.printf("\nOnes ", shopper.orderList.get(orderSelect).c.t.ones);
                            tran.ones = keyboard.nextInt();
                            System.out.printf("\nFives : ", shopper.orderList.get(orderSelect).c.t.fives);
                            tran.fives = keyboard.nextInt();
                            System.out.printf("\nTens : ", shopper.orderList.get(orderSelect).c.t.tens);
                            tran.tens = keyboard.nextInt();
                            System.out.printf("\nTwenties : ", shopper.orderList.get(orderSelect).c.t.twenties);
                            tran.twenties = keyboard.nextInt();
                            //Making a transaction. Further explained in method in the SubController(shop)
                            shopper.makeTransactions(tran,totalAmount, orderSelect);
                            break;
                        case 2:
                            //calculated the total amount.
                            System.out.println("Your total amount is: " + totalAmount);
                            break;
                    }
                    try {
                        /**
                         * This is for making the changes back to the files and the objects in the 
                         * ArrayList, the user will have different number of coins and total money now.
                         */

                        shopper.changeCandW(orderSelect, totalAmount);
                    } catch (IOException ex) {
                                Logger.getLogger(MenuManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    try {
                        keepTrying = true;
                        shopper.orderList.get(orderSelect).c.levelOfHappiness -=1;
                        throw new MoneyException();
                    } catch (MoneyException ex) {
                        System.out.println("Not enough money, please pay though credit");
                    }
                }
            }while(keepTrying);
        }
        else{

            System.out.println("Make a cashier active before paying");
        }
    }

    //To create a stockers menu
    public void stockerMenu(){
    
        System.out.println("1. Create Stocker");
        System.out.println("2. Refill Ice Cream");
        System.out.println("3. Setting on  current Stocker on break");
        
        int option = keyboard.nextInt();
        
        switch(option){
        
            case 1: 
                createStocker();
                break;
            case 2: 
                refillIceCream();
                break;
            case 3:
                setStockerOnBreak();
                break;
        }
    }
    
    //to assign a stocker
    public void createStocker(){
        
        int n = 0;
        for(int i = 0; i < shopper.workerList.size(); i++){
        
            if(shopper.workerList.get(i).typeOfWorker.equals("Stocker")){
                
                if(!shopper.workerList.get(i).getActive()){
            
                    System.out.println((i+1) +". " + shopper.workerList.get(i).workerName);
                }
                else {
                    n++;
                    System.out.println("A Stocker is currently active");
                    break;
                }
            }
        }
        if(n == 0){
            int stockerOption =keyboard.nextInt();
            stockerOption--;
            shopper.makeStockerActive(stockerOption);
        }
    }
    
    //To refill the ice cream
    public void refillIceCream(){
    
        System.out.println("Which Ice Cream to refill");
        for(int i = 0; i < IceCreamList.size(); i++){
            
                System.out.printf("%d. %s\n", i+1,IceCreamList.get(i).name);
        }
        
        int option = keyboard.nextInt();
        option--;
        shopper.refillIceCream(option);
    }
    
    // to put the stocker on break;
    public void setStockerOnBreak(){
        
        int n = 0;
    
        for(int i = 0; i < shopper.workerList.size(); i++){
        
            if((shopper.workerList.get(i).typeOfWorker.equals("Stocker")) & !(shopper.workerList.get(i).getOnBreak())){
                n++;
                shopper.workerList.get(i).setOnBreak();
                shopper.workerList.get(i).setActive();
                
            }
        }
        if(n==0){
            
                System.out.println("There is no active Stocker to put on break");
            }
    }
    
    //to display the cashier menu
    public void cashierMenu(){
    
        System.out.println("1. Create Cashier");
        System.out.println("2. Setting current Cashier on break");
        
        int option = keyboard.nextInt();
        
        switch(option){
        
            case 1: 
                createCashier();
                break;
            case 2: 
                setCashierOnBreak();
                break;
        }
    }
    
    //to make a cashier active
    public void createCashier(){
    
        int n = 0;
        int unitoption = 0;
        for(int i = 0; i < shopper.workerList.size(); i++){
        
            if(shopper.workerList.get(i).typeOfWorker.equals("Cashier")){
                
                if(!shopper.workerList.get(i).getActive()){
                    if(i+1 == 5) unitoption = 1;
                    else if(i+1 == 6) unitoption = 2;
                    System.out.println(unitoption +". " + shopper.workerList.get(i).workerName);
                }
                else {
                    n++;
                    System.out.println("A Cashier is currently active");
                    break;
                }
            }
        }
        if(n == 0){
            int cashierOption = keyboard.nextInt();
            if(cashierOption == 1) cashierOption = 4;
            else cashierOption =5;
            shopper.makeCashierActive(cashierOption);
        }
    }
    
    //to put a cashier on break
    public void setCashierOnBreak(){
        
        int n = 0;
    
        for(int i = 0; i < shopper.workerList.size(); i++){

            if((shopper.workerList.get(i).typeOfWorker.equals("Cashier")) & !(shopper.workerList.get(i).getOnBreak())){
                n++;
                shopper.workerList.get(i).setOnBreak();
                shopper.workerList.get(i).setActive();

            }
        }
        if(n==0){

                System.out.println("There is no active Cashier to put on break");
            }
    }
    
    //Displaying the status report.
    public void statusReport(){

        System.out.println("1. Waiting Orders");
        System.out.println("2. Who is in the ice cream parlor");
        System.out.println("3. How much money is in the cash register");
        System.out.println("4. Ice Cream Inventory");

        
        int option = keyboard.nextInt();
        
        switch(option){
        
            case 1:
                for(int i = 0; i < shopper.orderList.size(); i++){
                    
                    //Checking if the system has any unpaid orders or not.
                    if(shopper.orderList.get(i).status == false){
                    
                        System.out.printf("\nOrder Number: %d", shopper.orderList.get(i).orderNumber);
                        System.out.printf("\nWorker: %s", shopper.orderList.get(i).w.workerName);
                        System.out.printf("\nCustomer: %s", shopper.orderList.get(i).c.name);
                        System.out.println("\nAll the servings with prices are: ");
                        //for loop for displaying all the servings.
                        for(int j = 0; j < shopper.orderList.get(i).servings.size(); j++){
                            
                            if(shopper.orderList.get(i).status == false){
                                shopper.orderList.get(i).servings.get(j).displayServingDetails();
                            }
                        }
                    }
                }
                break;
            case 2:
                //Displaying all the workers and customers in the class.
                System.out.print("\nWorkers:\n ");
                for(int i = 0; i < shopper.workerList.size(); i++){
                    
                    shopper.workerList.get(i).displayDetails();
                }
                System.out.print("\nCustomers:\n ");
                for(int i = 0; i < shopper.customerList.size(); i++){
                    shopper.customerList.get(i).displayDetails();
               }
                break;
            case 3:
                //Displaying the total money and the number of coins in the cash register.
                System.out.printf("\nMoney in Cash Register: %f", shopper.cr.ShopMoney);
                shopper.cashRegisterCoins();
                break;
            case 4:
                for(int i = 0; i < shopper.iceCreamList.size(); i++){
                
            
                System.out.println(i+1 + ". " + shopper.iceCreamList.get(i).name + "  " + shopper.iceCreamList.get(i).GallonsIC);
            }
        }

    }
    
    public void exceptionHandling(){
    
        System.out.println("1) Not enough Ice Cream");
        System.out.println("2) Not enough Money");
        
        int option = keyboard.nextInt();
        
        switch(option){
        
            case 1:
                System.out.println("Setting all the values of amount of Ice Cream to 0");
                
                for(int i = 0; i < shopper.iceCreamList.size(); i++)
                    shopper.iceCreamList.get(i).GallonsIC = 0;
                break;
            
            case 2:
                System.out.println("No cash in: ");
                System.out.println("1) Cash Register");
                System.out.println("2) A specific person");
                
                int optionCase = keyboard.nextInt();
                makeItZero(optionCase);
                break;
        }
    }
    
    public void makeItZero(int option){
    
        switch(option){
        
            case 1: 
                shopper.cr.ShopMoney = 0;
                break;
            case 2: 
                for(int i = 0; i < customer.size() ; i++){
            
                    System.out.printf("%d. %s\n", i+1,customer.get(i).name);
                }
                int customerOption = keyboard.nextInt();
        
                customerOption--;
                shopper.customerList.get(customerOption).wallet = 0;
        }
    }
    
}

