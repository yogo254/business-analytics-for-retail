package com.yogo.predictiveanalytics.models;

import org.apache.spark.ml.feature.LabeledPoint;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;
import java.util.stream.Collectors;

public class SalesForecast {

private SparkSession session;
private LinearRegressionModel dayHourModel;
private LinearRegressionModel dayMonthBydayWeekModel;
private LinearRegressionModel monthBydayMonthModel;

    public SalesForecast(SparkSession session) {
        this.session = session;
        init();
    }

    private void init(){
        Dataset<Row> data=session.read()
                .option("inferSchema",true)
                .option("header",true)
                .csv("saletimeseries.csv");
        List<LabeledPoint> dayByHour=data
                .select("day_week","hour_day","price")
                .groupBy("day_week","hour_day").mean("price")
                .withColumnRenamed("avg(price)","price")
                .collectAsList().stream()
                .map(e->new LabeledPoint(e.getDouble(2), Vectors.dense(e.getInt(0),e.getInt(1))))
                .collect(Collectors.toList());

        List<LabeledPoint>dayMonthByWeek=data.select("day_month","day_week","price")
                .groupBy("day_month","day_week")
                .mean("price")
                .withColumnRenamed("avg(price)","price")
                .collectAsList().stream()
                .map(e->new LabeledPoint(e.getDouble(2),Vectors.dense(e.getInt(0),e.getInt(1))))
                .collect(Collectors.toList());
        List<LabeledPoint>monthBydayMonth=data.select("month","day_month","price")
                .groupBy("month","day_month")
                .mean("price")
                .withColumnRenamed("avg(price)","price")
                .collectAsList().stream()
                .map(e->new LabeledPoint(e.getDouble(2),Vectors.dense(e.getInt(0),e.getInt(1))))
                .collect(Collectors.toList());

        LinearRegression linearRegression=new LinearRegression()
                .setMaxIter(10000)
                .setRegParam(0.01)
                .setElasticNetParam(0.0)
                .setSolver("auto");


       dayHourModel =linearRegression.fit(session.createDataFrame(dayByHour,LabeledPoint.class));
       dayMonthBydayWeekModel=linearRegression.fit(session.createDataFrame(dayMonthByWeek,LabeledPoint.class));
       monthBydayMonthModel=linearRegression.fit(session.createDataFrame(monthBydayMonth,LabeledPoint.class));

    }
    public Dataset<Row> predictMeanSalesBy_dayWeek_Hour(Dataset<Row> data){
        return dayHourModel.transform(data);
    }
    public Dataset<Row>predictMeanSalesBy_month_dayWeek(Dataset<Row>dataset){
        return dayMonthBydayWeekModel.transform(dataset);
    }
    public Dataset<Row>predictMeanSalesBy_month_dayMonth(Dataset<Row>dataset){
        return monthBydayMonthModel.transform(dataset);
    }

    }




