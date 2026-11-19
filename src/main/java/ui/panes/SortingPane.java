package ui.panes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.AnimationController;
import ui.AnimationStep;

public class SortingPane extends Pane implements AnimationController.AnimationPane {

    public SortingPane() {
        setPrefSize(600, 250);
    }

    @Override
    public void renderStep(AnimationStep step) {
        getChildren().clear();
        if (step == null || step.getArrayState() == null) return;

        int[] arr = step.getArrayState();
        if(arr.length == 0) return;
        long min = Math.min(0, java.util.Arrays.stream(arr).min().orElse(0));
        long max = Math.max(0, java.util.Arrays.stream(arr).max().orElse(0));
        double scale = 220.0 / Math.max(1L, max-min);
        double baseline = 15 + max*scale;
        double barWidth = getPrefWidth() / arr.length;

        for (int i = 0; i < arr.length; i++) {
            double height = Math.max(Math.abs((double)arr[i]) * scale, 2);
            Rectangle bar = new Rectangle(barWidth - 5, height);
            bar.setTranslateX(i * barWidth);
            bar.setTranslateY(arr[i] >= 0 ? baseline-height : baseline);

            if (i == step.getHighlightA() || i == step.getHighlightB())
                bar.setFill(Color.RED);
            else
                bar.setFill(Color.BLUE);

            getChildren().add(bar);
        }
    }

    @Override
    public void reset() {
        getChildren().clear();
    }
}

