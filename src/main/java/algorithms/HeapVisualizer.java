package algorithms;
import java.util.*;
import ui.AnimationStep;

public class HeapVisualizer implements SortingAlgorithm {
    @Override
    public List<AnimationStep> sort(int[] arr) { return heapSort(arr); }

    public List<AnimationStep> heapSort(int[] arr) {
        List<AnimationStep> steps = new ArrayList<>();
        int n = arr.length;
        steps.add(AnimationStep.arrayStep(arr.clone(), -1, -1));
        for (int i = n/2 - 1; i >= 0; i--) heapify(arr, n, i, steps);
        for (int i = n-1; i > 0; i--) {
            swap(arr, 0, i);
            steps.add(AnimationStep.arrayStep(arr.clone(), 0, i));
            heapify(arr, i, 0, steps);
        }
        return steps;
    }

    private void heapify(int[] arr, int n, int i, List<AnimationStep> steps) {
        int l = 2*i+1, r = 2*i+2, largest = i;
        if (l < n) {
            steps.add(AnimationStep.arrayStep(arr.clone(), i, l));
            if (arr[l] > arr[largest]) largest = l;
        }
        if (r < n) {
            steps.add(AnimationStep.arrayStep(arr.clone(), largest, r));
            if (arr[r] > arr[largest]) largest = r;
        }
        if (largest != i) {
            swap(arr, i, largest);
            steps.add(AnimationStep.arrayStep(arr.clone(), i, largest));
            heapify(arr, n, largest, steps);
        }
    }

    private void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }
}
