package algorithms;
import java.util.*;
import ui.AnimationStep;

public class BubbleSort implements SortingAlgorithm {
    public List<AnimationStep> sort(int[] arr) {
        List<AnimationStep> steps = new ArrayList<>();
        steps.add(AnimationStep.arrayStep(arr, -1, -1));
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                SortRecorder.record(steps, arr, j, j + 1);
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = tmp;
                    SortRecorder.record(steps, arr, j, j + 1);
                }
            }
        }
        return steps;
    }
}