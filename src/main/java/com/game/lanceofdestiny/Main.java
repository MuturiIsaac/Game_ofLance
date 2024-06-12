package com.game.lanceofdestiny;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load the background image
        Image backgroundImage = new Image(getClass().getResourceAsStream("/200Background.png"));

        // Create a canvas
        Canvas canvas = new Canvas(800, 600); // Adjust the size as needed
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw the background image on the canvas
        gc.drawImage(backgroundImage, 0, 0, canvas.getWidth(), canvas.getHeight());

        // You can now draw other game elements on the canvas using the GraphicsContext (gc)
        // For example, drawing a rectangle
        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillRect(100, 100, 50, 50);

        // Set up the root pane and scene
        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("2D Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
