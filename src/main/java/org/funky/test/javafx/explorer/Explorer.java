package org.funky.test.javafx.explorer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The <code>Funky Explorer</code> application.
 */
public class Explorer extends Application {

    public static void main(String ... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Funky Explorer");
        Parent explorer = FXMLLoader.load(getClass().getResource("Explorer.fxml"));
        Scene scene = new Scene(explorer);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
