package com.yogo.service.analytics.customer;

import javafx.scene.layout.VBox;

public class CustomerUI {
    private static VBox customerScene;

    public static VBox getCustomerScene() {
        return customerScene;
    }

    public static void setCustomerScene(VBox customerScene) {
        CustomerUI.customerScene = customerScene;
    }
}
