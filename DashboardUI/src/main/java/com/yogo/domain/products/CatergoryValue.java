package com.yogo.domain.products;


import com.yogo.util.DoubleStats;

public class CatergoryValue {
    private String categoryName;
    private DoubleStats stats;

    public CatergoryValue() {
    }

    public CatergoryValue(String categoryName, DoubleStats stats) {
        this.categoryName = categoryName;
        this.stats = stats;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public DoubleStats getStats() {
        return stats;
    }

    public void setStats(DoubleStats stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "CatergoryValue{" +
                "categoryName='" + categoryName + '\'' +
                ", stats=" + stats +
                '}';
    }
}
