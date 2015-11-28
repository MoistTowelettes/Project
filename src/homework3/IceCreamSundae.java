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
public class IceCreamSundae extends Serving{
    
    //class attributes
    int numberOfScoops;
    IceCreamMain[] iceCreamArray = new IceCreamMain[2];
    boolean nuts;
    
    
    int typeOfExtra;
    
    //constructor
    IceCreamSundae(){
    
        this.typeofServing = "Sundaes";
    }
    
    //get the total number of scoops
    @Override
    public int getNumberOfScoops(){
    
        return numberOfScoops;
    }
    
    //setting the number of scoops
    @Override
    public void setNumberOfScoops(int n){
    
        numberOfScoops = n;
    }
    
    //getting the overall price
    @Override
    public double getOverAllPrice(){
   
        for(int i = 0; i < numberOfScoops; i++){
        
            this.overallPrice += flavors[i].price;
        }
        
        overallPrice += 1.00;
        return overallPrice;
    }
    
    //setting the type of extras
    @Override
    public void setTypeOfExtras(int n){
    
        this.typeOfExtra = n;
    }
    
    //displaying the serving details
    @Override
    public void displayServingDetails(){
    
        for(int i = 0; i < this.numberOfScoops; i++){
            System.out.println("Name: " + this.flavors[i].name + "  Price: " + this.flavors[i].price);
        }
        
        System.out.println("Type of syrup the user chose: ");
        switch(this.typeOfExtra){
        
            case 1: 
                System.out.println("Strawberry");
                break;
            case 2:
                System.out.println("Chocolate");
                break;
            case 3:
                System.out.println("Marshmallow cream");
                break;
            case 4:
                System.out.println("Pineapple");
                break;
            case 5:
                System.out.println("Ketchup");
                break;
            case 6:
                System.out.println("Mustard");
                break;
            case 7:
                System.out.println("Pickle Relish");
                break;
        }
        
        double price = 0;
        for(int i = 0; i < numberOfScoops; i++){
        
            price += flavors[i].price;
        }
        
        price += 1.00;
        System.out.println("Overall Price: " + price);
        if(nuts)
            System.out.println("Add deez Nuts?: Yes");
        else
        System.out.println("Add deez Nuts?: Yes");
    }
    
    //setthing if the user want nuts or not.
    @Override
    public void setNuts(boolean value){
    
        nuts = value;
    }
    
}
