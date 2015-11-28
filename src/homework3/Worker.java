package homework3;

import java.io.File;
import java.io.FileNotFoundException;
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

public class Worker {

    static String FILENAME = "workerFile.txt";
    static int n = 0;
    //this is an attribute in worker.
    long idNumber;
    String workerName;
    long customerServed;
    long scoopsServed;
    double moneyTaken;
    String typeOfWorker;
    File workerFile;
    
    public Worker(){

        //This is the constructor. Constructors are called every time an object
        //is instantiated from a class.
        workerName = "Suzie";
        idNumber = 0;
        customerServed = 0;
        scoopsServed = 0;
        moneyTaken = 0;
    }
    
    /**
     * It will find all the customers form the workerFile.txt text file and then 
     * create an ArrayList and return it to the Shop class where the the data will be
     * stored in the worker array list within the sub-controller (shop);
     * @return 
     */
    public ArrayList<Worker> getWorkerList(File workerFile){
        
        //File workerFile = new File(FILENAME);
        String line;
        String tokens[];
        ArrayList <Worker> worList = new ArrayList<>();
        try {
            this.workerFile = workerFile;
            Scanner inputWorker = new Scanner(workerFile);
            System.out.println("Successfully loaded file");
            while (inputWorker.hasNextLine()){
                line=inputWorker.nextLine();
                tokens=line.split(", ");
                
                Worker wor = checkForWorkerType(tokens[5]);
                wor.idNumber = parseLong(tokens[0]);
                wor.workerName = tokens[1];
                wor.customerServed = parseLong(tokens[2]);
                wor.scoopsServed = parseInt(tokens[3]);
                wor.typeOfWorker = tokens[5];
                
                worList.add(wor);
                
            }

        }catch(FileNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return worList;
    }

    public void saveWorker(ArrayList<Worker> worker) {
        int workerLen = worker.size();
        try {
            Formatter myFormatter = new Formatter(workerFile);
            for (int i = 0; i < workerLen; i++) {
                if (i != workerLen-1) {
                    myFormatter.format("%s, %s, %d, %d, %s, %s\n", worker.get(i).idNumber, worker.get(i).workerName,
                            worker.get(i).scoopsServed, worker.get(i).customerServed, worker.get(i).moneyTaken,
                            worker.get(i).getClass().getSimpleName());
                }
                else {
                    myFormatter.format("%s, %s, %d, %d, %s, %s", worker.get(i).idNumber, worker.get(i).workerName,
                            worker.get(i).scoopsServed, worker.get(i).customerServed, worker.get(i).moneyTaken,
                            worker.get(i).getClass().getSimpleName());
                }
            }
            myFormatter.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("WORKER FILE NOT FOUND");
        }
    }
    
    public Worker checkForWorkerType(String type){
    
        Worker wor;
        switch(type){
            
            case "Stocker":
                wor = new Stocker();
                return wor;
            case "Worker":
                wor = new Worker();
                return wor;
            case "Cashier":
                wor = new Cashier();
                return wor;
        }
        return null;
    }
    
    /**
     * Updating the file after the user has paid his share of money. He will have
     * less amount of coins and total money with him now.
     * @throws FileNotFoundException 
     */
    /*public void updateFile() throws FileNotFoundException{
    
        n++;
        File f = new File("workerFile(" + n +").txt");
        File workerFile = new File(FILENAME);
        Formatter myFormatter;
        String line;
        String tokens[];
        myFormatter = new Formatter(f);
        Scanner inputWorker=new Scanner(workerFile);
        
        while (inputWorker.hasNextLine()){
            line=inputWorker.nextLine();
            tokens=line.split(", ");
            String name = this.workerName;
            if(tokens[1].equals(name)){
                
                tokens[2] = String.valueOf(this.customerServed);
                tokens[3] = String.valueOf(this.scoopsServed);
                tokens[4] = String.valueOf(this.moneyTaken);
                String newLine = tokens[0];
                for(int i = 1; i < tokens.length; i++){
                    newLine = newLine + ", " + tokens[i];
                }
                myFormatter.format(newLine + "%n");
            }
            else{myFormatter.format(line + "%n");}
            
        }
        
        FILENAME = "workerFile(" + n + ").txt";
        myFormatter.flush();
        myFormatter.close();
        inputWorker.close();
        workerFile.delete();
    }*/
    
    public void displayDetails(){
    
        System.out.println("ID Number: " + this.idNumber);
        System.out.println("Type of Worker: " + this.typeOfWorker);
        System.out.println("Name: " + this.workerName);
        System.out.println("Money Taken: " + this.moneyTaken);
        System.out.println("Scoops Served: " + this.scoopsServed);
        System.out.println("Customer Served: " + this.customerServed);
    }
    /**
     * Polymorphism
     */
    
    public int getPatience(){
    
        return -1;
    }
    
    public void setPatience(int number){
    
        //abstract method.
    }
    
    public int getStamina(){
    
        return -1;
    }
    
    public void setStamina(int number){
    
        //abstract method.
    }
    
    public boolean getOnBreak(){
    
        return false;
    }
    
    public void setOnBreak(){
    
        //abstract method
    }
    
    public boolean getActive(){
    
        return true;
    }
    
    public void setActive(){
    
        //abstract method.
    }
    
}
