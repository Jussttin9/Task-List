package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;

public class create extends App {
    @FXML
    private TextField newUsername;

    @FXML
    private TextField newPassword;

    @FXML
    private void backToLogin() throws IOException {
        if(!loginHashMap.containsKey(newUsername.getText())) {
            loginHashMap.put(newUsername.getText(), newPassword.getText());
            App.setRoot("Login");
        } else {
            System.out.println("Error: Username already taken");
        }
    }

}
