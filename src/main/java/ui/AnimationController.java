package ui;

import javafx.animation.*;
import javafx.util.Duration;
import java.util.List;

public class AnimationController {

    public interface AnimationPane {
        void renderStep(AnimationStep step);
        void reset();
    }

    private final AnimationPane pane;
    private List<AnimationStep> steps;
    private int index = 0;
    private Timeline timeline;
    public void stop() { if(timeline != null) { timeline.stop(); timeline = null; } }

    public AnimationController(AnimationPane pane) {
        this.pane = pane;
    }

    public void load(List<AnimationStep> steps) {
        stop();
        this.steps = steps;
        this.index = 0;
        pane.reset();
        // 修复：立即渲染第一步
        if (!steps.isEmpty()) {
            pane.renderStep(steps.get(0));
            index = 1;
        }
    }

    public void play() {
        stop();
        if (steps == null || index >= steps.size()) return;

        timeline = new Timeline(
            new KeyFrame(Duration.millis(600), e -> playStep())
        );
        timeline.setCycleCount(steps.size() - index); 
        timeline.play();
    }

    private void playStep() {
        if (index >= steps.size()) return;
        pane.renderStep(steps.get(index));
        index++;
    }
}
