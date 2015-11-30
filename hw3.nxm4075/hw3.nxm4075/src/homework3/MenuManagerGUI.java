package homework3;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import sun.reflect.annotation.ExceptionProxy;

public class MenuManagerGUI extends Application {

    private Shop shopper;
    ArrayList<Customer> customer;
    ArrayList <Worker> worker;
    ArrayList <IceCreamMain> IceCreamList;
    //added
    private static Stage stage;
    private static MenuBar menubar;
    Random rand = new Random(1000);

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
        this.stage=primaryStage;
        stage.setScene(primaryStage());
        stage.show();
    }

    public Scene primaryStage() {
        BorderPane layout;

        stage.setTitle("Honey Badger Ice Cream Parlor");
        
        //File menu
        Menu fileMenu = new Menu("File");
        MenuItem fileIceCream = new MenuItem("Ice Cream");
        fileIceCream.setOnAction((ActionEvent event) -> {
            iceCreamFileChooser(stage);
        });
        MenuItem fileWorkers = new MenuItem("Workers");
        fileWorkers.setOnAction((ActionEvent event) -> {
            workerFileChooser(stage);
        });
        MenuItem fileCustomers = new MenuItem("Customers");
        fileCustomers.setOnAction((ActionEvent event) -> {
            customerFileChooser(stage);
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
            stage.close();
        });
        fileMenu.getItems().addAll(fileIceCream, fileWorkers, fileCustomers, fileSave, fileExit);
        
        //Create Menu
        Menu createMenu = new Menu("Create");
        MenuItem createIceCream = new MenuItem("Create Ice Cream");
        createIceCream.setOnAction(e -> {
            stage.setScene(new Scene(createIceCream(), 400, 350));
        });
        MenuItem createWorker = new MenuItem("Create Worker");
        createWorker.setOnAction(e -> {
            stage.setScene(new Scene(createWorker(), 400, 350));
        });
        MenuItem createCustomer = new MenuItem("Create Customer");
        createCustomer.setOnAction(e -> {
            stage.setScene(new Scene(createCustomer(), 400, 350));
        });
        createMenu.getItems().addAll(createIceCream, createWorker, createCustomer);

        //Update Menu
        Menu updateMenu = new Menu("Update");
        MenuItem updateIceCream = new MenuItem("Update Ice Cream");
        updateIceCream.setOnAction(e -> {
            stage.setScene(new Scene(build_ice(), 400, 350));
        });
        MenuItem updateWorker = new MenuItem("Update Worker");
        updateWorker.setOnAction(e -> {
            stage.setScene(new Scene(build_worker(), 400, 350));
        });
        MenuItem updateCustomer = new MenuItem("Update Customer");
        updateCustomer.setOnAction(e -> {
            stage.setScene(new Scene(build_customer(), 400, 350));
        });
        updateMenu.getItems().addAll(updateIceCream, updateWorker, updateCustomer);

        //Task Menu
        Menu tasksMenu = new Menu("Tasks");
        MenuItem tasksPayOrder = new MenuItem("Pay Order");
        tasksPayOrder.setOnAction(e -> {
            stage.setScene(new Scene(pay_order(), 400, 350));
        });
        MenuItem tasksPlaceOrder = new MenuItem("Place Order");
        tasksPlaceOrder.setOnAction(e -> {
            stage.setScene(new Scene(place_order(), 400, 350));
        });
        MenuItem tasksActiveCashier = new MenuItem("Active Cashier");
        tasksActiveCashier.setOnAction(e -> {
            stage.setScene(new Scene(active_cashier(), 400, 350));
        });
        MenuItem tasksActiveStocker = new MenuItem("Active Stocker");
        tasksActiveStocker.setOnAction(e -> {
            stage.setScene(new Scene(active_stocker(), 400, 350));
        });
        MenuItem tasksCashierOnBreak = new MenuItem("Cashier on Break");
        tasksCashierOnBreak.setOnAction(e -> {
            stage.setScene(new Scene(set_cashier_break(), 400, 350));
        });
        MenuItem tasksStockerOnBreak = new MenuItem("Stocker on Break");
        tasksStockerOnBreak.setOnAction(e -> {
            stage.setScene(new Scene(set_stocker_break(), 400, 350));
        });
        tasksMenu.getItems().addAll(tasksPayOrder, tasksPlaceOrder,
                tasksActiveCashier, tasksActiveStocker, tasksCashierOnBreak,
                tasksStockerOnBreak);
        //taskmenue action
        tasksMenu.setOnAction(e->stage.setScene(task_menu()));
        //Charts Menu
        Menu chartsMenu = new Menu("Charts");
        MenuItem chartsHappinessPie = new MenuItem("Happiness Pie Chart");
        chartsHappinessPie.setOnAction((ActionEvent event) -> {

            int numberofcus=customer.size();
            int x[]= new int[numberofcus];
            for(int i=0;i<numberofcus;i++)

            {x[i] = customer.get(i).levelOfHappiness;

            }
            DefaultPieDataset pieDataset=new DefaultPieDataset();

            for( int i=0;i<numberofcus;i++)

            {  pieDataset.setValue(customer.get(i).name,new Integer(x[i]));}

            JFreeChart chart= ChartFactory.createPieChart3D("Happiness Pie Chart", pieDataset, true, true, true);
            PiePlot3D P=(PiePlot3D)chart.getPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Happiness Pie Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,450);
        });
        MenuItem chartsMoneyPie = new MenuItem("Money Pie Chart");

        chartsMoneyPie.setOnAction((ActionEvent event) -> {

            int numberofwork=worker.size();
            double x[]= new double[numberofwork];
            for(int i=0;i<numberofwork;i++)

            {x[i] = worker.get(i).moneyTaken;

            }
            DefaultPieDataset pieDataset=new DefaultPieDataset();

            for( int i=0;i<numberofwork;i++)

            {  pieDataset.setValue(worker.get(i).workerName,new Double(x[i]));}

            JFreeChart chart= ChartFactory.createPieChart3D("Money Pie Chart", pieDataset, true, true, true);
            PiePlot3D P=(PiePlot3D)chart.getPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Money Pie Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,450);
        });

        MenuItem chartsHappinessBar = new MenuItem("Happiness Bar Chart");
        chartsHappinessBar.setOnAction((ActionEvent event) -> {

            int numberofcus=customer.size();
            int x[]= new int[numberofcus];
            for(int i=0;i<numberofcus;i++)

            {x[i] = customer.get(i).levelOfHappiness;

            }
            DefaultCategoryDataset dataset=new DefaultCategoryDataset();

            for( int i=0;i<numberofcus;i++)

            {  dataset.setValue(new Integer(x[i]),"Happiness",customer.get(i).name);}

            JFreeChart chart= ChartFactory.createBarChart("Happiness Bar Chart", "Customers","Happiness", dataset, PlotOrientation.VERTICAL, false,true,false);
            CategoryPlot P=chart.getCategoryPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Happiness Bar Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,450);
        });


        MenuItem chartsMoneyBar = new MenuItem("Money Bar Chart");
        chartsMoneyBar.setOnAction((ActionEvent event) -> {

            int numberofwork=worker.size();
            double x[]= new double[numberofwork];
            for(int i=0;i<numberofwork;i++)

            {x[i] = worker.get(i).moneyTaken;

            }
            DefaultCategoryDataset dataset=new DefaultCategoryDataset();

            for( int i=0;i<numberofwork;i++)

            {  dataset.setValue(new Double(x[i]),"Money",worker.get(i).workerName);}

            JFreeChart chart= ChartFactory.createBarChart("Money Bar Chart", "Worker","Money", dataset, PlotOrientation.VERTICAL, false,true,false);
            CategoryPlot P=chart.getCategoryPlot();
            //P.setForegroundAlpha(TOP_ALIGNMENT);
            ChartFrame frame= new ChartFrame("Money Bar Chart",chart);
            frame.setVisible(true);
            frame.setSize(500,450);
        });



        chartsMenu.getItems().addAll(chartsHappinessPie, chartsMoneyPie,
                chartsHappinessBar, chartsMoneyBar);
        
        //About Menu
        Menu aboutMenu = new Menu("About");
        
        //Create the Menu bar along the top
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, createMenu, updateMenu, tasksMenu,
                chartsMenu, aboutMenu);

        //Create the Menu bar along the top
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, createMenu, updateMenu, tasksMenu,
                chartsMenu, aboutMenu);

        this.menubar=menuBar;

        layout = new BorderPane();

        layout.setTop(menuBar);
        Scene scene = new Scene(layout, 400, 350);
        //stage.setScene(scene);
        //stage.show();
        return scene;
    }

    public void iceCreamFileChooser(Stage stage) {
        FileChooser icFile = new FileChooser();
        icFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File file = icFile.showOpenDialog(stage);

        if (file != null) {
            shopper.createIceCreamArrayList(file);
            IceCreamList = shopper.iceCreamList;
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
            worker = shopper.workerList;
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
            customer = shopper.customerList;
        }
    }

    public BorderPane createIceCream() {
        BorderPane layout = new BorderPane();
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        //name
        Label nameLabel = new Label("Name:");
        TextField nameText = new TextField();
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameText, 1, 0);

        //price
        Label priceLabel = new Label("Price:");
        TextField priceText = new TextField();
        GridPane.setConstraints(priceLabel, 0, 1);
        GridPane.setConstraints(priceText, 1, 1);

        //flavor
        Label flavorLabel = new Label("Flavor:");
        TextField flavorText = new TextField();
        GridPane.setConstraints(flavorLabel, 0, 2);
        GridPane.setConstraints(flavorText, 1, 2);

        //description
        Label descriptionLabel = new Label("Description:");
        TextField descriptionText = new TextField();
        GridPane.setConstraints(descriptionLabel, 0, 3);
        GridPane.setConstraints(descriptionText, 1, 3);

        //scoops
        Label scoopLabel = new Label("Number of Scoops:");
        TextField scoopText = new TextField("80");
        GridPane.setConstraints(scoopLabel, 0, 4);
        GridPane.setConstraints(scoopText, 1, 4);

        //Submit
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 5);

        submit.setOnAction(e -> {
            shopper.addIceCream(nameText.getText(), priceText.getText(), flavorText.getText(),
                    descriptionText.getText(), scoopText.getText());
        });

        pane.getChildren().addAll(nameLabel, nameText, priceLabel, priceText, flavorLabel, flavorText,
                descriptionLabel, descriptionText, scoopLabel, scoopText, submit);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    public BorderPane createWorker() {
        BorderPane layout = new BorderPane();
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        //name
        Label nameLabel = new Label("Name:");
        TextField nameText = new TextField();
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameText,1, 0);

        //Id number
        Label idLabel = new Label("ID Number:");
        TextField idText = new TextField();
        GridPane.setConstraints(idLabel, 0, 1);
        GridPane.setConstraints(idText, 1, 1);

        //submit
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 2);

        submit.setOnAction(e -> {
            shopper.addWorker(nameText.getText(), idText.getText());
        });

        pane.getChildren().addAll(nameLabel, nameText, idLabel, idText, submit);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    public BorderPane createCustomer() {
        BorderPane layout = new BorderPane();
        GridPane pane = new GridPane();

        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(8);
        pane.setHgap(10);

        //name
        Label nameLabel = new Label("Name:");
        TextField nameText = new TextField();
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameText, 1, 0);

        //id number
        Label idLabel = new Label("ID Number:");
        TextField idText = new TextField();
        GridPane.setConstraints(idLabel, 0, 1);
        GridPane.setConstraints(idText, 1, 1);

        //level of happiness
        Label happinessLabel = new Label("Happiness Level:");
        TextField happinessText = new TextField();
        GridPane.setConstraints(happinessLabel, 0, 2);
        GridPane.setConstraints(happinessText, 1, 2);

        //wallet
        Label walletLabel = new Label("Cash in Wallet:");
        TextField walletText = new TextField();
        GridPane.setConstraints(walletLabel, 0, 3);
        GridPane.setConstraints(walletText, 1, 3);

        //submit
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 4);

        submit.setOnAction(e -> {
            shopper.addCustomer(nameText.getText(), idText.getText(), happinessText.getText(), walletText.getText());
        });
        pane.getChildren().addAll(nameLabel, nameText, idLabel, idText, happinessLabel, happinessText, walletLabel,
                walletText, submit);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    private BorderPane build_ice(){
        Label label1=new Label("Which Ice Cream:");
        Label dlabel= new Label("Description:");
        Label plabel= new Label("Price:");
        Button button=new Button("Submit");
        BorderPane layout = new BorderPane();
        GridPane pane = new GridPane();
        ChoiceBox<IceCreamMain> choice = new ChoiceBox<>();
        TextField dtext = new TextField();
        TextField ptext = new TextField();
        for(int x=0;x<IceCreamList.size();x++){
            choice.getItems().add(IceCreamList.get(x));
        }
        button.setOnAction(e->{
            try{
                System.out.println(dtext.getText());
                double p= Double.parseDouble(ptext.getText());
                choice.getValue().description=dtext.getText();
                choice.getValue().price=p;

                stage.setScene(primaryStage());
                //stage.setScene(update_Menu());
            }catch(Exception ex){
                System.err.println("Please enter a double for price!");
            }
        });

        choice.setOnAction(e->{
            dtext.setText(choice.getValue().description);
            ptext.setText(choice.getValue().price+"");




        });
        choice.setValue(IceCreamList.get(0));
        pane.add(label1,0,0);
        pane.add(choice, 1, 0);
        pane.add(dlabel,0,1);
        pane.add(dtext,1,1);
        pane.add(plabel, 0, 2);
        pane.add(ptext,1,2);
        pane.add(button,1,3);
        pane.setHgap(10.0);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    private BorderPane build_worker(){
        Label label1=new Label("Which worker:");
        Label clabel= new Label("Customers Served:");
        Label sslabel= new Label("Scoops Served:");
        Label mtlabel= new Label("Money Taken:");
        Button button=new Button("Submit");
        GridPane pane = new GridPane();
        BorderPane layout = new BorderPane();
        ChoiceBox<Worker> choice = new ChoiceBox<>();
        TextField ctext = new TextField();
        TextField sstext = new TextField();
        TextField mttext = new TextField();
        for(int x=0;x<worker.size();x++){
            choice.getItems().add(worker.get(x));
        }
        button.setOnAction(e->{
            try{
                long ss;
                long c;
                double mt;
                ss=Long.parseLong(sstext.getText());
                mt=Double.parseDouble(mttext.getText());

                c=Long.parseLong(ctext.getText());
                choice.getValue().moneyTaken=mt;
                choice.getValue().scoopsServed=ss;
                choice.getValue().customerServed=c;
                stage.setScene(primaryStage());
                //stage.setScene(update_Menu());
            }catch(Exception ex){
                System.err.println("Values not correct!");
            }
        });

        choice.setOnAction(e->{
            mttext.setText(""+choice.getValue().moneyTaken);
            sstext.setText(""+choice.getValue().scoopsServed);
            ctext.setText(""+choice.getValue().customerServed);

        });
        choice.setValue(worker.get(0));



        pane.add(label1,0,0);
        pane.add(choice, 1, 0);
        pane.add(clabel,0,1);
        pane.add(ctext,1,1);
        pane.add(sslabel, 0, 2);
        pane.add(sstext,1,2);
        pane.add(mtlabel, 0, 3);
        pane.add(mttext,1,3);
        pane.add(button,1,4);
        pane.setHgap(10.0);
        pane.setVgap(10.0);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    private BorderPane build_customer(){
        GridPane pane=new GridPane();
        BorderPane layout = new BorderPane();
        Label label1=new Label("Which Customer:");
        Label wlabel= new Label("Customer Money:");
        Label lhlabel = new Label("Level of Happiness:");
        TextField lhtext= new TextField();
        TextField wtext= new TextField();
        //Label sslabel= new Label("Scoops Served:");
        //Label mtlabel= new Label("Money Taken:");
        Button button=new Button("Submit");
        ChoiceBox<Customer>choice=new ChoiceBox<>();
        for(int x=0;x<customer.size();x++)choice.getItems().add(customer.get(x));
        button.setOnAction(e->{
            try{
                int l=Integer.parseInt(lhtext.getText());
                double w = Double.parseDouble(wtext.getText());
                choice.getValue().levelOfHappiness=l;
                choice.getValue().wallet=w;
                stage.setScene(primaryStage());
                //stage.setScene(update_Menu());
            }catch(Exception ex){
                System.err.println("TRY again!");
            }
        });



        choice.setOnAction(e->{
            lhtext.setText(choice.getValue().levelOfHappiness+"");
            wtext.setText(choice.getValue().wallet+"");

        });
        choice.setValue(customer.get(0));
        pane.add(label1,0,0);
        pane.add(choice, 1, 0);
        pane.add(wlabel,0,1);
        pane.add(wtext,1,1);
        pane.add(lhlabel, 0, 2);
        pane.add(lhtext,1,2);
        pane.add(button, 1, 3);
        pane.setHgap(10.0);
        pane.setVgap(10.0);

        layout.setTop(menubar);
        layout.setCenter(pane);

        return layout;
    }

    private BorderPane pay_order(){
        BorderPane layout = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        layout.setCenter(grid);
        layout.setTop(menubar);
        if(shopper.orderList.isEmpty()){
            grid.add(new Label("No order created!"),0,0);
            return layout;
        }
        Button button = new Button("Submit");



        Label label = new Label("Pay Order:");
        ChoiceBox<Order> choice = new ChoiceBox();
        for(int x=0;x<shopper.orderList.size();x++)choice.getItems().add(shopper.orderList.get(x));


        button.setOnAction(e->{
            try {
                shopper.pay_order(choice.getValue());
            }catch(MoneyException ex){
                System.out.println(ex);
            }
            stage.setScene(primaryStage());
        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }


    private BorderPane place_order(){
        //shopper.servingList.add(new Serving("Dog Shit"));
        BorderPane layout = new BorderPane();
        GridPane grid = new GridPane();
        ArrayList<Serving> servingList = new ArrayList<>();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        layout.setCenter(grid);
        layout.setTop(menubar);
        if(customer.isEmpty()){
            Label temp = new Label("No Customer Created");
            grid.add(temp,0,0);
            return layout;
        }
        else if(worker.isEmpty()){
            Label temp = new Label("No Worker Created");
            grid.add(temp,0,0);
            return layout;
        }
        else if(IceCreamList.isEmpty()){
            Label temp = new Label("No Ice Cream Created");
            grid.add(temp,0,0);
            return layout;
        }

        Label wl= new Label("Worker:");
        ChoiceBox<Worker> wc = new ChoiceBox<>();
        for(int x=0;x<worker.size();x++){

            if(worker.get(x).typeOfWorker!="Stocker" && !worker.get(x).getOnBreak()) {
                wc.getItems().add(worker.get(x));
                wc.setValue(worker.get(x));
            }
        }
        GridPane.setConstraints(wl, 0, 0);
        GridPane.setConstraints(wc, 1, 0);

        Label cl = new Label("Customer:");
        ChoiceBox<Customer> cc = new ChoiceBox<>();
        for(int x=0;x<customer.size();x++){

            cc.getItems().add(customer.get(x));
        }
        GridPane.setConstraints(cl, 0, 1);
        GridPane.setConstraints(cc, 1, 1);

        Label addLabel = new Label("Add to Order:");
        ChoiceBox<String> addChoice= new ChoiceBox<>();
        addChoice.getItems().addAll("Ice Cream Cone", "Ice Cream Sundae", "Banana Split", "Ice Cream Soda",
                "Root Beer Float");
        addChoice.setValue("Ice Cream Cone");
        Button addButton = new Button("Add");
        GridPane.setConstraints(addLabel, 0, 2);
        GridPane.setConstraints(addChoice, 1, 2);
        GridPane.setConstraints(addButton, 2, 2);
        addButton.setOnAction(e -> {
            switch (addChoice.getValue()) {
                case "Ice Cream Cone":
                    servingList.add(addIceCreamCone());
                    break;
                case "Ice Cream Sundae":
                    System.out.println("Ice Cream Sundae");
                    break;
                case "Banana Split":
                    System.out.println("Banana Split");
                    break;
                case "Ice Cream Soda":
                    System.out.println("Ice Cream Soda");
                    break;
                case "Root Beer Float":
                    System.out.println("Root Beer Float");
                    break;
            }
        });

        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 1, 3);
        submit.setOnAction(e -> {
            Order ord = new Order(cc.getValue(), wc.getValue(), servingList, rand);
            shopper.orderList.add(ord);
            stage.setScene(primaryStage());
        });

        grid.getChildren().addAll(wc, wl, cc, cl, addLabel, addChoice, addButton, submit);
        return layout;
    }

    public IceCreamCone addIceCreamCone(){
        Stage iccStage = new Stage();
        GridPane grid = new GridPane();
        IceCreamCone icc = new IceCreamCone();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label firstScoopLabel = new Label("1st Scoop:");
        ChoiceBox<IceCreamMain> firstScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(firstScoopLabel, 0, 0);
        GridPane.setConstraints(firstScoopChoice, 1, 0);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            firstScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label secondScoopLabel = new Label("2nd Scoop:");
        ChoiceBox<IceCreamMain> secondScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(secondScoopLabel, 0, 1);
        GridPane.setConstraints(secondScoopChoice,1, 1);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            secondScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label thirdScoopLabel = new Label("3rd Scoop:");
        ChoiceBox<IceCreamMain> thirdScoopChoice = new ChoiceBox<>();
        GridPane.setConstraints(thirdScoopLabel, 0, 2);
        GridPane.setConstraints(thirdScoopChoice, 1, 2);

        for(int x=0;x<shopper.iceCreamList.size();x++){

            thirdScoopChoice.getItems().add(shopper.iceCreamList.get(x));
        }

        Label coneTypeLabel = new Label("Type of Cone:");
        ChoiceBox<String> coneTypeChoice = new ChoiceBox<>();
        GridPane.setConstraints(coneTypeLabel, 0, 3);
        GridPane.setConstraints(coneTypeChoice, 1, 3);

        coneTypeChoice.getItems().addAll("Cake Cone", "Sugar Cone", "Waffle Cone");
        coneTypeChoice.setValue("Cake Cone");

        Button add = new Button("Add");
        GridPane.setConstraints(add, 1, 4);
        add.setOnAction(e -> {
            if (firstScoopChoice != null && secondScoopChoice == null && thirdScoopChoice == null) {
                icc.numberOfScoops = 1;
                icc.flavors[0] = firstScoopChoice.getValue();
            }
            else if (firstScoopChoice != null && secondScoopChoice != null && thirdScoopChoice == null) {
                icc.numberOfScoops = 2;
                icc.flavors[0] = firstScoopChoice.getValue();
                icc.flavors[1] = secondScoopChoice.getValue();
            }
            else if (firstScoopChoice != null && secondScoopChoice != null && thirdScoopChoice != null) {
                icc.numberOfScoops = 3;
                icc.flavors[0] = firstScoopChoice.getValue();
                icc.flavors[1] = secondScoopChoice.getValue();
                icc.flavors[2] = thirdScoopChoice.getValue();
            }
            icc.coneType = coneTypeChoice.getValue();
            iccStage.close();
        });

        grid.getChildren().addAll(firstScoopLabel, firstScoopChoice, secondScoopLabel, secondScoopChoice,
                thirdScoopLabel, thirdScoopChoice, coneTypeLabel, coneTypeChoice, add);

        iccStage.setScene(new Scene(grid, 300, 300));
        iccStage.show();
        return icc;
    }

    private BorderPane active_cashier(){
        GridPane grid = new GridPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(grid);
        layout.setTop(menubar);
        Button button = new Button("Submit");


        Label label = new Label(" Set Cashier Active");
        ChoiceBox<Cashier> choice = new ChoiceBox();
        for(int x=0;x<worker.size();x++){
            if(worker.get(x).typeOfWorker.equals("Cashier"))choice.getItems().add((Cashier)worker.get(x));

        }


        button.setOnAction(e->{
            if(choice.getValue()!=null) {

                choice.getValue().setActive();
            }

            stage.setScene(primaryStage());

        });


        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }

    private BorderPane active_stocker(){
        GridPane grid = new GridPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(grid);
        layout.setTop(menubar);
        Button button = new Button("Submit");


        Label label = new Label(" Set Stocker Active:");
        ChoiceBox<Stocker> choice = new ChoiceBox();
        for(int x=0;x<worker.size();x++){
            if(worker.get(x).typeOfWorker.equals("Stocker"))choice.getItems().add((Stocker)worker.get(x));

        }


        button.setOnAction(e->{
            if(choice.getValue()!=null){

                choice.getValue().setActive();
            }

            stage.setScene(primaryStage());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }

    private BorderPane set_cashier_break(){
        GridPane grid = new GridPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(grid);
        layout.setTop(menubar);
        Button button = new Button("Submit");



        Label label = new Label(" Set Cashier On Break:");
        ChoiceBox<Cashier> choice = new ChoiceBox();
        for(int x=0;x<worker.size();x++){
            if(worker.get(x).typeOfWorker.equals("Cashier"))choice.getItems().add((Cashier)worker.get(x));

        }


        button.setOnAction(e->{
            if(choice.getValue()!=null){
                if(choice.getValue().getOnBreak())
                    choice.getValue().onBreak=false;
                else choice.getValue().onBreak=true;}

            stage.setScene(primaryStage());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }



    private BorderPane set_stocker_break(){
        GridPane grid = new GridPane();
        BorderPane layout = new BorderPane();
        layout.setCenter(grid);
        layout.setTop(menubar);
        Button button = new Button("Submit");



        Label label = new Label(" Set Stocker On Break:");
        ChoiceBox<Stocker> choice = new ChoiceBox();
        for(int x=0;x<worker.size();x++)if(worker.get(x).typeOfWorker.equals("Stocker"))choice.getItems().add((Stocker)worker.get(x));


        button.setOnAction(e->{
            if(choice.getValue()!=null){
                if(choice.getValue().getOnBreak())
                    choice.getValue().onBreak=false;
                else choice.getValue().onBreak=true;}

            stage.setScene(primaryStage());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return layout;
    }

    /*
    private GridPane check_task(String str){
        switch(str){
            case "Pay Order":return pay_order();
            case "Place Order":return place_order();
            case "Active Cashier":return active_cashier();
            case "Active Stocker": return active_stocker();
            case "Cashier on Break":return set_cashier_break();
            case "Stocker on Break":return set_stocker_break();
            //case "Start Thread":start_thread();break;
        }
        return new GridPane();
    }

    void start_thread(){
        Thread thread = new Thread(new TimeThread(stage));
        thread.start();

    }
    */



    private Scene task_menu(){
        BorderPane pane = new BorderPane();
        GridPane grid= new GridPane();
        pane.setTop(menubar);
        Button button = new Button("Submit");


        Label label = new Label("Select a task!");
        ChoiceBox<String> check = new ChoiceBox<>();
        check.getItems().add("Pay Order");
        check.getItems().add("Place Order");
        check.getItems().add("Active Cashier");
        check.getItems().add("Active Stocker");
        check.getItems().add("Cashier on Break");
        check.getItems().add("Stocker on Break");
        check.getItems().add("Start Thread");

        check.setValue("Pay Order");

        grid.add(label, 0,0);
        grid.add(check, 1,0);
        grid.add(button,2,0);




        button.setOnAction(e->pane.setCenter(check_task(check.getValue())));
        pane.setCenter(grid);
        Scene scene = new Scene(pane,stage.getHeight(),stage.getWidth());
        return scene;
    }

    private GridPane pay_order(){
        GridPane grid = new GridPane();
        if(shopper.orderList.isEmpty()){
            grid.add(new Label("No order created!"),0,0);
            return grid;
        }
        Button button = new Button("Submit");



        Label label = new Label("Pay Order:");
        ChoiceBox<Order> choice = new ChoiceBox();
        for(int x=0;x<shopper.orderList.size();x++)choice.getItems().add(shopper.orderList.get(x));


        button.setOnAction(e->{
            try {
                shopper.pay_order(choice.getValue());
            }catch(MoneyException ex){
                System.out.println(ex);
            }
            stage.setScene(task_menu());
        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return grid;
    }


    private GridPane place_order(){
        shopper.servingList.add(new Serving("Dog Shit"));
        GridPane grid = new GridPane();
        if(customer.isEmpty()){
            Label temp = new Label("No Customer Created");
            grid.add(temp,0,0);
            return grid;
        }else if(worker.isEmpty()){
            Label temp = new Label("No Worker Created");
            grid.add(temp,0,0);
            return grid;
        }


        Button button = new Button("Submit");




        Label wl= new Label("Worker");
        ChoiceBox<Worker> wc = new ChoiceBox<>();
        for(int x=0;x<worker.size();x++){

            if(worker.get(x).typeOfWorker!="Stocker" && !worker.get(x).getOnBreak()) {
                wc.getItems().add(worker.get(x));
                wc.setValue(worker.get(x));
            }
        }
        grid.add(wl,0,0);
        grid.add(wc,1,0);




        Label cl = new Label("Customer");
        ChoiceBox<Customer> cc = new ChoiceBox<>();
        for(int x=0;x<worker.size();x++){

                cc.getItems().add(customer.get(x));
        }


        grid.add(cl,0,1);
        grid.add(cc,1,1);




            Label sl = new Label("Serving");
            VBox items = new VBox();
            ArrayList<CheckBox> check = new ArrayList<>();
            for (int x = 0; x < IceCreamList.size(); x++) {
                //Color col= new Color(1,1,0,1);
                CheckBox cb = new CheckBox(IceCreamList.get(x).toString());
                cb.setUserData(IceCreamList.get(x));
                //cb.setTextFill(col);

                check.add(cb);
            }
                for (int x = 0; x < check.size(); x++) items.getChildren().add(check.get(x));

            grid.add(items, 1, 2);




        button.setOnAction(e->{
            if(wc.getValue()!=null || cc.getValue()!=null) {
                ArrayList<Serving> serv = new ArrayList<>();
                for (int x = 0; x < check.size(); x++) {
                    if (check.get(x).isSelected()) serv.add((Serving) check.get(x).getUserData());
                }

                shopper.orderList.add(new Order(cc.getValue(), wc.getValue(), serv, rand));
            }
            stage.setScene(task_menu());
        });



        grid.add(button,1 ,3);
        grid.setTranslateX(100);
        grid.setTranslateY(50);
        return grid;
    }
    private GridPane active_cashier(){
        GridPane grid = new GridPane();
        Button button = new Button("Submit");


        Label label = new Label(" Set Cashier Active");
        ChoiceBox<Cashier> choice = new ChoiceBox();
        for(int x=0;x<worker.size();x++){
            if(worker.get(x).typeOfWorker.equals("Cashier"))choice.getItems().add((Cashier)worker.get(x));

        }


        button.setOnAction(e->{
            if(choice.getValue()!=null) {

                choice.getValue().setActive();
            }

            stage.setScene(task_menu());

        });


        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return grid;
    }

    private GridPane active_stocker(){
        GridPane grid = new GridPane();
        Button button = new Button("Submit");


        Label label = new Label(" Set Stocker Active:");
        ChoiceBox<Stocker> choice = new ChoiceBox();
        for(int x=0;x<worker.size();x++){
            if(worker.get(x).typeOfWorker.equals("Stocker"))choice.getItems().add((Stocker)worker.get(x));

        }


        button.setOnAction(e->{
            if(choice.getValue()!=null){

                    choice.getValue().setActive();
            }

            stage.setScene(task_menu());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return grid;
    }

    private GridPane set_cashier_break(){
        GridPane grid = new GridPane();
        Button button = new Button("Submit");



        Label label = new Label(" Set Cashier On Break:");
        ChoiceBox<Cashier> choice = new ChoiceBox();
        for(int x=0;x<worker.size();x++){
            if(worker.get(x).typeOfWorker.equals("Cashier"))choice.getItems().add((Cashier)worker.get(x));

        }


        button.setOnAction(e->{
            if(choice.getValue()!=null){
                if(choice.getValue().getOnBreak())
                    choice.getValue().onBreak=false;
                else choice.getValue().onBreak=true;}

            stage.setScene(task_menu());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return grid;
    }



    private GridPane set_stocker_break(){
        GridPane grid = new GridPane();
        Button button = new Button("Submit");



        Label label = new Label(" Set Stocker On Break:");
        ChoiceBox<Stocker> choice = new ChoiceBox();
        for(int x=0;x<worker.size();x++)if(worker.get(x).typeOfWorker.equals("Stocker"))choice.getItems().add((Stocker)worker.get(x));


        button.setOnAction(e->{
            if(choice.getValue()!=null){
                if(choice.getValue().getOnBreak())
                    choice.getValue().onBreak=false;
                else choice.getValue().onBreak=true;}

            stage.setScene(task_menu());

        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return grid;
    }


    private GridPane check_task(String str){
        switch(str){
            case "Pay Order":return pay_order();
            case "Place Order":return place_order();
            case "Active Cashier":return active_cashier();
            case "Active Stocker": return active_stocker();
            case "Cashier on Break":return set_cashier_break();
            case "Stocker on Break":return set_stocker_break();
            case "Start Thread":start_thread();break;
        }
        return new GridPane();
    }

    void start_thread(){
        Thread thread = new Thread(new TimeThread(stage));
        thread.start();

    }



}
