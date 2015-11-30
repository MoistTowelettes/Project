package homework3;


import java.util.ArrayList;

/**
 *
 * @author Neil
 */

public class Serving {

    //These are the attributes in serving
    double overallPrice;
    //iceCream ic;
    ArrayList iceCreamNumber = new ArrayList();
    String typeofServing;
    IceCreamMain[] flavors = new IceCreamMain[3];

    //This is the constructor. Constructors are called every time an object
    //is instantiated from a class.
    public Serving(){


        //overallPrice = ic.price;
        //System.out.printf("Created %s\n",getClass().getSimpleName());
    }
    //This function, when called, will print a report to the screen.
    public Serving(String type){
        this.typeofServing=type;
        overallPrice=100;
    }
    public String display(){

        String result=""; 
        result=result+String.format("Serving: ");
        result=result+String.format("\n");
        return result;
    }

    //This function is a setter, it updates a private attribute.
    public void setTypeofServing(String typeofServing){

        //this.typeofServing = typeofServing;
        
    }
    public double getOverAllPrice(){
    
        return overallPrice;
    }

    /**
     * Polymorphism
     */
    
    public int getNumberOfScoops(){
    
        return 0;
    }
    
    public void setNumberOfScoops(int n){
    
        //abstract method
    }
    
    public void setTypeOfExtras(int n){
    
        //abstract method
    }
    
    public void setTypeOfExtras(int n[]){
    
        //abstract method
    }
    
    public void displayServingDetails(){
    
        //abstract method
    }
    
    public void setNuts(boolean value){
    
        //abstract mathod.
    }

    public String toString(){
        return this.typeofServing;
    }
}
