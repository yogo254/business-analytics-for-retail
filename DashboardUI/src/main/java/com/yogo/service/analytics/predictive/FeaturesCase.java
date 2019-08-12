package com.yogo.service.analytics.predictive;

public class FeaturesCase {
   private int f1;
   private int f2;
   private double prediction;

    public FeaturesCase() {
    }

    public FeaturesCase(int f1, int f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public double getPrediction() {
        return prediction;
    }

    public void setPrediction(double prediction) {
        this.prediction = prediction;
    }

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "FeaturesCase{" +
                "f1=" + f1 +
                ", f2=" + f2 +
                ", prediction=" + prediction +
                '}';
    }
}
