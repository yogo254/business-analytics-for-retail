package com.yogo.service.analytics.predictive;

import com.yogo.util.ExecutorPool;
import javafx.application.Platform;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SalesForeCastUI {

    private static VBox saleForecastScene;
    private static final XYChart.Series<String,Number>hourDay=new XYChart.Series<>();
    private static final XYChart.Series<String,Number>daymonthWeek=new XYChart.Series<>();
    private static final XYChart.Series<String,Number>monthMonthWeek=new XYChart.Series<>();


    public static void updatemonthMonthday(LocalDate date){
        Runnable runnable=()->{
            List<FeaturesCase>featuresCases=new ArrayList<>();
            int numDays=date.getMonth().length(date.isLeapYear());
            int month=date.getMonth().getValue();

         for(int x=1;x<=numDays;x++)
             featuresCases.add(new FeaturesCase(month,x));
         featuresCases=SalesForecast.dayHourPrediction(featuresCases);

            List<FeaturesCase> finalFeaturesCases = featuresCases;
            finalFeaturesCases.stream().forEach(System.out::println);
            Platform.runLater(()->{
                monthMonthWeek.getData().clear();
                monthMonthWeek.setName(date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE,Locale.ENGLISH));
                for (FeaturesCase featuresCase: finalFeaturesCases
                     ) monthMonthWeek.getData().add(new XYChart.Data<>(""+featuresCase.getF2(),featuresCase.getPrediction()));
            });

        };


        ExecutorPool.getExecutor().submit(runnable);
    }
    public static void updatedayMonthWeek(LocalDate date){
        Runnable runnable=()->{
            List<FeaturesCase>featuresCases=new ArrayList<>();
            DayOfWeek dayOfWeek=date.getDayOfWeek();
           int dayOfMonth=date.getDayOfMonth();
           for(int x=1;x<8;++x)
           featuresCases.add(new FeaturesCase(dayOfMonth,x));
           featuresCases=SalesForecast.dayMonthWeekPrediction(featuresCases);

            List<FeaturesCase> finalFeaturesCases = featuresCases;
            Platform.runLater(()->{
                daymonthWeek.getData().clear();
                daymonthWeek.setName(""+dayOfMonth+" "+dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE,Locale.ENGLISH));
                for (FeaturesCase featuresCase: finalFeaturesCases
                ) daymonthWeek.getData().add(new XYChart.Data<>(DayOfWeek.of(featuresCase.getF2()).getDisplayName(TextStyle.FULL_STANDALONE,Locale.ENGLISH),featuresCase.getPrediction()));
            });

        };


        ExecutorPool.getExecutor().submit(runnable);
    }

    public static void updateDayHour(LocalDate date){
        Runnable runnable=()->{
            List<FeaturesCase>featuresCases=new ArrayList<>();
            DayOfWeek dayOfWeek=date.getDayOfWeek();
            int day=dayOfWeek.getValue();
            String dayName=dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH);
            for(int x=0;x<24;x++)
                featuresCases.add(new FeaturesCase(day,x));
            featuresCases=SalesForecast.monthMonthWeekPrediction(featuresCases);

            List<FeaturesCase> finalFeaturesCases = featuresCases;
            Platform.runLater(()->{
                hourDay.getData().clear();
                hourDay.setName(dayName);
                for (FeaturesCase featuresCase: finalFeaturesCases
                ) hourDay.getData().add(new XYChart.Data<>(""+featuresCase.getF2()+":00",featuresCase.getPrediction()));
            });

        };


        ExecutorPool.getExecutor().submit(runnable);
    }


    public static XYChart.Series<String, Number> getHourDay() {
        return hourDay;
    }

    public static VBox getSaleForecastScene() {
        return saleForecastScene;
    }

    public static XYChart.Series<String, Number> getDaymonthWeek() {
        return daymonthWeek;
    }

    public static XYChart.Series<String, Number> getMonthMonthWeek() {
        return monthMonthWeek;
    }

    public static void setSaleForecastScene(VBox saleForecastScene) {
        SalesForeCastUI.saleForecastScene = saleForecastScene;
    }
}
