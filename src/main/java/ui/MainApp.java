package ui;

import algorithms.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ui.panes.BSTPane;
import ui.panes.SortingPane;

import datastructures.BSTVisualizer;

public class MainApp extends Application {

    private BorderPane root;
    private AnimationController controller;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();

        VBox control = new VBox(10);
        control.setPadding(new Insets(10));
        control.setPrefWidth(220);

        ComboBox<String> algorithmBox = new ComboBox<>();
        algorithmBox.getItems().addAll(
                "BubbleSort", "InsertionSort", "SelectionSort",
                "MergeSort", "QuickSort", "HeapSort", "BSTSearch");
        algorithmBox.setValue("BubbleSort");

        TextField inputField = new TextField("5,3,8,6,2,7,4,1");
        TextField targetField = new TextField("6");
        Button play = new Button("Play");

        control.getChildren().addAll(
                new Label("Algorithm:"), algorithmBox,
                new Label("Input:"), inputField,
                new Label("BST Target (for BSTSearch):"), targetField,
                play
        );

        root.setRight(control);
        root.setCenter(new Pane());

        play.setOnAction(e -> {
            try {
            String algo = algorithmBox.getValue();
            int[] data = DataLoader.parseInput(inputField.getText());
            if (controller != null) controller.stop();

            // Case 1: BST search visualization
            if ("BSTSearch".equals(algo)) {
                BSTVisualizer bst = new BSTVisualizer();
                for (int v : data) bst.insert(v);

                // Read search target value
                int target = 0;
                try {
                    target = Integer.parseInt(targetField.getText().trim());
                } catch (NumberFormatException ex) { throw new IllegalArgumentException("Enter a valid integer search target."); }
                BSTPane bstPane = new BSTPane();
                bstPane.setTreeFromVisualizer(bst);

                // Use unified animation controller to play search steps
                root.setCenter(bstPane); 
                controller = new AnimationController(bstPane);
                controller.load(bst.searchSteps(target));
                controller.play();

            } else {
                // Case 2: Sorting algorithm visualization
                SortingPane sortPane = new SortingPane();
                controller = new AnimationController(sortPane);

                // Select sorting algorithm based on user choice
                SortingAlgorithm alg = switch (algo) {
                    case "InsertionSort" -> new InsertionSort();
                    case "SelectionSort" -> new SelectionSort();
                    case "MergeSort"     -> new MergeSort();
                    case "QuickSort"     -> new QuickSort();
                    case "HeapSort"      -> new HeapVisualizer();
                    default              -> new BubbleSort();
                };
                // Load sorting animation steps and play
                controller.load(alg.sort(data));
                root.setCenter(sortPane);
                controller.play();
            }
            } catch (IllegalArgumentException ex) { new Alert(Alert.AlertType.WARNING, ex.getMessage(), ButtonType.OK).showAndWait(); }
        });


        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Sorting & Searching Visualizer");
        stage.show();
    }

    @Override public void stop() { if(controller != null) controller.stop(); }

    public static void main(String[] args) {
        launch();
    }
}
