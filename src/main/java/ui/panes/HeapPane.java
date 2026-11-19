package ui.panes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.AnimationController;
import ui.AnimationStep;

public class HeapPane extends Pane implements AnimationController.AnimationPane {

    public HeapPane() {
        setPrefSize(600, 250);
        setMinSize(600, 250);
        setMaxSize(600, 250);
    }

    @Override
    public void renderStep(AnimationStep step) {
        getChildren().clear();
        if (step == null || step.getArrayState() == null) return;

        int[] arr = step.getArrayState();
        double barWidth = getPrefWidth() / arr.length;

        for (int i = 0; i < arr.length; i++) {
            double height = Math.max(arr[i] * 15, 5);
            Rectangle bar = new Rectangle(barWidth - 5, height);
            bar.setTranslateX(i * barWidth);
            bar.setTranslateY(getPrefHeight() - height);

            if (i == step.getHighlightA() || i == step.getHighlightB())
                bar.setFill(Color.GREEN);
            else
                bar.setFill(Color.ORANGE);

            getChildren().add(bar);
        }
    }

    @Override
    public void reset() {
        getChildren().clear();
    }
}