package com.yogo.service.ui.dashboard;

import javafx.scene.Scene;

public class DashboardUI {
    private static Scene dashboard;

    public static Scene getDashboard() {
        return dashboard;
    }

    public static void setDashboard(Scene dashboard) {
        DashboardUI.dashboard = dashboard;
    }
}
