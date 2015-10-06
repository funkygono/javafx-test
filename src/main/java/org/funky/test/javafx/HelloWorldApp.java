package org.funky.test.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorldApp extends Application {

    public static void main(String ... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello world!");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloWorldApp.class.getResource("view/HelloWorld.fxml"));
        StackPane root = loader.load();

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
