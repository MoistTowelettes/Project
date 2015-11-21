/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework3;

/**
 *
 * @author Neil
 */
public class Cashier extends Worker{
    
    int patience;
    boolean onBreak;
    static boolean active = false;
    
    //constructor
    public Cashier(){
    
        super();
        this.patience = 18;
        this.onBreak = true;
    }
    
    //to get the patience value
    @Override
    public int getPatience(){
    
        return patience;
    }
    
    //to set the patience value
    @Override
    public void setPatience(int number){
    
        if(number < 0){
            if(patience < 19)
                patience += number;
        }
        else{
            if(patience < 18)
                patience += number;
        }
    }
    
    //to get the onBreak Value
    @Override
    public boolean getOnBreak(){
    
        return onBreak;
    }
    
    //to set the worker on break
    @Override
    public void setOnBreak(){
    
        onBreak = !this.onBreak;
    }
    
    //to get the state
    @Override
    public boolean getActive(){
    
        return active;
    }
    
    // to fix the state
    @Override
    public void setActive(){
    
        active = !this.active;
    }
    
    //to display the worker details
    @Override
    public void displayDetails(){
    
        System.out.println("ID Number: " + this.idNumber);
        System.out.println("Type of Worker: " + this.typeOfWorker);
        System.out.println("Name: " + this.workerName);
        System.out.println("Money Taken: " + this.moneyTaken);
        System.out.println("Scoops Served: " + this.scoopsServed);
        System.out.println("Customer Served: " + this.customerServed);
        System.out.println("Patience: " + this.patience);
    }
}
