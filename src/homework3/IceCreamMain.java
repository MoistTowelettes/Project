package homework3;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Formatter;

/**
 *
 * @author Neil
 */

public class IceCreamMain {

    //These are the attributes in iceCream
    long idNumber;
    double price;
    String name;
    String description;
    int gallonsIC;
    File iceCreamFile;
    
    public IceCreamMain(){
        idNumber = 0;
        gallonsIC = 80;
        price = 10.00;
        name = "Vanilla";
        description = "Pure Vanilla";
    }
    
    /**
     * Getting a list of all the ice creams from the TEXT file and storing it in the
     * iceCream ArrayList within this class and then returning it to the ArrayList 
     * @return 
     */
    public ArrayList<IceCreamMain> getIceCreamList(File iceCreamFile){
        String line;
        String tokens[];
        ArrayList <IceCreamMain> icList = new ArrayList<>();
        try {
            //File iceCreamFile = new File("iceCreamFile.txt");
            //System.out.println(iceCreamFile.getAbsolutePath());
            this.iceCreamFile = iceCreamFile;
            Scanner inputIceCream = new Scanner(iceCreamFile);
            System.out.println(iceCreamFile.getAbsolutePath());
            System.out.println("Successfully loaded file");
            while (inputIceCream.hasNextLine())
            {
                line=inputIceCream.nextLine();
                tokens=line.split(", ");
                IceCreamMain ic = new IceCreamMain();
                ic.idNumber = parseLong(tokens[0]);
                ic.price = parseDouble(tokens[1]);
                ic.name = tokens[2];
                ic.description = tokens[3];
                icList.add(ic);
            }
            inputIceCream.close();
        }catch(FileNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return icList;
    }

    //save to IceCreamList.txt
    public void saveIceCream(ArrayList<IceCreamMain> iceCream) {
        int iceCreamLen = iceCream.size();
        //System.out.println(iceCreamFile.exists());
        System.out.println(iceCreamFile);
        try {
            Formatter myFormatter = new Formatter(iceCreamFile);
            for (int i = 0; i < iceCreamLen; i++) {
                if (i != iceCreamLen-1) {
                    myFormatter.format("%s, %s, %s, %s, %d\n", iceCream.get(i).idNumber,
                            iceCream.get(i).price, iceCream.get(i).name,
                            iceCream.get(i).description, iceCream.get(i).gallonsIC);
                }
                else {
                    myFormatter.format("%s, %s, %s, %s, %d", iceCream.get(i).idNumber,
                            iceCream.get(i).price, iceCream.get(i).name,
                            iceCream.get(i).description, iceCream.get(i).gallonsIC);
                }
            }
            myFormatter.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("ICE CREAM FILE NOT FOUND");
        }
    }

    public String toString(){
        return name;
    }
}
