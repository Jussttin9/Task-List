package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import java.io.IOException;

public class Login extends App {       
    @FXML
    TextField userName;

    @FXML
    PasswordField passCode;

    @FXML
    Hyperlink forgotPass;
    
    @FXML
    private void loginSubmit() throws IOException {
        if(loginHashMap.containsKey(userName.getText()) && loginHashMap.get(userName.getText()).equals(passCode.getText())) {
            System.out.println("Successfully Logged In!");
            App.setRoot("secondary");
        }  else {
            System.out.println("Unable to Login. Invalid Username or Password.");
        }
    }

    @FXML
    private void switchToCreate() throws IOException {
        App.setRoot("createAcc");
    }

    @FXML
    private void limitChars() {
        
    }

    @FXML
    private void switchToForgot() throws IOException {
        App.setRoot("checkUser");
    }

}
