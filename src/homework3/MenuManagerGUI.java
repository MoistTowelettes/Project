package homework3;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.text.Text;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

import java.util.ArrayList;

public class MenuManagerGUI extends Application {

    private Shop shopper;
    ArrayList<Customer> customer;
    ArrayList <Worker> worker;
    ArrayList <IceCreamMain> IceCreamList;

    public MenuManagerGUI() {
        customer = new ArrayList<>();
        worker = new ArrayList<>();
        IceCreamList = new ArrayList<>();
        shopper = new Shop();
    }

    public void setShopcontroller(Shop shopcontroller){
        this.shopper = shopcontroller;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane layout;

        /*
        shopper.createCustomerArrayList();
        customer = shopper.customerList;
        shopper.createWorkerArrayList();
        worker = shopper.workerList;
        shopper.createIceCreamArrayList();
        IceCreamList = shopper.iceCreamList;
        */

        primaryStage.setTitle("Honey Badger Ice Cream Parlor");
        
        //File menu
        Menu fileMenu = new Menu("File");
        MenuItem fileIceCream = new MenuItem("Ice Cream");
        fileIceCream.setOnAction((ActionEvent event) -> {
            iceCreamFileChooser(primaryStage);
        });
        MenuItem fileWorkers = new MenuItem("Workers");
        fileWorkers.setOnAction((ActionEvent event) -> {
            workerFileChooser(primaryStage);
        });
        MenuItem fileCustomers = new MenuItem("Customers");
        fileCustomers.setOnAction((ActionEvent event) -> {
            customerFileChooser(primaryStage);
        });
        Menu fileSave = new Menu("Save");
        MenuItem saveAll = new MenuItem("Save All");
        saveAll.setOnAction(e -> {
            shopper.saveAllFiles();
        });
        MenuItem saveIceCream = new MenuItem("Save Ice Cream");
        saveIceCream.setOnAction(e -> {
            shopper.ic.saveIceCream(shopper.iceCreamList);
        });
        MenuItem saveWorkers = new MenuItem("Save Workers");
        saveWorkers.setOnAction(e -> {
            shopper.wor.saveWorker(shopper.workerList);
        });
        MenuItem saveCustomers = new MenuItem("Save Customers");
        saveCustomers.setOnAction(e -> {
            shopper.cus.saveCustomer(shopper.customerList);
        });
        fileSave.getItems().addAll(saveAll, saveIceCream, saveWorkers, saveCustomers);
        MenuItem fileExit = new MenuItem("Exit");
        fileExit.setOnAction((ActionEvent event) -> {
            primaryStage.close();
        });
        fileMenu.getItems().addAll(fileIceCream, fileWorkers, fileCustomers, fileSave, fileExit);
        
        //Create Menu
        Menu createMenu = new Menu("Create");
        MenuItem createIceCream = new MenuItem("Create Ice Cream");
        MenuItem createWorker = new MenuItem("Create Worker");
        MenuItem createCustomer = new MenuItem("Create Customer");
        createMenu.getItems().addAll(createIceCream, createWorker, createCustomer);

        //Update Menu
        Menu updateMenu = new Menu("Update");
        MenuItem updateIceCream = new MenuItem("Update Ice Cream");
        MenuItem updateWorker = new MenuItem("Update Worker");
        MenuItem updateCustomer = new MenuItem("Update Customer");
        updateMenu.getItems().addAll(updateIceCream, updateWorker, updateCustomer);
        
        //Task Menu
        Menu tasksMenu = new Menu("Tasks");
        MenuItem tasksPayOrder = new MenuItem("Pay Order");
        MenuItem tasksPlaceOrder = new MenuItem("Place Order");
        MenuItem tasksActiveCashier = new MenuItem("Active Cashier");
        MenuItem tasksActiveStocker = new MenuItem("Active Stocker");
        MenuItem tasksCashierOnBreak = new MenuItem("Cashier on Break");
        MenuItem tasksStockerOnBreak = new MenuItem("Stocker on Break");
        tasksMenu.getItems().addAll(tasksPayOrder, tasksPlaceOrder,
                tasksActiveCashier, tasksActiveStocker, tasksCashierOnBreak,
                tasksStockerOnBreak);
        
        //Charts Menu
        Menu chartsMenu = new Menu("Charts");
        MenuItem chartsHappinessPie = new MenuItem("Happiness Pie Chart");
        MenuItem chartsMoneyPie = new MenuItem("Money Pie Chart");
        MenuItem chartsHappinessBar = new MenuItem("Happiness Bar Chart");
        MenuItem chartsMoneyBar = new MenuItem("Money Bar Chart");
        chartsMenu.getItems().addAll(chartsHappinessPie, chartsMoneyPie,
                chartsHappinessBar, chartsMoneyBar);
        
        //About Menu
        Menu aboutMenu = new Menu("About");
        
        //Create the Menu bar along the top
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, createMenu, updateMenu, tasksMenu,
                chartsMenu, aboutMenu);
        
        layout = new BorderPane();
        
        layout.setTop(menuBar);
        Scene scene = new Scene(layout, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void iceCreamFileChooser(Stage stage) {
        FileChooser icFile = new FileChooser();
        icFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = icFile.showOpenDialog(stage);

        if (file != null) {
            shopper.createIceCreamArrayList(file);
        }
    }

    public void workerFileChooser(Stage stage) {
        FileChooser wFile = new FileChooser();
        wFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = wFile.showOpenDialog(stage);
        if (file != null) {
            shopper.createWorkerArrayList(file);
        }
    }

    public void customerFileChooser(Stage stage) {
        FileChooser cFile = new FileChooser();
        cFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = cFile.showOpenDialog(stage);

        if (file != null) {
            shopper.createCustomerArrayList(file);
        }
    }
}
