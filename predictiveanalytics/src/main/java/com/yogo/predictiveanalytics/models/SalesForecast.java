package com.yogo.predictiveanalytics.models;

import com.yogo.predictiveanalytics.domain.Sale;
import com.yogo.predictiveanalytics.models.caseclasses.LinearRegParams;
import com.yogo.predictiveanalytics.models.caseclasses.PriceTimestamp;
import com.yogo.predictiveanalytics.models.caseclasses.RegresionEvaluatorMetrics;
import org.apache.spark.ml.feature.LabeledPoint;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.ml.regression.LinearRegression;
import org.apache.spark.ml.regression.LinearRegressionModel;
import org.apache.spark.ml.evaluation.RegressionEvaluator;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.List;
import java.util.stream.Collectors;

public class SalesForecast {

private static SparkSession session;
private static Dataset<Row>dataset;

private static final LinearRegression linearRegression=new LinearRegression();
    private  LinearRegressionModel dayByHourModel;
    private  LinearRegressionModel dayMonthByWeedayModel;
    private  LinearRegressionModel monthByMonthDayModel;

    public SalesForecast(SparkSession session) {
        this.session = session;
    }

    private  void createTimestamps(List<Sale> sales){
      List<PriceTimestamp>priceTimestamps=  sales.stream()
                .map(e->

                        new PriceTimestamp(e.toLocalDateTime().getMonth().getValue()
                                ,e.toLocalDateTime().getDayOfMonth(),
                                e.toLocalDateTime().getDayOfWeek().getValue(),
                                e.toLocalDateTime().getHour(),e.getPrice())
                )
                .collect(Collectors.toList());
        dataset=session.createDataFrame(priceTimestamps,PriceTimestamp.class);
    }
    public RegresionEvaluatorMetrics createDayByHourModel(LinearRegParams params){

                linearRegression.setMaxIter(params.getMaxIterations())
                .setRegParam(params.getRegParam())
                .setElasticNetParam(params.getElasticNetParam())
                .setSolver(params.getSolver());
        List<LabeledPoint> dayByHour=dataset
                .select("day_week","hour_day","price")
                .groupBy("day_week","hour_day").mean("price")
                .withColumnRenamed("avg(price)","price")
                .collectAsList().stream()
                .map(e->new LabeledPoint(e.getDouble(2), Vectors.dense(e.getInt(0),e.getInt(1))))
                .collect(Collectors.toList());
        Dataset<Row>[] splits= session.createDataFrame(dayByHour,LabeledPoint.class).randomSplit(new double[]{0.9,0.1},11L);
        Dataset<Row> train=splits[0];
        Dataset<Row> test=splits[1];
        dayByHourModel=linearRegression.fit(train);

        return getRegresionEvaluatorMetrics(dayByHourModel,test);


    }




    public  RegresionEvaluatorMetrics createDayMonthByWeedayModel(LinearRegParams params){
        linearRegression.setMaxIter(params.getMaxIterations())
                .setRegParam(params.getRegParam())
                .setElasticNetParam(params.getElasticNetParam())
                .setSolver(params.getSolver());
        List<LabeledPoint>dayMonthByWeek=dataset.select("day_month","day_week","price")
                .groupBy("day_month","day_week")
                .mean("price")
                .withColumnRenamed("avg(price)","price")
                .collectAsList().stream()
                .map(e->new LabeledPoint(e.getDouble(2),Vectors.dense(e.getInt(0),e.getInt(1))))
                .collect(Collectors.toList());
        Dataset<Row>[] splits= session.createDataFrame(dayMonthByWeek,LabeledPoint.class).randomSplit(new double[]{0.9,0.1},11L);
        Dataset<Row> train=splits[0];
        Dataset<Row> test=splits[1];
        dayMonthByWeedayModel=linearRegression.fit(train);

      return getRegresionEvaluatorMetrics(dayMonthByWeedayModel,test);




    }
    public RegresionEvaluatorMetrics createMonthByMonthDayModel(LinearRegParams params){
        linearRegression.setMaxIter(params.getMaxIterations())
                .setRegParam(params.getRegParam())
                .setElasticNetParam(params.getElasticNetParam())
                .setSolver(params.getSolver());

        List<LabeledPoint>monthBydayMonth=dataset.select("month","day_month","price")
                .groupBy("month","day_month")
                .mean("price")
                .withColumnRenamed("avg(price)","price")
                .collectAsList().stream()
                .map(e->new LabeledPoint(e.getDouble(2),Vectors.dense(e.getInt(0),e.getInt(1))))
                .collect(Collectors.toList());
        Dataset<Row>[] splits= session.createDataFrame(monthBydayMonth,LabeledPoint.class).randomSplit(new double[]{0.9,0.1},11L);
        Dataset<Row> train=splits[0];
        Dataset<Row> test=splits[1];
        monthByMonthDayModel=linearRegression.fit(train);

       return getRegresionEvaluatorMetrics(monthByMonthDayModel,test)0;

    }

    private RegresionEvaluatorMetrics getRegresionEvaluatorMetrics(LinearRegressionModel model,Dataset<Row> test) {


             Dataset<Row>predictions=model.transform(test);
        return  getMetrics(predictions);
    }

    private RegresionEvaluatorMetrics getMetrics(Dataset<Row> predictions) {
        RegressionEvaluator evaluator=new RegressionEvaluator();
        evaluator.setLabelCol("label")
                .setPredictionCol("prediction")
                .setMetricName("rmse");

        double rmse=evaluator.evaluate(predictions);
        evaluator.setMetricName("mse");
        double mse=evaluator.evaluate(predictions);
        double r2=evaluator.setMetricName("r2").evaluate(predictions);
        double mae=evaluator.setMetricName("mae").evaluate(predictions);
        RegresionEvaluatorMetrics metrics=new RegresionEvaluatorMetrics();
        metrics.setMae(mae);
        metrics.setR2(r2);
        metrics.setMse(mse);
        metrics.setRmse(rmse);
        return metrics;
    }


    public  void setSales(List<Sale> sales) {
     createTimestamps(sales);
     LinearRegParams defaultRegParams=new LinearRegParams();
     defaultRegParams.setElasticNetParam(0.0);
     defaultRegParams.setMaxIterations(1000);
     defaultRegParams.setRegParam(0.001);
     defaultRegParams.setSolver("auto");
     createDayByHourModel(defaultRegParams);
     createMonthByMonthDayModel(defaultRegParams);
     createDayMonthByWeedayModel(defaultRegParams);
    }

    public Dataset<Row> predictMeanSalesBy_dayWeek_Hour(Dataset<Row> dataFrame) {
        return dayByHourModel.transform(dataFrame);
    }

    public Dataset<Row> predictMeanSalesBy_month_dayWeek(Dataset<Row> dataFrame) {
        return dayMonthByWeedayModel.transform(dataFrame);
    }

    public Dataset<Row> predictMeanSalesBy_month_dayMonth(Dataset<Row> dataFrame) {
        return monthByMonthDayModel.transform(dataFrame);
    }
}




