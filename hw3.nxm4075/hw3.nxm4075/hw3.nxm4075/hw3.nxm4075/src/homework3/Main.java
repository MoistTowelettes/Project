package homework3;

import javafx.application.Application;

/**
 *
 * @author Neil
 */

public class Main {

    private MenuManagerGUI menuManagerGUI;
    private Shop shopper; 


    //The Constructor for the MAIN class
    public Main() {
        menuManagerGUI = new MenuManagerGUI();
        shopper = new Shop();
        menuManagerGUI.setShopcontroller(shopper);
    }

    /*public void go() {
        menuManager.go();//Start the Menu Manager
    }*/

    
    public static void main(String[] args) {
        
        Main main = new Main();
        Application.launch(MenuManagerGUI.class, args);
        //main.go();
    }
}
