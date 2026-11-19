package algorithms;
import java.util.*;
import ui.AnimationStep;

public class InsertionSort implements SortingAlgorithm {
    public List<AnimationStep> sort(int[] arr) {
        List<AnimationStep> steps = new ArrayList<>();
        steps.add(AnimationStep.arrayStep(arr, -1, -1));
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i], j = i-1;
            SortRecorder.record(steps, arr, i, -1);
            while (j >= 0 && arr[j] > key) {
                SortRecorder.record(steps, arr, j, i);
                arr[j+1] = arr[j];
                SortRecorder.record(steps, arr, j, j+1);
                j--;
            }
            arr[j+1] = key;
            SortRecorder.record(steps, arr, j+1, i);
        }
        return steps;
    }
}