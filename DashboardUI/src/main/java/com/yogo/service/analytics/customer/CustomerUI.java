package com.yogo.service.analytics.customer;

import com.yogo.domain.customer.CustomerCounts;
import com.yogo.domain.customer.CustomerRow;
import com.yogo.domain.customer.CustomerStats;
import com.yogo.domain.customer.CustomerValue;
import com.yogo.util.DoubleStats;
import com.yogo.util.ExecutorPool;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CustomerUI {
    private static boolean currentScene;
    private static final ObservableList<XYChart.Series<Number,Number>>chartData=FXCollections.observableArrayList();
    private static VBox customerScene;
    private  static final ObservableList<CustomerRow> topCustomers= FXCollections.observableArrayList();
    private static final ObservableList<CustomerRow>resentCustomers=FXCollections.observableArrayList();

    private static final XYChart.Series<Number,Number>meanSeries=new XYChart.Series<>();


    private static final BlockingQueue<List<CustomerValue>>topCust=new LinkedBlockingQueue<>();
    private static final BlockingQueue<CustomerCounts>resentCust=new LinkedBlockingQueue<>();
    private static final BlockingQueue<CustomerStats>statistics=new LinkedBlockingQueue<>();



    private static void setTopCustomers() {
        Runnable runnable = () -> {
            if (isCurrentScene() && !topCust.isEmpty()) {
                List<CustomerRow> customerRows = topCust.poll().stream()
                        .map(e -> new CustomerRow(e.getCustomerId(), e.getStats()))
                        .collect(Collectors.toList());
                Platform.runLater(() -> {
                    topCustomers.clear();
                    topCustomers.addAll(customerRows);
                });
            }

        };
        ExecutorPool.getScheduledExecutorService()
                .scheduleAtFixedRate(runnable,10000,1000, TimeUnit.MILLISECONDS);
    }

    private static void setResentCustomers(){
        Runnable runnable=()-> {
            if (isCurrentScene() && !resentCust.isEmpty()) {
                CustomerCounts customerCounts = resentCust.poll();
                List<String> customerIds = customerCounts.getResentCustomersIds();
                DoubleStats stats = customerCounts.getStatistics();

                List<CustomerRow> customerRows =
                        customerIds.stream()
                                .map(e -> new CustomerRow(e, stats))
                                .collect(Collectors.toList());
                Platform.runLater(() -> {
                    resentCustomers.clear();
                    resentCustomers.addAll(customerRows);
                });
            }

        };

        ExecutorPool.getScheduledExecutorService()
                .scheduleAtFixedRate(runnable,9000,700,TimeUnit.MILLISECONDS);


    }
    private static void setStasts(){
        Runnable runnable=()-> {
            if (isCurrentScene() && !statistics.isEmpty()) {
                CustomerStats stats=statistics.poll();

                LocalTime timestamp= stats.getTimestamp();
                int sec = timestamp.getSecond();
                int nano = timestamp.getNano();
                double time = Double.valueOf("" + sec +"."+ nano);
                Platform.runLater(() -> {

                    meanSeries.getData().add(new XYChart.Data<>(time, stats.getStats().getAverage()));
                });

            }

        };
        ExecutorPool.getScheduledExecutorService()
                .scheduleAtFixedRate(runnable ,9000,700,TimeUnit.MILLISECONDS);

    }

    public static boolean isCurrentScene() {
        return currentScene;
    }

    public static void setCurrentScene(boolean currentScene) {
        CustomerUI.currentScene = currentScene;
    }

    public static BlockingQueue<List<CustomerValue>> getTopCust() {
        return topCust;
    }

    public static BlockingQueue<CustomerCounts> getResentCust() {
        return resentCust;
    }

    public static BlockingQueue<CustomerStats> getStatistics() {
        return statistics;
    }

    public static VBox getCustomerScene() {
        return customerScene;
    }

    public static ObservableList<CustomerRow> getResentCustomers() {
        return resentCustomers;
    }

    public static void setCustomerScene(VBox customerScene) {
        CustomerUI.customerScene = customerScene;
        CustomerUI.setResentCustomers();
        CustomerUI.setTopCustomers();
        CustomerUI.setStasts();
        meanSeries.setName("Mean Sales");
        chartData.add(meanSeries);
    }
    public static ObservableList<CustomerRow> getTopCustomers() {
        return topCustomers;
    }

    public static ObservableList<XYChart.Series<Number,Number>> getChartData() {
        return chartData;
    }
}
