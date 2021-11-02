package com.ramich;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        configStage(primaryStage);

        Scene scene = new Scene(root, 510, 600);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void configStage(Stage stage){
        stage.show();
        stage.setTitle("Sapper");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:res/img/icon.png"));
    }
}
