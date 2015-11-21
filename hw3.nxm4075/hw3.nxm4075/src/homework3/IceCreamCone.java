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
public class IceCreamCone extends Serving {
    
    int numberOfScoops;
    
    int typeOfExtra;
    
    //constructor
    IceCreamCone(){
        
        this.typeofServing = "Cones";
    }
    
    //et the number of scoops
    @Override
    public int getNumberOfScoops(){
    
        return numberOfScoops;
    }
    
    //to set the number of scoops
    @Override
    public void setNumberOfScoops(int n){
    
        this.numberOfScoops = n;
    }
    
    //to get the overall price
    @Override
    public double getOverAllPrice(){
   
        for(int i = 0; i < this.numberOfScoops; i++){
            
            this.overallPrice += this.flavors[i].price;
        } 
        
        return overallPrice;
    }
    
    //to get the type of extras
    @Override
    public void setTypeOfExtras(int n){
    
        this.typeOfExtra = n;
    }
    
    //to display the serving details
    @Override
    public void displayServingDetails(){
    
        for(int i = 0; i < this.numberOfScoops; i++){
            System.out.println("Name: " + this.flavors[i].name + "  Price: " + this.flavors[i].price);
        }
        
        System.out.println("Type of cone the user chose: ");
        switch(this.typeOfExtra){
        
            case 1: System.out.println("Cake Cone");
                break;
            case 2:System.out.println("Sugar Cone");
                break;
            case 3:System.out.println("Waffle Cone");
                break;
        }
        
        double overallPriceDisplay = 0;
        for(int i = 0; i < this.numberOfScoops; i++){
            
            overallPriceDisplay += this.flavors[i].price;
        }
        
        System.out.println("Overall Price: " + overallPriceDisplay);
    }
    
}
