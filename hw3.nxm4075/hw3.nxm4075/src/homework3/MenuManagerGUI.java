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
import javafx.stage.Stage;

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


        shopper.createCustomerArrayList();
        customer = shopper.customerList;
        shopper.createWorkerArrayList();
        worker = shopper.workerList;
        shopper.createIceCreamArrayList();
        IceCreamList = shopper.iceCreamList;

        primaryStage.setTitle("Honey Badger Ice Cream Parlor");
        
        //File menu
        Menu fileMenu = new Menu("File");
        MenuItem fileIceCream = new MenuItem("Ice Cream");
        fileIceCream.setOnAction((ActionEvent event) -> {
            displayIceCream();
        });
        MenuItem fileWorkers = new MenuItem("Workers");
        fileWorkers.setOnAction((ActionEvent event) -> {
            displayWorker();
        });
        MenuItem fileCustomers = new MenuItem("Customers");
        fileCustomers.setOnAction((ActionEvent event) -> {
            displayCustomer();
        });
        MenuItem fileExit = new MenuItem("Exit");
        fileExit.setOnAction((ActionEvent event) -> {
            primaryStage.close();
        });
        fileMenu.getItems().addAll(fileIceCream, fileWorkers, fileCustomers, fileExit);
        
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

    public void displayIceCream() {
        Stage icStage = new Stage();
        Group group = new Group();
        ScrollBar sb = new ScrollBar();
        String icList = shopper.makeIceCreamString();
        int icLen = shopper.iceCreamList.size();

        Text icText = new Text(icList);
        VBox vb = new VBox();
        vb.getChildren().add(icText);
        group.getChildren().addAll(vb, sb);
        Scene scene = new Scene(group, 300, 300);
        sb.setLayoutX(scene.getWidth() - sb.getWidth());
        sb.setMin(0);
        sb.setOrientation(Orientation.VERTICAL);
        sb.setPrefHeight(scene.getHeight());
        sb.setMax(135 * icLen);
        icStage.setScene(scene);

        sb.valueProperty().addListener((observable, oldValue, newValue) -> {
            vb.setLayoutY(-newValue.doubleValue());
        });
        icStage.show();
    }

    public void displayWorker() {
        Stage wStage = new Stage();
        Group group = new Group();
        ScrollBar sb = new ScrollBar();
        String wList = shopper.makeWorkerString();
        int wLen = shopper.workerList.size();

        Text wText = new Text(wList);
        VBox vb = new VBox();
        vb.getChildren().add(wText);
        group.getChildren().addAll(vb, sb);
        Scene scene = new Scene(group, 300, 300);
        sb.setLayoutX(scene.getWidth() - sb.getWidth());
        sb.setMin(0);
        sb.setOrientation(Orientation.VERTICAL);
        sb.setPrefHeight(scene.getHeight());
        sb.setMax(120 * wLen);
        wStage.setScene(scene);

        sb.valueProperty().addListener((observable, oldValue, newValue) -> {
            vb.setLayoutY(-newValue.doubleValue());
        });
        wStage.show();
    }

    public void displayCustomer() {
        Stage cStage = new Stage();
        Group group = new Group();
        ScrollBar sb = new ScrollBar();
        String cList = shopper.makeCustomerString();
        int cLen = shopper.customerList.size();

        Text cText = new Text(cList);
        VBox vb = new VBox();
        vb.getChildren().add(cText);
        group.getChildren().addAll(vb, sb);
        Scene scene = new Scene(group, 300, 300);
        sb.setLayoutX(scene.getWidth() - sb.getWidth());
        sb.setMin(0);
        sb.setOrientation(Orientation.VERTICAL);
        sb.setPrefHeight(scene.getHeight());
        sb.setMax(100 * cLen);
        cStage.setScene(scene);

        sb.valueProperty().addListener((observable, oldValue, newValue) -> {
            vb.setLayoutY(-newValue.doubleValue());
        });
        cStage.show();
    }
}
