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
                tokens=line.split(", |,");
                
                Worker wor = checkForWorkerType(tokens[1]);
                wor.idNumber = parseLong(tokens[0]);
                wor.typeOfWorker = tokens[1];
                wor.workerName = tokens[2];
                wor.customerServed = parseLong(tokens[3]);
                wor.scoopsServed = parseInt(tokens[4]);
                wor.moneyTaken = Double.parseDouble(tokens[5]);

                switch (wor.typeOfWorker) {
                    case "Cashier":
                        wor.setPatience(Integer.parseInt(tokens[6]));
                        break;
                    case "Stocker":
                        wor.setStamina(Integer.parseInt(tokens[6]));
                        break;
                    default:
                        break;
                }

                //need to add patience and stamina
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
                    switch (worker.get(i).getClass().getSimpleName()){
                        case "Cashier":
                            myFormatter.format("%s, %s, %s, %d, %d, %s, %d\n", worker.get(i).idNumber,
                                    worker.get(i).getClass().getSimpleName(), worker.get(i).workerName,
                                    worker.get(i).scoopsServed, worker.get(i).customerServed, worker.get(i).moneyTaken,
                                    worker.get(i).getPatience());
                            break;
                        case "Stocker":
                            myFormatter.format("%s, %s, %s, %d, %d, %s, %d\n", worker.get(i).idNumber,
                                    worker.get(i).getClass().getSimpleName(), worker.get(i).workerName,
                                    worker.get(i).scoopsServed, worker.get(i).customerServed, worker.get(i).moneyTaken,
                                    worker.get(i).getStamina());
                            break;
                        default:
                            myFormatter.format("%s, %s, %s, %d, %d, %s\n", worker.get(i).idNumber,
                                    worker.get(i).getClass().getSimpleName(), worker.get(i).workerName,
                                    worker.get(i).scoopsServed, worker.get(i).customerServed, worker.get(i).moneyTaken);
                    }
                }

                else {
                    switch (worker.get(i).getClass().getSimpleName()){
                        case "Cashier":
                            myFormatter.format("%s, %s, %s, %d, %d, %s, %d", worker.get(i).idNumber,
                                    worker.get(i).getClass().getSimpleName(), worker.get(i).workerName,
                                    worker.get(i).scoopsServed, worker.get(i).customerServed, worker.get(i).moneyTaken,
                                    worker.get(i).getPatience());
                            break;
                        case "Stocker":
                            myFormatter.format("%s, %s, %s, %d, %d, %s, %d", worker.get(i).idNumber,
                                    worker.get(i).getClass().getSimpleName(), worker.get(i).workerName,
                                    worker.get(i).scoopsServed, worker.get(i).customerServed, worker.get(i).moneyTaken,
                                    worker.get(i).getStamina());
                            break;
                        default:
                            myFormatter.format("%s, %s, %s, %d, %d, %s", worker.get(i).idNumber,
                                    worker.get(i).getClass().getSimpleName(), worker.get(i).workerName,
                                    worker.get(i).scoopsServed, worker.get(i).customerServed, worker.get(i).moneyTaken);
                    }
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

    public String toString(){
        return this.workerName;
    }
}
