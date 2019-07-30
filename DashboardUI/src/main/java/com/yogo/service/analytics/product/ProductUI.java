package com.yogo.service.analytics.product;

import com.yogo.domain.products.CatergoryValue;
import com.yogo.domain.products.ProductCounts;
import com.yogo.domain.products.ResentProductItem;
import com.yogo.util.DoubleStats;
import com.yogo.util.ExecutorPool;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ProductUI {

    private static VBox productScene;


    private static BlockingQueue<List<CatergoryValue>> topCategories=new LinkedBlockingQueue<>();
    private static BlockingQueue<ProductCounts>productCounts=new LinkedBlockingQueue<>();
    private static BlockingQueue<DoubleStats>doubleStats=new LinkedBlockingQueue<>();

    private static final ObservableList<PieChart.Data> pieChartSeries =FXCollections.observableArrayList();
    private static final ObservableList<XYChart.Series>XYChartData=FXCollections.observableArrayList();
    private static final ObservableList<ResentProductItem>resentItems=FXCollections.observableArrayList();
    private static final XYChart.Series<Number,Number>revenue=new XYChart.Series<>();
    private static final XYChart.Series<Number,Number>salesCount=new XYChart.Series<>();
    private static final XYChart.Series<Number,Number>salesAvg=new XYChart.Series<>();




    private static void createService() {
        Runnable runnable=()->{
            if (!topCategories.isEmpty()) {
                List<CatergoryValue> categoryValues = topCategories.poll();
                Platform.runLater(() -> {
                    pieChartSeries.clear();
                    categoryValues.stream()
                            .forEach(c -> pieChartSeries.add(new PieChart.Data(c.getCategoryName(), c.getStats().getSum())));
                });
                List<XYChart.Data>data=categoryValues.stream()
                        .limit(3)
                        .map(e->new XYChart.Data<>(e.getCategoryName(),e.getStats().getSum()))
                        .collect(Collectors.toList());
                Platform.runLater(()->{
                    if(XYChartData.size()>3){
                        XYChartData.remove(0);
                        XYChart.Series series=new XYChart.Series(FXCollections.observableList(data));

                        XYChartData.add(series);
                    }else
                    XYChartData.add(new XYChart.Series(FXCollections.observableList(data)));

                });
            }

        };

        ExecutorPool.getScheduledExecutorService().scheduleAtFixedRate(runnable,10000,1000, TimeUnit.MILLISECONDS);
    }
    public static void createService2(){
        Runnable runnable=()->{
          if(!productCounts.isEmpty()){
              ProductCounts counts=productCounts.poll();
                      List<ResentProductItem> items=counts.getResentItems()
                      .stream()
                      .map(e-> new ResentProductItem(e.getProductId(),e.getSellerId(),e.getPrice(),LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)))
                      .collect(Collectors.toList());

                      Platform.runLater(()->{
                          if(resentItems.size()>30)
                              resentItems.clear();
                          resentItems.addAll(items);
                      });


          }

        };
        ExecutorPool.getScheduledExecutorService().scheduleAtFixedRate(runnable,10000,30000, TimeUnit.MILLISECONDS);

    }
    private static void createService3(){
        Runnable runnable=()->{
            if(!doubleStats.isEmpty()){
                int sec=LocalTime.now().getSecond();
                DoubleStats summary=doubleStats.poll();
                Platform.runLater(()->{
                    revenue.getData().add(new XYChart.Data<>(sec,summary.getSum()));
                    salesCount.getData().add(new XYChart.Data<>(sec,summary.getCount()));
                    salesAvg.getData().add(new XYChart.Data<>(sec,summary.getAverage()));
                });


            }


        };
        ExecutorPool.getScheduledExecutorService().scheduleAtFixedRate(runnable,60,15,TimeUnit.SECONDS);
    }

    public static XYChart.Series<Number, Number> getSalesAvg() {
        return salesAvg;
    }

    public static XYChart.Series<Number, Number> getSalesCount() {
        return salesCount;
    }

    public static XYChart.Series<Number, Number> getRevenue() {
        return revenue;
    }

    public static ObservableList<XYChart.Series> getXYChartData() {
        return XYChartData;
    }

    public static ObservableList<PieChart.Data> getPieChartSeries() {
        return pieChartSeries;
    }

    public static void setTopCategories(BlockingQueue<List<CatergoryValue>> topCategories) {
        ProductUI.topCategories = topCategories;

    }

    public static void setProductScene(VBox productScene) {
        ProductUI.productScene = productScene;
        ProductUI.createService();
        ProductUI.createService2();
        ProductUI.createService3();
    }
    public static VBox getProductScene() {
        return productScene;
    }
    public static BlockingQueue<List<CatergoryValue>> getTopCategories() {
        return topCategories;
    }

    public static BlockingQueue<ProductCounts> getProductCounts() {
        return productCounts;
    }

    public static void setProductCounts(BlockingQueue<ProductCounts> productCounts) {
        ProductUI.productCounts = productCounts;
    }

    public static BlockingQueue<DoubleStats> getDoubleStats() {
        return doubleStats;
    }

    public static void setDoubleStats(BlockingQueue<DoubleStats> doubleStats) {
        ProductUI.doubleStats = doubleStats;
    }

    public static ObservableList<ResentProductItem> getResentItems() {
        return resentItems;
    }

}
