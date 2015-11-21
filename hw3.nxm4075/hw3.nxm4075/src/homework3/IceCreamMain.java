package homework3;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neil
 */

public class IceCreamMain {

    //These are the attributes in iceCream
    String flavor;
    long idNumber;
    double price;
    String name;
    String description;
    int GallonsIC;  
    
    public IceCreamMain(){

        GallonsIC = 80;
        flavor = "";
        price = 10.00;
        name = "Vanilla";
        description = "Pure Vanilla";
    }
    
    /**
     * Getting a list of all the ice creams from the TEXT file and storing it in the
     * iceCream ArrayList within this class and then returning it to the ArrayList 
     * @return 
     */
    public ArrayList<IceCreamMain> getIceCreamList(){
        String line;
        String tokens[];
        ArrayList <IceCreamMain> icList = new ArrayList<>();
        try {
            File iceCreamFile = new File("iceCreamFile.txt");
            System.out.println(iceCreamFile.getAbsolutePath());
            Scanner inputIceCream= new Scanner(iceCreamFile);
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

        }catch(FileNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return icList;
    }


}
