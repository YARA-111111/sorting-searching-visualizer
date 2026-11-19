package algorithms;
import java.util.*;
import ui.AnimationStep;

public class SelectionSort implements SortingAlgorithm {
    public List<AnimationStep> sort(int[] arr) {
        List<AnimationStep> steps = new ArrayList<>();
        steps.add(AnimationStep.arrayStep(arr, -1, -1));
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                SortRecorder.record(steps, arr, min, j);
                if (arr[j] < arr[min]) {
                    min = j;
                    SortRecorder.record(steps, arr, i, min);
                }
            }
            if (min != i) {
                int tmp = arr[i]; arr[i] = arr[min]; arr[min] = tmp;
                SortRecorder.record(steps, arr, i, min);
            }
        }
        return steps;
    }
}