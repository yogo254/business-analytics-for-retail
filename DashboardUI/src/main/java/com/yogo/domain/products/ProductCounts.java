package com.yogo.domain.products;



import com.yogo.util.LongStatistics;

import java.util.List;
import java.util.LongSummaryStatistics;

public class ProductCounts {
    private List<OrderItem> resentItems;
    private LongStatistics statistics;

    public ProductCounts() {
    }


    public ProductCounts(List<OrderItem> resentItems, LongSummaryStatistics statistics) {
        this.resentItems = resentItems;
        this.statistics = new LongStatistics(statistics);
    }

    public List<OrderItem> getResentItems() {
        return resentItems;
    }

    public void setResentItems(List<OrderItem> resentItems) {
        this.resentItems = resentItems;
    }

    public LongStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(LongStatistics statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "ProductCounts{" +
                "resentItems=" + resentItems +
                ", statistics=" + statistics +
                '}';
    }
}
