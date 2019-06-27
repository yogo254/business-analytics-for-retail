package com.yogo.javafxdatautil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavafxDataUtilApplication extends Application {

    public static void main(String[] args) {
        SpringApplication.run(JavafxDataUtilApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox box=new HBox();
        Scene scene=new Scene(box,400,400);
        stage.setScene(scene);
        stage.setTitle("Analytics Dashboard");
        stage.show();

    }
}
