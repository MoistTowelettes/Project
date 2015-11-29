package homework3;

import java.io.File;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.util.Random;

public class MenuManagerGUI extends Application {

    private Shop shopper;
    ArrayList<Customer> customer;
    ArrayList <Worker> worker;
    ArrayList <IceCreamMain> IceCreamList;
    //added
    private static Stage stage;
    private static MenuBar menubar;
    //for order num
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
        BorderPane layout;

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
        updateWorker.setOnAction(e->stage.setScene(update_Menu(/*stage.getHeight(),stage.getWidth()*/)));

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
        //added
        this.stage=primaryStage;
        /*
        shopper.orderList.add(new Order());
        shopper.orderList.get(0).c=customer.get(0);
        shopper.orderList.get(0).w=worker.get(0);
        shopper.orderList.add(new Order());
        shopper.orderList.get(1).c=customer.get(2);
        shopper.orderList.get(1).w=worker.get(2);
        */
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

    private Scene update_Menu(/*Double height,Double width*/){
        Label option= new Label("Which would you like to update!\n");
        BorderPane layout=new BorderPane();
        GridPane grid = new GridPane();
        DropShadow ds=new DropShadow();
        ds.setOffsetX(2.0);
        ds.setOffsetY(3.0);
        option.setEffect(ds);
        ChoiceBox<String> choice = new ChoiceBox<>();
        choice.getItems().addAll("Ice Cream","Worker","Customer","Order","Serving");
        choice.setValue("Ice Cream");
        Button button = new Button("Submit");
        button.setOnAction(e->{
            option.setVisible(false);
            button.setVisible(false);
            choice.setVisible(false);
            layout.setCenter(submit(choice.getValue()));


        });
        grid.add(option,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);
        grid.setVgap(10.0);
        grid.setHgap(10.0);
        grid.setVgap(10.0);



        layout.setTop(menubar);
        layout.setCenter(grid);
        Scene scene = new Scene(layout,393.0,242.0);
        //scene.getStylesheets().add("/homework3/updatePage.css");
        return scene;
    }

    private GridPane build_ice(){
        Label label1=new Label("Which Ice Cream:");
        Label dlabel= new Label("Description:");
        Label plabel= new Label("Price:");
        Button button=new Button("Submit");
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

                stage.setScene(update_Menu(/*stage.getHeight(),stage.getWidth()*/));
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

        return pane;

    }

    private GridPane build_worker(){
        Label label1=new Label("Which worker:");
        Label clabel= new Label("Customers Served:");
        Label sslabel= new Label("Scoops Served:");
        Label mtlabel= new Label("Money Taken:");
        Button button=new Button("Submit");
        GridPane pane = new GridPane();
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
                stage.setScene(update_Menu());
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
        return pane;
    }

    private GridPane build_customer(){
        GridPane pane=new GridPane();
        Label label1=new Label("Which worker:");
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
                stage.setScene(update_Menu());
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
        return pane;
    }
    GridPane build_order(){
        GridPane box = new GridPane();

        if(shopper.orderList.size()!=0){



            //choicebox
            Label ctext = new Label("WHich order:");
            ChoiceBox<Order> choice = new ChoiceBox<>();
            ChoiceBox<Worker>wchoice = new ChoiceBox<>();
            for(int x=0 ; x<shopper.orderList.size(); x++) choice.getItems().add(shopper.orderList.get(x));
            box.add(ctext, 0, 0);
            box.add(choice, 1, 0);
            choice.setValue(shopper.orderList.get(0));


            //update worker
            Label wlabel = new Label("Worker:");
            for(int x=0 ; x<worker.size(); x++) wchoice.getItems().add(worker.get(x));
            wchoice.setValue(choice.getValue().w);
            box.add(wlabel, 0, 1);
            box.add(wchoice,1,1);

            //customer
            ChoiceBox<Customer> cchoice = new ChoiceBox<>();
            Label clabel = new Label("Customer:");
            for(int x=0 ; x<customer.size(); x++) cchoice.getItems().add(customer.get(x));
            cchoice.setValue(choice.getValue().c);
            box.add(clabel, 0, 2);
            box.add(cchoice,1,2);


            //serving
            VBox items = new VBox();
            ArrayList<CheckBox> check = new ArrayList<>();
            for(int x=0;x<shopper.servingList.size();x++){
                Color col= new Color(1,1,0,1);
                CheckBox cb = new CheckBox(shopper.servingList.get(x).toString());
                cb.setUserData(shopper.servingList.get(x));
                cb.setTextFill(col);
                for(int y=0;y<choice.getValue().servings.size();y++){
                    if(cb.getUserData().equals(choice.getValue().servings.get(y)))
                        cb.setSelected(true);
                }
                check.add(cb);
            }
            for(int x=0;x<check.size();x++)items.getChildren().add(check.get(x));
            box.add(items, 1, 3);
            choice.setOnAction(e->{
                cchoice.setValue(choice.getValue().c);
                wchoice.setValue(choice.getValue().w);
                for(int x=0;x<check.size();x++){
                    for(int y=0;y<choice.getValue().servings.size();y++){
                        if(check.get(x).equals(choice.getValue().servings.get(y)))
                            check.get(x).setSelected(true);
                        else check.get(x).setSelected(false);
                    }
                }});



            //button
            Button button = new Button("Submit");
            box.add(button, 1,4);
            button.setOnAction(e->{
                Order it=choice.getValue();
                it.w=wchoice.getValue();
                it.c=cchoice.getValue();
                for(int x = 0;x<check.size();x++){
                    if(check.get(x).isSelected()){
                        choice.getValue().servings.add((Serving)check.get(x).getUserData());
                    }
                }
                stage.setScene(update_Menu());
            });
            box.setVgap(5);
            box.setHgap(10);
            box.setTranslateX(100);
            box.setTranslateY(50);

        }else {Label label = new Label("NO Order placed!");

            box.add(label, 0,0);
        }
        return box;
    }

    private GridPane submit(String choice){

        switch(choice){
            case "Ice Cream":return build_ice();
            case "Worker":return build_worker();
            case "Customer":return build_customer();
            case "Order": return build_order();
        }

        return new GridPane();
    }
    /*
    private VBox vbox_maker(String local,String lab){
        VBox box= new VBox();
        Label label= new Label(lab);
        File file= new File(local);
        Image img = new Image(file.toString(),false);

        ImageView view = new ImageView();
        view.setFitWidth(75);
        view.setFitHeight(75);
        view.setImage(img);
        view.setOnDragDetected(e->{
            Dragboard db=view.startDragAndDrop(TransferMode.ANY);
            ClipboardContent clip = new ClipboardContent();
            view.setVisible(false);

            clip.putImage(view.getImage());
            db.setContent(clip);
        });
        view.setOnDragDone(e->view.setVisible(true));
        box.getChildren().addAll(view,label);
        box.setBorder(Border.EMPTY);
        return box;
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



        });



        grid.add(label,0,0);
        grid.add(choice, 1, 0);
        grid.add(button,2,0);


        return grid;
    }


    private GridPane place_order(){
        GridPane grid = new GridPane();
        if(customer.isEmpty()){
            Label temp = new Label("No Customer Created");
            grid.add(temp,0,0);
            return grid;
        }else if(worker.isEmpty()){
            Label temp = new Label("No Worker Created");
            grid.add(temp,0,0);
            return grid;
        }else if(shopper.servingList.isEmpty()) {
            Label temp = new Label("No Serving Created");
            grid.add(temp, 0, 0);
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
            for (int x = 0; x < shopper.servingList.size(); x++) {
                //Color col= new Color(1,1,0,1);
                CheckBox cb = new CheckBox(shopper.servingList.get(x).toString());
                cb.setUserData(shopper.servingList.get(x));
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
        }
        return new GridPane();
    }





}
