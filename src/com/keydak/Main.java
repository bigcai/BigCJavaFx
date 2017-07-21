package com.keydak;

import com.keydak.contronller.main.ContainerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage currentStage = null;

    @Override
    public void start(Stage primaryStage) throws Exception{

        currentStage = primaryStage;
        ContainerController controller = new ContainerController();
        primaryStage.setTitle("配置管理云平台");
        primaryStage.setScene(new Scene(controller));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage currentStage) {
        Main.currentStage = currentStage;
    }
}
