package com.game.lanceofdestiny;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load images
        Image backgroundImage = new Image(getClass().getResourceAsStream("/200Background.png"));
        Image fireballImage = new Image(getClass().getResourceAsStream("/Fireball.png"));
        Image blueGemImage = new Image(getClass().getResourceAsStream("/BlueGem.png"));
        Image firmImage = new Image(getClass().getResourceAsStream("/Firm.png"));
        Image greenGemImage = new Image(getClass().getResourceAsStream("/GreenGem.png"));
        Image heartImage = new Image(getClass().getResourceAsStream("/Heart.png"));
        Image redGemImage = new Image(getClass().getResourceAsStream("/RedGem.png"));
        Image iconFirmImage = new Image(getClass().getResourceAsStream("/IconFirm.png"));
        Image iconGreenGemImage = new Image(getClass().getResourceAsStream("/IconGreenGem.png"));

        // Create a canvas for the background
        Canvas backgroundCanvas = new Canvas(800, 600);
        GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
        gc.drawImage(backgroundImage, 0, 0, backgroundCanvas.getWidth(), backgroundCanvas.getHeight());

        // Create a GridPane for the game objects
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10); // Vertical gap between rows
        gridPane.setHgap(10); // Horizontal gap between columns

        // Define the grid layout
        int rows = 4;
        int columns = 7;
        double objectSize = 50; // Size for each object

        // Images to be used in the grid
        Image[] gridImages = {
                fireballImage, blueGemImage, firmImage, greenGemImage, heartImage, redGemImage,
                fireballImage, blueGemImage, firmImage, greenGemImage, heartImage, redGemImage,
                fireballImage, blueGemImage, firmImage, greenGemImage, heartImage, redGemImage,
                fireballImage, blueGemImage, firmImage, greenGemImage, heartImage, redGemImage,
                fireballImage, blueGemImage, firmImage, greenGemImage
        };

        // Populate the GridPane with images
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (index < gridImages.length) {
                    Canvas objectCanvas = new Canvas(objectSize, objectSize);
                    GraphicsContext objectGc = objectCanvas.getGraphicsContext2D();
                    objectGc.drawImage(gridImages[index], 0, 0, objectSize, objectSize);
                    gridPane.add(objectCanvas, col, row);
                    index++;
                }
            }
        }

        // Create bottom elements
        VBox bottomBox = new VBox(10);
        bottomBox.setAlignment(Pos.BOTTOM_CENTER);
        bottomBox.getChildren().addAll(
                createImageCanvas(iconFirmImage, objectSize),
                createImageCanvas(iconGreenGemImage, objectSize)
        );

        // Create top left buttons
        HBox topLeftBox = new HBox(10);
        topLeftBox.setAlignment(Pos.TOP_LEFT);
        topLeftBox.setPadding(new Insets(10));
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");
        Button pauseButton = new Button("Pause");
        Button quitButton = new Button("Quit");
        topLeftBox.getChildren().addAll(saveButton, loadButton, pauseButton, quitButton);

        // Create top right score and lives
        // Create top right score and lives
        VBox topRightBox = new VBox(10);
        topRightBox.setAlignment(Pos.TOP_RIGHT);
        topRightBox.setPadding(new Insets(10));

        // Create Score Label
        Label scoreLabel = new Label("Score: 102");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        scoreLabel.setTextFill(Color.WHITE); // Set text color to white
        topRightBox.getChildren().add(scoreLabel);

        // Create Lives Label
        // Create Lives Label
        HBox livesBox = new HBox(5);
        Label livesLabel = new Label("Lives: ");
        livesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        livesLabel.setTextFill(Color.WHITE); // Set text color to white
        for (int i = 0; i < 3; i++) {
            livesBox.getChildren().add(createImageCanvas(heartImage, 20));
        }

// Create a new HBox to hold the livesLabel and livesBox
        HBox livesHBox = new HBox();
        livesHBox.setAlignment(Pos.CENTER_RIGHT); // Align to the right
        livesHBox.getChildren().addAll(livesLabel, livesBox);

// Add the livesHBox to the topRightBox
        topRightBox.getChildren().add(livesHBox);

        // Combine gridPane and bottomBox in a VBox
        VBox centerBox = new VBox(20);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.getChildren().add(gridPane);

        // Set up the root StackPane to overlay elements on the canvas
        StackPane rootStack = new StackPane();
        rootStack.getChildren().addAll(backgroundCanvas, centerBox, bottomBox, topLeftBox, topRightBox);

        // Position bottomBox at the bottom center
        StackPane.setAlignment(bottomBox, Pos.BOTTOM_CENTER);
        StackPane.setMargin(bottomBox, new Insets(0, 0, 20, 0));

        // Position topLeftBox at the top left
        StackPane.setAlignment(topLeftBox, Pos.TOP_LEFT);
        StackPane.setMargin(topLeftBox, new Insets(10, 0, 0, 10));

        // Position topRightBox at the top right
        StackPane.setAlignment(topRightBox, Pos.TOP_RIGHT);
        StackPane.setMargin(topRightBox, new Insets(10, 10, 0, 0));

        // Set up the scene
        Scene scene = new Scene(rootStack, 800, 600);

        primaryStage.setTitle("Lance of Destiny: Phase I");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create a Canvas with an image
    private Canvas createImageCanvas(Image image, double size) {
        Canvas canvas = new Canvas(size, size);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0, size, size);
        return canvas;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
