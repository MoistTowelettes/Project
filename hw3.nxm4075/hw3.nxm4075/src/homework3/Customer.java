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
    //File name of the file i'm reading.
    static String FILENAME = "customerFile.txt";
    //created a variable to keep count of the file that is being used and delete the previous one.
    static int n = 1;
    

    //This is the constructor. Constructors are called every time an object
    //is instantiated from a class.
    public Customer(){
        
        name = "Nice guy";
        levelOfHappiness = 10;
        wallet = 100.00;
    }
    
    
     public ArrayList<Customer> getCustomerList(){
        
        String line;
        String tokens[];
        File customerFile = new File(FILENAME);
        ArrayList <Customer> cusList = new ArrayList<>();
        try {
            Scanner inputCustomer=new Scanner(customerFile);
            while (inputCustomer.hasNextLine())
            {
                line=inputCustomer.nextLine();
                tokens=line.split(", ");
                Customer cus = new Customer();
                //reading all the contents from the file and storing it in the respective variables.
                cus.idNumber = parseLong(tokens[0]);
                cus.name = tokens[1];
                cus.wallet = parseDouble(tokens[2]);
                cus.levelOfHappiness = parseInt(tokens[3]);
                cus.t.pennies = parseInt(tokens[4]);
                cus.t.nickels = parseInt(tokens[5]);
                cus.t.dimes = parseInt(tokens[6]);
                cus.t.quaters = parseInt(tokens[7]);
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
     
    
    public void updateFile() throws FileNotFoundException{
        
        //creating a new file to write the data in.
        File f = new File("customerFile(" + n + ").txt");
        //the old file from where the data is going to be read in.
        File customerFile = new File(FILENAME);
        Formatter myFormatter;
        String line;
        String tokens[];
        myFormatter = new Formatter(f);
        //creating a scanner to read the data from the input file.
        Scanner inputCustomer=new Scanner(customerFile);
        
        while (inputCustomer.hasNextLine()){
            line=inputCustomer.nextLine();
            tokens=line.split(", ");
            String name = this.name;
            //checks if the name chosen by the user is matches with the name in the file.
            if(tokens[1].equals(name)){
                tokens[2] = String.valueOf(this.wallet);
                tokens[3] = String.valueOf(this.levelOfHappiness);
                tokens[4] = String.valueOf(this.t.pennies);
                tokens[5] = String.valueOf(this.t.nickels);
                tokens[6] = String.valueOf(this.t.dimes);
                tokens[7] = String.valueOf(this.t.quaters);
                tokens[8] = String.valueOf(this.t.ones);
                tokens[9] = String.valueOf(this.t.fives);
                tokens[10] = String.valueOf(this.t.tens);
                tokens[11] = String.valueOf(this.t.twenties);
                
                String newLine = tokens[0];
                //Converting the string array to a string because the toString() wasn't working.
                for(int i = 1; i < tokens.length; i++){
                    newLine = newLine + ", " + tokens[i];
                }
                myFormatter.format(newLine + "%n");
            }
            else{myFormatter.format(line + "%n");}
            
        }
        
        //changing the global name of the file so the system reads from the most newly updated file.
        FILENAME = "customerFile(" + n + ").txt";
        n++;
        myFormatter.flush();
        myFormatter.close();
        //closing the input stream on the file from which we were reading.
        inputCustomer.close();
        //Deleting the previous file.
        customerFile.delete();
        
    }
    
    public void displayDetails(){
    
        System.out.println("ID Number: " + this.idNumber);
        System.out.println("Name: " + this.name);
        System.out.println("Happiness level: " + this.levelOfHappiness);
        System.out.println("Money: " + this.wallet);
    }
}
