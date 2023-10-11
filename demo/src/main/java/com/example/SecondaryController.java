package com.example;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class SecondaryController {
// things to do:
    // implement strikethrough when checking the box using css

    HBox task;

    @FXML
    Button addTask;

    @FXML
    VBox panel;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void click() {
        addTask.setTranslateY(20.0 + addTask.getTranslateY());
        addCheck();
    }

    private void addCheck() {
        // removes the + button
        panel.getChildren().remove(addTask);

        // creates the new nodes needed for a task
        TextField newText = new TextField();
        CheckBox newCheck = new CheckBox();
        Button newButton = new Button("X");

        // Resizes checkbox
        newCheck.setScaleY(1.5);
        newCheck.setScaleX(1.5);
        newCheck.setPadding(new Insets(2.5, 2, 0, 0));

        // adds event handlers for nodes
        newCheck.setOnAction(e -> {
            strikeOutTask(newCheck);
        });

        addEventText(newText);

        newButton.setOnAction(e -> {
            if(newButton.getParent() instanceof HBox && newButton.getParent().getParent() instanceof VBox) {
                HBox taskGroup = (HBox)newButton.getParent();
                VBox taskList = (VBox)newButton.getParent().getParent();
                taskList.getChildren().remove(taskGroup);
            }
        });

        // adds the group of nodes needed when creating a new task
        task = new HBox();
        task.getChildren().addAll(newCheck, newText, newButton);
        task.setSpacing(10);

        panel.getChildren().add(task);
        addTask = new Button("+");
        panel.getChildren().add(addTask);
        addTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                click();
            }
        });

    }

    // adds the drag and drop feature for the tasks
    // this is done by just switching the text between the
    // selected TextFields and then updating their checkbox status
    private void addEventText(TextField t) {
        // when the system detects a cursor dragging this specific node
        // it copies the text inside the Textfield and prepares for movement
        t.setOnDragDetected(e -> {
            Dragboard db = t.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(t.getText());
            db.setContent(content);
            e.consume(); 
        });

        // this filters out the type of nodes that are able to be dragged over this node
        // in this case, only nodes that have the TransferMode, MOVE, is allowed
        // to be successfully dragged over this node
        t.setOnDragOver(e -> {
            if(e.getGestureSource() != t && e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });


        t.setOnDragEntered(e -> {
            // used for providing the user a visual appearance
            // when hovering over a valid node
        });

        t.setOnDragExited(e -> {
            // used for providing the user a visual appearance
            // when hovering over a valid node
        });

        // when a node is dropped onto this one, it will read its data
        // and copy its text into its own node
        t.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            boolean success = false;
            Object source = e.getGestureSource();

            if(source instanceof TextField) {
                TextField sourceText = (TextField)source;
                boolean sourceCheckStatus = getCheckStatus(sourceText);
                updateCheck(sourceText, getCheckStatus(t));
                updateCheck(t, sourceCheckStatus);
            }

            // saves the replaced text so it can replace the text in the dropped node
            ClipboardContent content = new ClipboardContent();
            content.putString(t.getText());

            if(db.hasString()) {
                t.setText(db.getString());
                success = true;
            }

            db.setContent(content);
            e.setDropCompleted(success);
            e.consume();
        });

        // when this node is done dropping its data into another node
        // it will set the text of this node to the text that it had originally replaced
        t.setOnDragDone(e -> {
            if(e.getTransferMode() == TransferMode.MOVE) {
                Dragboard db = e.getDragboard();
                t.setText(db.getString());
            }
            e.consume();
        });
        
    }

    private Node getNode(List<Node> childList, TextField t) {
        for(Node n : childList) {
            if(n instanceof TextField) {
                return n;
            }
        }
        return null;
    }

    private Node getNode(List<Node> childList, CheckBox c) {
        for(Node n : childList) {
            if(n instanceof CheckBox) {
                return n;
            }
        }
        return null;
    }
    
    private boolean getCheckStatus(TextField t) {
        boolean status = false;

        if(t.getParent() instanceof HBox) {
            HBox mainGroup = (HBox)t.getParent();
            CheckBox c = (CheckBox)getNode(mainGroup.getChildren(), new CheckBox());
            if(c != null) {
                status = c.isSelected();
            }
        }
        
        return status;
    }

    private void updateCheck(TextField t, boolean checkStatus) {
        if(t.getParent() instanceof HBox) {
            HBox mainGroup = (HBox)t.getParent();
            CheckBox c = (CheckBox)getNode(mainGroup.getChildren(), new CheckBox());
            if(c != null) {
                c.setSelected(checkStatus);
            }
        }
    }

    private void strikeOutTask(CheckBox check) {
        if(check.getParent() instanceof HBox) {
            HBox mainGroup = (HBox)check.getParent();
            TextField t = (TextField)getNode(mainGroup.getChildren(), new TextField());

            if(t != null) {
                // do something
            }

            if(check.isSelected()) {
                // MAKE IT SO THAT WHEN THE BOX IS CHECKED IT PUTS A STRIKETHROUGH ON THE TEXT
                //groupText.setText("Strikethrough");
            } else {
                // MAKE IT SO THAT WHEN THE BOX IS UNCHECKED IT REMOVES THE STRIKETHROUGH ON THE TEXT
                //groupText.setText("REMOVED Strikethrough");
            }
        } else {
            return;
        }
    }
}