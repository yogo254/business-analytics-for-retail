package com.yogo.util;

import java.util.LongSummaryStatistics;

public class LongStatistics {

    private double average;
    private long max;
    private long count;
    private long min;
    private long sum;

    public LongStatistics(LongSummaryStatistics longSummaryStatistics) {
     this.average=longSummaryStatistics.getAverage();
     this.count=longSummaryStatistics.getCount();
     this.max=longSummaryStatistics.getMax();
     this.min=longSummaryStatistics.getMin();
     this.sum=longSummaryStatistics.getSum();

    }

    public LongStatistics() {
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
}
