package com.yogo.predictiveanalytics.controler;

import com.yogo.predictiveanalytics.models.SalesForecast;
import com.yogo.predictiveanalytics.models.caseclasses.LinearRegParams;
import com.yogo.predictiveanalytics.models.caseclasses.RegresionEvaluatorMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sales/create")
public class SalePredictionModelsControler {
    @Autowired
    private SalesForecast salesForecast;

    @PostMapping("/dayhour")
    public RegresionEvaluatorMetrics createDayHourModel(@RequestBody LinearRegParams params){
        return salesForecast.createDayByHourModel(params);
    }
    @PostMapping("/daymonth/week")
    public RegresionEvaluatorMetrics createDayMonthWeekModel(@RequestBody LinearRegParams params){
        return salesForecast.createDayMonthByWeedayModel(params);

    }
    @PostMapping("/month/monthweek")
    private RegresionEvaluatorMetrics createMonthMonthDayModel(@RequestBody LinearRegParams params){
        return salesForecast.createMonthByMonthDayModel(params);
    }

}
