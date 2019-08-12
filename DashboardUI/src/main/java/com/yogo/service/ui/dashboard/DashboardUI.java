package com.yogo.service.ui.dashboard;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardUI {
    private static Scene dashboard;

    public static Scene getDashboard() {
        return dashboard;
    }

    public static void setDashboard(Scene dashboard) {
        DashboardUI.dashboard = dashboard;
    }
    public static void switchDashboard(VBox vBox){
        HBox box= (HBox) dashboard.lookup("#mainPane");
        box.getChildren().remove(1);
        box.getChildren().add(vBox);
    }
}
