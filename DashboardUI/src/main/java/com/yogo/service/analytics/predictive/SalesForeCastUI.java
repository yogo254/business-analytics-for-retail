package com.yogo.service.analytics.predictive;

import com.yogo.util.ExecutorPool;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SalesForeCastUI {

    private static VBox saleForecastScene;
    private static final XYChart.Series<Number,Number>hourDay=new XYChart.Series<>();


    public static void updateDayHour(LocalDate date){
        Runnable runnable=()->{
            List<FeaturesCase>featuresCases=new ArrayList<>();
            DayOfWeek dayOfWeek=date.getDayOfWeek();
            int day=dayOfWeek.getValue();
         String dayName=dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
         for(int x=0;x<24;x++)
             featuresCases.add(new FeaturesCase(day,x));
         featuresCases=SalesForecast.dayHourPrediction(featuresCases);

            List<FeaturesCase> finalFeaturesCases = featuresCases;
            finalFeaturesCases.stream().forEach(System.out::println);
            Platform.runLater(()->{
                hourDay.getData().clear();
                hourDay.setName(dayName);
                for (FeaturesCase featuresCase: finalFeaturesCases
                     ) hourDay.getData().add(new XYChart.Data<>(featuresCase.getF2(),featuresCase.getPrediction()));
            });

        };


        ExecutorPool.getExecutor().submit(runnable);
    }


    public static XYChart.Series<Number, Number> getHourDay() {
        return hourDay;
    }

    public static VBox getSaleForecastScene() {
        return saleForecastScene;
    }

    public static void setSaleForecastScene(VBox saleForecastScene) {
        SalesForeCastUI.saleForecastScene = saleForecastScene;
    }
}
