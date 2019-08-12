package com.yogo.predictiveanalytics.controler;

import com.yogo.predictiveanalytics.models.SalesForecast;
import com.yogo.predictiveanalytics.models.caseclasses.FeaturesCase;
import org.apache.spark.ml.feature.LabeledPoint;
import org.apache.spark.ml.linalg.Vectors;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/sales")
public class SalesPredictions {
    @Autowired
    private SalesForecast salesForecast;
    @Autowired
    private SparkSession session;

    @PostMapping("/dayhour")
    public FeaturesCase predictByDayHour(@RequestBody FeaturesCase featuresCase){
        List<LabeledPoint>labeledPoints=new ArrayList<>();
        labeledPoints.add(new LabeledPoint(0.0,Vectors.dense(featuresCase.getF1(),featuresCase.getF2())));
        List<Double> predictions=salesForecast
                .predictMeanSalesBy_dayWeek_Hour(session.createDataFrame(labeledPoints,LabeledPoint.class))
                .select("prediction")
                .collectAsList()
                .stream().map(e->e.getDouble(0))
                .collect(Collectors.toList());
        featuresCase.setPrediction(predictions.get(0));

        return featuresCase;
    }
    @PostMapping("/daymonth/week")
    public FeaturesCase predictBymonthDay_week(@RequestBody FeaturesCase featuresCase){
        List<LabeledPoint>labeledPoints=new ArrayList<>();
        labeledPoints.add(new LabeledPoint(0.0,Vectors.dense(featuresCase.getF1(),featuresCase.getF2())));
        List<Double> predictions=salesForecast
                .predictMeanSalesBy_month_dayWeek(session.createDataFrame(labeledPoints,LabeledPoint.class))
                .select("prediction")
                .collectAsList()
                .stream().map(e->e.getDouble(0))
                .collect(Collectors.toList());
        featuresCase.setPrediction(predictions.get(0));

        return featuresCase;
    }
    @PostMapping("/month/monthweek")
    public FeaturesCase predictmonth_MonthDay(@RequestBody FeaturesCase featuresCase){
        List<LabeledPoint>labeledPoints=new ArrayList<>();
        labeledPoints.add(new LabeledPoint(0.0,Vectors.dense(featuresCase.getF1(),featuresCase.getF2())));
        List<Double> predictions=salesForecast
                .predictMeanSalesBy_month_dayMonth(session.createDataFrame(labeledPoints,LabeledPoint.class))
                .select("prediction")
                .collectAsList()
                .stream().map(e->e.getDouble(0))
                .collect(Collectors.toList());
        featuresCase.setPrediction(predictions.get(0));

        return featuresCase;
    }

}
