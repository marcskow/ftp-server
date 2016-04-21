package pl.edu.agh.marcskow.ftpserver.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ServerApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane ftpRootPane = FXMLLoader.load(this.getClass().getClassLoader().getResource("ServerPanel.fxml"));
        Scene scene = new Scene(ftpRootPane, ftpRootPane.getPrefWidth(), ftpRootPane.getPrefHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }


    public static void main(String[] args) {
        Application.launch(ServerApplication.class);
    }
}