package com.yogo.domain.customer;

import com.yogo.util.DoubleStats;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerRow {
    private final SimpleStringProperty customerId;
    private final SimpleDoubleProperty average;
    private final SimpleDoubleProperty max;
    private final SimpleDoubleProperty min;
    private final SimpleDoubleProperty sum;
    private final SimpleLongProperty count;


    public CustomerRow(String id, DoubleStats stats){
        this.average=new SimpleDoubleProperty(stats.getAverage());
        this.count=new SimpleLongProperty(stats.getCount());
        this.max=new SimpleDoubleProperty(stats.getMax());
        this.min=new SimpleDoubleProperty(stats.getMin());
        this.customerId=new SimpleStringProperty(id);
        this.sum=new SimpleDoubleProperty(stats.getSum());
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public SimpleStringProperty customerIdProperty() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId.set(customerId);
    }

    public double getAverage() {
        return average.get();
    }

    public SimpleDoubleProperty averageProperty() {
        return average;
    }

    public void setAverage(double average) {
        this.average.set(average);
    }

    public double getMax() {
        return max.get();
    }

    public SimpleDoubleProperty maxProperty() {
        return max;
    }

    public void setMax(double max) {
        this.max.set(max);
    }

    public double getMin() {
        return min.get();
    }

    public SimpleDoubleProperty minProperty() {
        return min;
    }

    public void setMin(double min) {
        this.min.set(min);
    }

    public double getSum() {
        return sum.get();
    }

    public SimpleDoubleProperty sumProperty() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum.set(sum);
    }

    public long getCount() {
        return count.get();
    }

    public SimpleLongProperty countProperty() {
        return count;
    }

    public void setCount(long count) {
        this.count.set(count);
    }
}
