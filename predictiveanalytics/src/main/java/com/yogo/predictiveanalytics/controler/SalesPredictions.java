package com.yogo.predictiveanalytics.controler;

import com.yogo.predictiveanalytics.models.SalesForecast;
import com.yogo.predictiveanalytics.models.caseclasses.FeaturesCase;
import org.apache.spark.ml.feature.LabeledPoint;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/sales")
public class SalesPredictions {
    @Autowired
    private SalesForecast salesForecast;
    @Autowired
    private SparkSession session;

    @GetMapping("/dayhour")
    public List<Double>predictByDayHour(@RequestBody List<FeaturesCase> featuresCases){
        List<LabeledPoint>labeledPoints=featuresCases
                .stream()
                .map(e->new LabeledPoint(0.0, Vectors.dense(e.getF1(),e.getF2())))
                .collect(Collectors.toList());
        List<Row> predictions=salesForecast
                .predictMeanSalesBy_dayWeek_Hour(session.createDataFrame(labeledPoints,LabeledPoint.class))
                .select("prediction")
                .collectAsList();
        return predictions.stream()
                .map(e->e.getDouble(0))
                .collect(Collectors.toList());
    }
    @GetMapping("/daymonth/week")
    public List<Double>predictBymonthDay_week(@RequestBody List<FeaturesCase> featuresCases){
        List<LabeledPoint>labeledPoints=featuresCases
                .stream()
                .map(e->new LabeledPoint(0.0, Vectors.dense(e.getF1(),e.getF2())))
                .collect(Collectors.toList());
        List<Row> predictions=salesForecast
                .predictMeanSalesBy_month_dayWeek(session.createDataFrame(labeledPoints,LabeledPoint.class))
                .select("prediction")
                .collectAsList();
        return predictions.stream()
                .map(e->e.getDouble(0))
                .collect(Collectors.toList());
    }
    @GetMapping("/month/monthweek")
    public List<Double>predictmonth_MonthDay(@RequestBody List<FeaturesCase> featuresCases){
        List<LabeledPoint>labeledPoints=featuresCases
                .stream()
                .map(e->new LabeledPoint(0.0, Vectors.dense(e.getF1(),e.getF2())))
                .collect(Collectors.toList());
        List<Row> predictions=salesForecast
                .predictMeanSalesBy_month_dayMonth(session.createDataFrame(labeledPoints,LabeledPoint.class))
                .select("prediction")
                .collectAsList();
        return predictions.stream()
                .map(e->e.getDouble(0))
                .collect(Collectors.toList());
    }

}
