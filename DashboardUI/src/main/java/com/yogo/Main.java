package com.yogo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yogo.domain.customer.CustomerRow;
import com.yogo.domain.products.ResentProductItem;
import com.yogo.service.analytics.customer.CustomerUI;
import com.yogo.service.analytics.predictive.SalesForeCastUI;
import com.yogo.service.analytics.product.ProductUI;
import com.yogo.service.ui.dashboard.DashboardUI;
import com.yogo.util.ExecutorPool;
import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
@SpringBootApplication

public class Main extends Application {

    private static final String basepath="src/main/resources/fxml";
    private ExecutorService executorService= ExecutorPool.getExecutor();
    private static ConfigurableApplicationContext context;
   private Service<ConfigurableApplicationContext>bootContext;

    @Override
    public void start(Stage primaryStage) throws Exception{
        bootContext=createBootContext();
        bootContext.setOnSucceeded(e->{
            context=bootContext.getValue();
            primaryStage.setScene(DashboardUI.getDashboard());

        });

        primaryStage.setTitle("Analytics Dashboard");
        Path path= Paths.get(basepath+"/main.fxml");
        Parent parent=null;
        try {
            parent = FXMLLoader.load(path.toUri().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene=new Scene(parent,600,400);
        ProgressIndicator indicator= (ProgressIndicator) scene.lookup("#indicator");
        indicator.progressProperty().bind(bootContext.progressProperty());



        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

        primaryStage.show();


    }
    private static VBox createSalesForecastScene(){
        Path path=Paths.get(basepath+"/predictive/salesforecast.fxml");
        Parent parent = null;
        try {
            parent = FXMLLoader.load(path.toUri().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox box= (VBox) parent.lookup("#dashboard");
        VBox hourDay= (VBox) box.lookup("#hourday");
        DatePicker datePicker= (DatePicker) hourDay.lookup("#datePicker");


        datePicker.setShowWeekNumbers(true);
        datePicker.valueProperty()
                .addListener((observableValue, oldVal, newVal) -> {
                    SalesForeCastUI.updateDayHour(newVal);

                });

        NumberAxis hourDayY=new NumberAxis();
        hourDayY.setAutoRanging(true);
        hourDayY.setLowerBound(100);

        hourDayY.setLabel("Mean Sales");
        CategoryAxis hourDayX=new CategoryAxis();

        hourDayX.setLabel("Hour of Day");
        StackedAreaChart hourDayChart=new StackedAreaChart(hourDayX,hourDayY);
        hourDayChart.setAnimated(true);
        hourDayChart.setTitle("Sales Forecast By Hour and Day of Week");
        hourDayChart.setLegendSide(Side.BOTTOM);
        hourDayChart.setLegendVisible(true);
        hourDayChart.setMaxSize(950,230);
        hourDayChart.getData().add(SalesForeCastUI.getHourDay());

        hourDay.getChildren().add(1,hourDayChart);

        VBox dayMonthWeek= (VBox) box.lookup("#dayMonthWeek");
        DatePicker datePicker1= (DatePicker) box.lookup("#datePicker1");
        datePicker1.valueProperty()
                .addListener((observableValue, oldVal, newVal) ->SalesForeCastUI.updatedayMonthWeek(newVal) );
        NumberAxis dayMonthWeekY=new NumberAxis();
        dayMonthWeekY.setAutoRanging(true);
        dayMonthWeekY.setLabel("Mean Sales");
        CategoryAxis categoryAxis=new CategoryAxis();
        categoryAxis.setLabel("Day of Week");

        BarChart<String,Number> barChart=new BarChart<>(categoryAxis,dayMonthWeekY);
        barChart.getData().add(SalesForeCastUI.getDaymonthWeek());
        barChart.setAnimated(true);



        dayMonthWeek.getChildren().add(1,barChart);

        VBox monthMonthWeek= (VBox) box.lookup("#monthMonthWeek");
        DatePicker datePicker2= (DatePicker) box.lookup("#datePicker2");
        datePicker2.valueProperty()
                .addListener((observableValue, oldVal,newVal) ->SalesForeCastUI.updatemonthMonthday(newVal) );
        CategoryAxis categoryAxis1=new CategoryAxis();
        categoryAxis1.setLabel("Day of Month");
        StackedAreaChart<String,Number>stackedAreaChart=new StackedAreaChart<>(categoryAxis1,hourDayY);
        stackedAreaChart.setAnimated(true);
        stackedAreaChart.getData().add(SalesForeCastUI.getMonthMonthWeek());
        monthMonthWeek.getChildren().add(1,stackedAreaChart);





        return box;

    }
    private static VBox createCustomerScene(){
        Path path=Paths.get(basepath+"/customer/customer.fxml");
        Parent parent = null;
        try {
            parent = FXMLLoader.load(path.toUri().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox box= (VBox) parent.lookup("#dashboard");
        VBox topTablePane= (VBox) box.lookup("#topTablePane");
        VBox resentTablePane= (VBox) box.lookup("#resentTablePane");

        TableView topCustomers=createCustomerTable();
        topCustomers.setItems(CustomerUI.getTopCustomers());
        TableView resentCustomers=createCustomerTable();
        resentCustomers.setItems(CustomerUI.getResentCustomers());
        topTablePane.getChildren().add(topCustomers);

        resentTablePane.getChildren().add(resentCustomers);

        HBox areaChartPane= (HBox) box.lookup("#areaChartPane");
        NumberAxis numberAxis=new NumberAxis();
        numberAxis.setAutoRanging(true);
        numberAxis.setLabel("Sales value");
        NumberAxis time=new NumberAxis(0,60,1);
        time.setMinorTickCount(1000);
        time.setLabel("TimeStamp");

        StackedAreaChart<Number,Number>stackedAreaChart=new StackedAreaChart<>(time,numberAxis);
        stackedAreaChart.setAnimated(true);
        stackedAreaChart.setPrefSize(1000,250);
        stackedAreaChart.setData(CustomerUI.getChartData());
        areaChartPane.getChildren().add(stackedAreaChart);





        return box;
    }

    private static TableView createCustomerTable() {
        TableView topCustomers=new TableView();
        TableColumn customerId=new TableColumn("Customer ID");
        customerId.setCellValueFactory(new PropertyValueFactory<CustomerRow,String>("customerId"));
        TableColumn avarage=new TableColumn("Average");
        avarage.setCellValueFactory(new PropertyValueFactory<CustomerRow,Double>("average"));
        TableColumn sum=new TableColumn("Total Amount");
        sum.setCellValueFactory(new PropertyValueFactory<CustomerRow,Double>("sum"));
        TableColumn min=new TableColumn("Minimum Amount");
        min.setCellValueFactory(new PropertyValueFactory<CustomerRow,Double>("min"));
        TableColumn max=new TableColumn("Maximum Amount");
        max.setCellValueFactory(new PropertyValueFactory<CustomerRow,Double>("max"));
        TableColumn count =new TableColumn("Item Count");
        count.setCellValueFactory(new PropertyValueFactory<CustomerRow,Long>("count"));

        topCustomers.getColumns().addAll(customerId,count,min,max,avarage,sum);

        return topCustomers;
    }

    private  static VBox createProductPane(){
        Path path = Paths.get(basepath + "/product/product.fxml");
        Parent parent = null;
        try {
            parent = FXMLLoader.load(path.toUri().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        VBox box= (VBox) parent.lookup("#dashboard");
        PieChart pieChart = (PieChart) box.lookup("#topPieChart");
        pieChart.setTitle("Top 5 product category");
        pieChart.setLabelLineLength(10);
        pieChart.setAnimated(true);
        pieChart.setVisible(true);
        pieChart.setData(ProductUI.getPieChartSeries());


        StackedBarChart stackedBarChart= (StackedBarChart) box.lookup("#stackBarGraph");
        stackedBarChart.setAnimated(true);
        CategoryAxis categoryAxis= (CategoryAxis) stackedBarChart.lookup("#stackCategory");
        categoryAxis.setLabel("Time difference (10000 Millis)");
        categoryAxis.getCategories().addAll("3","2","1","0");
        NumberAxis numberAxis= (NumberAxis) stackedBarChart.lookup("#stackNumber");
        numberAxis.setAutoRanging(true);
        numberAxis.setLabel("Sales price (Dollars)");
        stackedBarChart.setData(ProductUI.getXYChartData());

        VBox tablePane= (VBox) box.lookup("#tablePane");
        TableView tableView=new TableView();
        tableView.setEditable(true);
        TableColumn productId=new TableColumn("Product_Id");
        productId.setCellValueFactory(new PropertyValueFactory<ResentProductItem,String>("productId"));
        TableColumn sellerId=new TableColumn("Seller_ID");
        sellerId.setCellValueFactory(new PropertyValueFactory<ResentProductItem,String>("sellerId"));
        TableColumn price=new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<ResentProductItem,Double>("price"));
        TableColumn time=new TableColumn("Timestamp");
        time.setCellValueFactory(new PropertyValueFactory<ResentProductItem,String>("timeStamp"));
        tableView.getColumns().addAll(productId,sellerId,price,time);
        tableView.setItems(ProductUI.getResentItems());
        tablePane.getChildren().add(tableView);

        NumberAxis yaxis=new NumberAxis();
        yaxis.setAutoRanging(true);
        NumberAxis xAxis=new NumberAxis(1,60,5);

        AreaChart<Number,Number>revenue=new AreaChart<>(xAxis,yaxis);
        revenue.setTitle("Sales KPI");
        revenue.setAnimated(true);
        ProductUI.getRevenue().setName("Total Revenue");
        ProductUI.getSalesAvg().setName("Average Revenue");
        revenue.setLegendVisible(true);
        revenue.setLegendSide(Side.BOTTOM);
        revenue.getData().add(ProductUI.getRevenue());


        NumberAxis salesyaxis=new NumberAxis();
        yaxis.setAutoRanging(true);
        NumberAxis salesxAxis=new NumberAxis(1,60,5);


        AreaChart salesCount=new AreaChart(salesxAxis,salesyaxis);
        salesCount.setAnimated(true);
        salesCount.setTitle("Sales Count");
        salesCount.getData().add(ProductUI.getSalesCount());


        HBox hBox= (HBox) box.lookup("#kpiPane");

        hBox.getChildren().addAll(revenue,salesCount);






        return box;
    }

    private Service<ConfigurableApplicationContext> createBootContext() {

        Service<ConfigurableApplicationContext> bootContext=new Service<ConfigurableApplicationContext>() {
            @Override
            protected Task<ConfigurableApplicationContext> createTask() {

                return new Task<ConfigurableApplicationContext>() {
                    @Override
                    protected ConfigurableApplicationContext call() throws Exception {
                        return SpringApplication.run(Main.class);

                    }
                };
            }
        };

        bootContext.setExecutor(executorService);
        bootContext.setOnFailed(e-> {System.out.println("Spark Context Error");

            bootContext.restart();
        });
        bootContext.start();
        return bootContext;
    }
    
    public static void main(final String[] args) {
        SalesForeCastUI.setSaleForecastScene(createSalesForecastScene());
        CustomerUI.setCustomerScene(createCustomerScene());
        ProductUI.setProductScene(createProductPane());
        DashboardUI.setDashboard(createDashboardPane());
        launch(args);
    }

    private static Scene createDashboardPane() {
        Path path = Paths.get(basepath + "/dashboard.fxml");
        Parent parent = null;
        try {
            parent = FXMLLoader.load(path.toUri().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene=new Scene(parent,1200,800);

        TreeView<String>treeView= (TreeView<String>) scene.lookup("#tree");
        TreeItem<String>analytics=new TreeItem<>("Analytics");
        treeView.setRoot(analytics);

        TreeItem<String>descriptive=new TreeItem<>("Descriptive Analytics");
        TreeItem<String>des_Product=new TreeItem<>("Products");
        TreeItem<String>desc_customer=new TreeItem<>("Customers");

        descriptive.getChildren().addAll(des_Product,desc_customer);


        TreeItem<String>predictive=new TreeItem<>("Predictive Analytics");
        TreeItem<String>salesForecast=new TreeItem<>("Sales Forecast");
        predictive.getChildren().add(salesForecast);

        analytics.getChildren().addAll(descriptive,predictive);

        treeView.getSelectionModel().selectedItemProperty()
                .addListener((observableValue, oldVal, newVal) -> {
                    if(newVal.equals(des_Product)) {
                        DashboardUI.switchDashboard(ProductUI.getProductScene());
                        ProductUI.setCurrentScene(true);
                        CustomerUI.setCurrentScene(false);
                    }
                    else if (newVal.equals(desc_customer)) {
                        DashboardUI.switchDashboard(CustomerUI.getCustomerScene());
                        CustomerUI.setCurrentScene(true);
                        ProductUI.setCurrentScene(false);
                    }
                    else if(newVal.equals(salesForecast))
                        DashboardUI.switchDashboard(SalesForeCastUI.getSaleForecastScene());

                });



        return scene;

    }
    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Override
    public void stop() throws Exception {
        if(!context.equals(null)) {
            context.stop();
            context.close();
        }
        bootContext.cancel();
        ExecutorPool.getScheduledExecutorService().shutdown();
        ExecutorPool.getExecutor().shutdown();
        super.stop();
    }


}

