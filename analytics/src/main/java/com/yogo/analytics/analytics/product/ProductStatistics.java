package com.yogo.analytics.analytics.product;

import com.yogo.analytics.util.DoubleStats;

import java.util.List;

public class ProductStatistics {
    private ProductCounts productCounts;
  private DoubleStats statistics;
  private List<ProductValue> resentProductValues;
  private List<ProductValue>topProducts;
  private List<CatergoryValue>topCartegories;

    public ProductStatistics() {
    }


    public ProductStatistics(ProductCounts productCounts, DoubleStats statistics, List<ProductValue> resentProductValues, List<ProductValue> topProducts, List<CatergoryValue> topCartegories) {
        this.productCounts = productCounts;
        this.statistics = statistics;
        this.resentProductValues = resentProductValues;
        this.topProducts = topProducts;
        this.topCartegories = topCartegories;
    }



    public List<CatergoryValue> getTopCartegories() {
        return topCartegories;
    }

    public void setTopCartegories(List<CatergoryValue> topCartegories) {
        this.topCartegories = topCartegories;
    }

    public List<ProductValue> getTopProducts() {
        return topProducts;
    }

    public void setTopProducts(List<ProductValue> topProducts) {
        this.topProducts = topProducts;
    }

    public List<ProductValue> getResentProductValues() {
        return resentProductValues;
    }

    public void setResentProductValues(List<ProductValue> resentProductValues) {
        this.resentProductValues = resentProductValues;
    }

    public ProductCounts getProductCounts() {
        return productCounts;
    }

    public void setProductCounts(ProductCounts productCounts) {
        this.productCounts = productCounts;
    }

    public DoubleStats getStatistics() {
        return statistics;
    }

    public void setStatistics(DoubleStats statistics) {
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "ProductStatistics{" +
                "productCounts=" + productCounts +
                ", statistics=" + statistics +
                ", resentProductValues=" + resentProductValues +
                '}';
    }
}
