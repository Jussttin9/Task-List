package com.example;

import java.io.IOException;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXML;

public class forgotPass extends CheckUser {
    @FXML
    PasswordField newPassword;

    @FXML
    PasswordField confirmNewPassword;

    @FXML
    private void switchToLogin() throws IOException {
        String username = getUsername();
        final String oldPass = loginHashMap.get(username);
        if(newPassword.getText().equals(oldPass)) {
            System.out.println("New password cannot be the same as your old password.");
        } else if(newPassword.getText().equals(confirmNewPassword.getText())) {
           loginHashMap.replace(username, newPassword.getText());
           App.setRoot("Login");  
        } else {
            System.out.println("Please make sure you typed in the same password in both fields.");
        }
        
    }
}
