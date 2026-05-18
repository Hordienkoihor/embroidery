package org.kma.summerpractice.embroidery;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Label windowTitle;

    @FXML
    private Button titleBarClose;

    @FXML
    private Button titleBarMinimize;

    @FXML
    private Button titleBarMaximise;

    private boolean isMaximized = false;
    private double prevX, prevY, prevWidth, prevHeight;

    @FXML
    public void initialize() {
        windowTitle.setText("Embroidery");
    }


    @FXML
    public void maximize(ActionEvent actionEvent) {
        Stage stage = Main.primaryStage;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        if (isMaximized) {

            stage.setX(prevX);
            stage.setY(prevY);
            stage.setWidth(prevWidth);
            stage.setHeight(prevHeight);

        } else {
            prevX = stage.getX();
            prevY = stage.getY();
            prevWidth = stage.getWidth();
            prevHeight = stage.getHeight();

            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());

        }

        isMaximized = !isMaximized;
    }

    @FXML
    public void minimize(ActionEvent actionEvent) {
        Main.primaryStage.setIconified(true);
    }

    @FXML
    public void close(ActionEvent actionEvent) {
        Platform.exit();
    }
}
