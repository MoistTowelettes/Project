package homework3;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neil
 */

public class Customer {

    //These are the attributes in customer
    long idNumber;
    String name;
    int levelOfHappiness;
    double wallet;
    // to store the number of coins.
    Transaction t = new Transaction();
    //created a variable to keep count of the file that is being used and delete the previous one.
    static int n = 1;
    File customerFile;
    

    //This is the constructor. Constructors are called every time an object
    //is instantiated from a class.
    public Customer(){
        
        name = "Nice guy";
        levelOfHappiness = 10;
        wallet = 100.00;
    }
    
    
     public ArrayList<Customer> getCustomerList(File customerFile){
        
        String line;
        String[] tokens;
        //File customerFile = new File(FILENAME);
        ArrayList <Customer> cusList = new ArrayList<>();
        try {
            this.customerFile = customerFile;
            Scanner inputCustomer = new Scanner(customerFile);
            System.out.println("Successfully loaded file");
            while (inputCustomer.hasNextLine())
            {
                line=inputCustomer.nextLine();
                tokens=line.split(", |,");
                Customer cus = new Customer();
                //reading all the contents from the file and storing it in the respective variables.
                cus.idNumber = parseLong(tokens[0]);
                cus.name = tokens[1];
                cus.wallet = parseDouble(tokens[2]);
                cus.levelOfHappiness = parseInt(tokens[3]);
                cus.t.pennies = parseInt(tokens[4]);
                cus.t.nickels = parseInt(tokens[5]);
                cus.t.dimes = parseInt(tokens[6]);
                cus.t.quarters = parseInt(tokens[7]);
                cus.t.ones = parseInt(tokens[8]);
                cus.t.fives = parseInt(tokens[9]);
                cus.t.tens = parseInt(tokens[10]);
                cus.t.twenties = parseInt(tokens[11]);
                //Adding the object to the ArrayList.
                cusList.add(cus);
            }

        }catch(FileNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Returning the arrayList to the sub-controller.
        return cusList;
    }

    //save customer file
    public void saveCustomer(ArrayList<Customer> customer) {
        int workerLen = customer.size();
        //File iceCreamFile = new File("CustomerFile.txt");
        try {
            Formatter myFormatter = new Formatter(customerFile);
            for (int i = 0; i < workerLen; i++) {
                if (i != workerLen-1) {
                    myFormatter.format("%s,%s,%s,%d,%d,%d,%d,%d,%d,%d,%d,%d\n", customer.get(i).idNumber,
                            customer.get(i).name, customer.get(i).wallet, customer.get(i).levelOfHappiness,
                            customer.get(i).t.pennies, customer.get(i).t.nickels, customer.get(i).t.dimes,
                            customer.get(i).t.quarters, customer.get(i).t.ones,
                            customer.get(i).t.fives, customer.get(i).t.tens,
                            customer.get(i).t.twenties);
                }
                else {
                    myFormatter.format("%s,%s,%s,%d,%d,%d,%d,%d,%d,%d,%d,%d", customer.get(i).idNumber,
                            customer.get(i).name, customer.get(i).wallet, customer.get(i).levelOfHappiness,
                            customer.get(i).t.pennies, customer.get(i).t.nickels, customer.get(i).t.dimes,
                            customer.get(i).t.quarters, customer.get(i).t.ones,
                            customer.get(i).t.fives, customer.get(i).t.tens,
                            customer.get(i).t.twenties);
                }
            }
            myFormatter.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("CUSTOMER FILE NOT FOUND");
        }
    }
    
    public void displayDetails(){
    
        System.out.println("ID Number: " + this.idNumber);
        System.out.println("Name: " + this.name);
        System.out.println("Happiness level: " + this.levelOfHappiness);
        System.out.println("Money: " + this.wallet);
    }

    public String toString(){
        return name;
    }

}
