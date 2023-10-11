package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    protected static HashMap<String, String> loginHashMap;

    // starting window
    public void start(Stage stage) throws IOException {
        // the two Scene int parameters represent the dimensions of the new window
        // 640 is the width
        // 480 is the height
        scene = new Scene(loadFXML("Login"), 640, 480);

        scene.getStylesheets().add(this.getClass().getResource("webappStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        stage.setTitle("To-Do List");

        loginHashMap = new HashMap<>();
    }

    // switches the window to whatever fxml file is sent into the parameter
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    // helper method for the setRoot method
    // gets the fxml file and returns it
    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println(App.class.getResource(fxml + ".fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}