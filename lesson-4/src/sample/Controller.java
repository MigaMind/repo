package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    HBox msgPanel;

    @FXML
    TextField msgField;

    @FXML
    TextArea msgTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized");
    }

    public void showAlert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
            alert.showAndWait();
        });
    }

    public void sendMsgToArea(String msg) {
        msgTextArea.appendText("\n" + msg);
    }

    public void sendMsg() {
        //showAlert(msgField.getText());
        sendMsgToArea(msgField.getText());
        msgField.clear();
        msgField.requestFocus();
    }
}