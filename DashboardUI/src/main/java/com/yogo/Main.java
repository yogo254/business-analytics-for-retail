package com.yogo;

import com.yogo.domain.products.ResentProductItem;
import com.yogo.service.analytics.product.ProductAnalytics;
import com.yogo.service.analytics.product.ProductUI;
import com.yogo.service.ui.dashboard.DashboardUI;
import com.yogo.util.ExecutorPool;
import javafx.application.Application;
import javafx.application.Platform;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
@SpringBootApplication

public class Main extends Application {
    @Autowired
    private ProductAnalytics productAnalytics;
    private static final String basepath="src/main/resources/fxml";
    private ExecutorService executorService= ExecutorPool.getExecutor();
    private static ConfigurableApplicationContext context;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Service<ConfigurableApplicationContext>bootContext=createBootContext();
        bootContext.setOnSucceeded(e->{
            context=bootContext.getValue();
            HBox box= (HBox) DashboardUI.getDashboard().lookup("#mainPane");
            box.getChildren().remove(1);
            box.getChildren().add(ProductUI.getProductScene());
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
        descriptive.getChildren().add(new TreeItem<>("Products"));
        TreeItem<String>predictive=new TreeItem<>("Predictive Analytics");
        predictive.getChildren().add(new TreeItem<>("Products"));

        analytics.getChildren().addAll(descriptive,predictive);



        return scene;

    }

    @Override
    public void stop() throws Exception {
        if(!context.equals(null))
        context.stop();
        ExecutorPool.getScheduledExecutorService().shutdown();
        ExecutorPool.getExecutor().shutdown();
        Platform.exit();
    }


}

