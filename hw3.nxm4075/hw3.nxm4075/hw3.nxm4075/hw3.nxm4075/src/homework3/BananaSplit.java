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
public class BananaSplit extends IceCreamSundae{
    
    //attributes for this class
    int numberOfScoops;
    boolean Nuts;
    int[] typeOfExtra ;
    
   void BananaSplit()
   {numberOfScoops=3;
   Nuts=false;
   typeOfExtra=new int[3];
   }
    
    // get the overall price
    @Override
    public double getOverAllPrice(){
   
        for(int i = 0; i < numberOfScoops; i++){
        
            this.overallPrice += flavors[i].price;
        }
        
        overallPrice += 2.00;
        return overallPrice;
    }
    
    //get the total number of scoops
    @Override
    public int getNumberOfScoops(){
    
        return numberOfScoops;
    }
    
    //to get the extras
    @Override
    public void setTypeOfExtras(int[] n){
    
       this.typeOfExtra = n.clone();
    }
    
    //to get the serving details
    @Override
    public void displayServingDetails(){
    
        for(int i = 0; i < this.numberOfScoops; i++){
            System.out.println("Name: " + this.flavors[i].name + "  Price: " + this.flavors[i].price);
        }
        
        for(int i = 0; i < 3; i++){
            System.out.println("Type of syrup the user chose: ");
            switch(typeOfExtra[i]){

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
        }
        double price = 0;
        for(int i = 0; i < numberOfScoops; i++){
        
            price += flavors[i].price;
        }
        
        price += 2.00;
        System.out.println("Price: " + price);
        if(Nuts)
            System.out.println("Add deez Nuts?: Yes");
        else
        System.out.println("Add deez Nuts?: No");
    }
    
    //to set "yes" or "no" for nuts
    @Override
    public void setNuts(boolean value){
    
        Nuts = value;
    }
    
}
