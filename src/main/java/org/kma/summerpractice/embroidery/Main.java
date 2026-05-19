package org.kma.summerpractice.embroidery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.awt.*;
import java.util.Objects;

public class Main extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Main.primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));

        Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.jpg")));

        stage.getIcons().add(icon);

        if (java.awt.Taskbar.isTaskbarSupported()) {
            java.awt.Taskbar taskbar = java.awt.Taskbar.getTaskbar();

            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {
                java.awt.Image iconImage = java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.jpg"));
                taskbar.setIconImage(iconImage);
            }
        }

        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        scene.setFill(Color.TRANSPARENT);

        stage.setTitle("Embroidery");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

    }
}
