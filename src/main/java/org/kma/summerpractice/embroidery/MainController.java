package org.kma.summerpractice.embroidery;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;

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
    private Canvas drawingCanvas;
    private int cols = 50;
    private int rows = 30;
    private double cellSize;
    private Color[][] colors = new Color[rows][cols];

    @FXML
    private ColorPicker colorPicker;
    private Color currentColor = Color.BLACK;

    private double currentX, currentY;

    @FXML
    public void initialize() {
        windowTitle.setText("Embroidery");

        cellSize = drawingCanvas.getWidth() / cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                colors[i][j] = Color.WHITE;
            }
        }

        drawGrid();

    }

    private void drawGrid() {
        GraphicsContext context = drawingCanvas.getGraphicsContext2D();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                context.setFill(colors[i][j]);
                context.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                context.setStroke(Color.LIGHTGRAY);
                context.setLineWidth(1.0);
                context.strokeRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    @FXML
    private void draw(MouseEvent event) {
        int col = (int) (event.getX() / cellSize);
        int row = (int) (event.getY() / cellSize);

        if (col >= 0 && col < cols && row >= 0 && row < rows) {
            colors[row][col] = currentColor;
            drawGrid();
        }
    }

    @FXML
    private void choseColor(ActionEvent event) {
        currentColor = colorPicker.getValue();
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

    public void dragged(MouseEvent mouseEvent) {
        Stage stage = Main.primaryStage;

        stage.setX(mouseEvent.getScreenX() - currentX);
        stage.setY(mouseEvent.getScreenY() - currentY);
    }

    public void pressed(MouseEvent mouseEvent) {
        currentX = mouseEvent.getSceneX();
        currentY = mouseEvent.getSceneY();
    }
}
