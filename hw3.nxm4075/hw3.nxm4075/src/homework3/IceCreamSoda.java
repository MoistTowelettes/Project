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
public class IceCreamSoda extends Serving{
    
    int numberOfScoops ;
    IceCreamMain[] iceCreamArray = new IceCreamMain[numberOfScoops];
    
    //constructor
    IceCreamSoda(){
        numberOfScoops=1;
        this.typeofServing = "Soda";
    }
    
    //get the number of scoops
    @Override
    public int getNumberOfScoops(){
    
        return numberOfScoops;
    }

    //setting the number of scoops
    @Override
    public void setNumberOfScoops(int n){
    
        numberOfScoops = 2;
    }
    
    //getting the overall price
    @Override
    public double getOverAllPrice(){
   
        for(int i = 0; i < numberOfScoops; i++){
        
            this.overallPrice += flavors[i].price;
        }
        
        overallPrice += 0.05;
        return overallPrice;
    }
    
    //displaying the serving details
    @Override
    public void displayServingDetails(){
    
        for(int i = 0; i < this.numberOfScoops; i++){
            System.out.println("Name: " + this.flavors[i].name + "  Price: " + this.flavors[i].price);
            System.out.println("Name: " + this.flavors[i].name + "  Price: " + this.flavors[i].price);
        }
        
        int price = 0;
        for(int i = 0; i < numberOfScoops; i++){
        
            price += flavors[i].price;
        }
        
        price += 0.05;
        System.out.println("Overall Price: " + price);
    }

}
