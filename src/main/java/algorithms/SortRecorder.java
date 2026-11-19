package algorithms;
import java.util.List;
import ui.AnimationStep;

public class SortRecorder {
    public static void record(List<AnimationStep> steps, int[] arr, int a, int b) {
        steps.add(AnimationStep.arrayStep(arr, a, b));
    }
}