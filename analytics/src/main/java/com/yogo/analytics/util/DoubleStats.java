package com.yogo.analytics.util;

import java.util.DoubleSummaryStatistics;

public class DoubleStats {
    private double average;
    private double max;
    private double min;
    private long count;
    private double sum;

    public DoubleStats(DoubleSummaryStatistics doubleSummaryStatistics) {
        this.average=doubleSummaryStatistics.getAverage();
        this.count=doubleSummaryStatistics.getCount();
        this.max=doubleSummaryStatistics.getMax();
        this.min=doubleSummaryStatistics.getMin();
        this.sum=doubleSummaryStatistics.getSum();
    }

    public DoubleStats() {
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
