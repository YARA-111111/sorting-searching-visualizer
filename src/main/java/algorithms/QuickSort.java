package algorithms;
import java.util.*;
import ui.AnimationStep;

public class QuickSort implements SortingAlgorithm {
    private List<AnimationStep> steps;
    public List<AnimationStep> sort(int[] a) {
        steps = new ArrayList<>();
        int[] c = a.clone();
        steps.add(AnimationStep.arrayStep(c, -1, -1));
        quickSort(c, 0, c.length-1);
        return steps;
    }
    private void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }
    }
    private int partition(int[] a, int lo, int hi) {
        int piv = a[hi], i = lo-1;
        record(a, hi, hi);
        for (int j=lo; j<hi; j++) {
            record(a, j, hi);
            if (a[j] <= piv) {
                i++;
                record(a, i, j);
                swap(a, i, j);
                record(a, i, j);
            }
        }
        record(a, i+1, hi);
        swap(a, i+1, hi);
        record(a, i+1, hi);
        return i+1;
    }
    private void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }
    private void record(int[] a, int x, int y) {
        steps.add(AnimationStep.arrayStep(a, x, y));
    }
}