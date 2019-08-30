package com.yogo.predictiveanalytics.models.caseclasses;

public class LinearRegParams {
    private double regParam;
    private double elasticNetParam;
    private String solver;
    private int maxIterations;

    public double getRegParam() {
        return regParam;
    }

    public void setRegParam(double regParam) {
        this.regParam = regParam;
    }

    public double getElasticNetParam() {
        return elasticNetParam;
    }

    public void setElasticNetParam(double elasticNetParam) {
        this.elasticNetParam = elasticNetParam;
    }

    public String getSolver() {
        return solver;
    }

    public void setSolver(String solver) {
        this.solver = solver;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public void setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
    }
}
