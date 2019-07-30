package com.yogo.controler.product;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductControler {

    @FXML
    private VBox dashboard;
    @FXML
    private HBox kpiPane;

    @FXML
    private VBox tablePane;
    @FXML
    private StackedBarChart<?, ?> stackBarGraph;
    @FXML
    private CategoryAxis stackCategory;
    @FXML
    private NumberAxis stackNumber;
    @FXML
    private PieChart topPieChart;

}
