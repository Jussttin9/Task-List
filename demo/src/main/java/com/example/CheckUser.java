package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.io.IOException;

public class CheckUser extends App {
    @FXML
    TextField checkUsername;

    @FXML
    Label errorMsg;

    private static String username;

    @FXML
    private void returnBack() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    private void checkUser() throws IOException {
        if(loginHashMap.containsKey(checkUsername.getText())) {
            username = checkUsername.getText();
            App.setRoot("forgotPass");
        } else {
            errorMsg.setText("Invalid Username. Please try again.");
        }
    }

    protected String getUsername() {
        return username;
    }
}
