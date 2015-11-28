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
public class RootBeerFloat extends Serving{
    
    static double price = 1.25;
    
    //constructor
    RootBeerFloat(){
    
        this.overallPrice = price;
        this.iceCreamNumber.add(0);
    }

    //serving details
    @Override
    public void displayServingDetails(){
    
        System.out.println("Name: Vanilla Ice Cream  Price: 1.00");

        System.out.println("Overall Price: " + price);
    }
    
}
