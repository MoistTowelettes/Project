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
public class Stocker extends Worker{
    
    //attributes
    int stamina;
    boolean onBreak;
    static boolean active = false;
    
    //constructor
    public Stocker(){
    
        super();
        this.stamina = 18;
        this.onBreak = true;
    }
    
    //get the stamina
    @Override
    public int getStamina(){
    
        return stamina;
    }
    
    //getting the stamina
    @Override
    public void setStamina(int number){
    
        if(number < 0){
            if(stamina < 19)
                stamina += number;
        }
        else{
            if(stamina < 18)
                stamina += number;
        }
    }
    
    //getting the onBreak value
    @Override
    public boolean getOnBreak(){
    
        return onBreak;
    }
    
    //setting the user on break
    @Override
    public void setOnBreak(){
    
        this.onBreak = !(this.onBreak);
    }
    
    //getting the active state
    @Override
    public boolean getActive(){
    
        return active;
    }
    
    //set Active
    @Override
    public void setActive(){
    
        this.active = !(this.active);
    }

    //displaying details.
    @Override
    public void displayDetails(){
    
        System.out.println("ID Number: " + this.idNumber);
        System.out.println("Type of Worker: " + this.typeOfWorker);
        System.out.println("Name: " + this.workerName);
        System.out.println("Money Taken: " + this.moneyTaken);
        System.out.println("Scoops Served: " + this.scoopsServed);
        System.out.println("Customer Served: " + this.customerServed);
        System.out.println("Stamina: " + this.stamina);
    }
}
