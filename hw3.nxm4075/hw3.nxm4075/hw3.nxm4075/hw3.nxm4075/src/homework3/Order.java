package homework3;

import java.util.ArrayList;

/**
 *
 * @author Neil
 */

public class Order {

    //These are the attributes in order
    boolean status;
    int orderNumber;
    Customer c;
    Worker w;
    //ArrayList for keeping all the servings.
    ArrayList <Serving> servings = new ArrayList<>();

    //This is the constructor. Constructors are called every time an object
    //is instantiated from a class.
    public Order(){

        orderNumber = 314;
        status = false;
        System.out.printf("Created %s\n", getClass().getSimpleName());
    }
    

    //This function, when called, will print a report to the screen.
    public String display(){

        String result="";
        result=result+String.format("Order Number: %d\n", orderNumber);
        return result;
    }

    //This function is a setter, it updates a private attribute.
    public void setOrderNumber(int orderNumber){

        this.orderNumber = orderNumber;
    }
}
