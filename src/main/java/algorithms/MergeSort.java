package algorithms;
import java.util.*;
import ui.AnimationStep;

public class MergeSort implements SortingAlgorithm {
    private List<AnimationStep> steps;
    public List<AnimationStep> sort(int[] a) {
        steps = new ArrayList<>();
        int[] c = a.clone();
        steps.add(AnimationStep.arrayStep(c, -1, -1));
        mergeSort(c, 0, c.length-1);
        return steps;
    }
    private void mergeSort(int[] a, int l, int r) {
        if (l >= r) return;
        int m = (l+r)/2;
        mergeSort(a, l, m);
        mergeSort(a, m+1, r);
        merge(a, l, m, r);
    }
    private void merge(int[] a, int l, int m, int r) {
        int[] t = new int[r-l+1];
        int i=l, j=m+1, k=0;
        while (i<=m && j<=r) {
            record(a, i, j);
            if (a[i] <= a[j]) t[k++] = a[i++];
            else t[k++] = a[j++];
            record(a, i-1, j-1);
        }
        while (i<=m) { t[k++] = a[i++]; record(a, i-1, i-1); }
        while (j<=r) { t[k++] = a[j++]; record(a, j-1, j-1); }
        for (int x=0; x<t.length; x++) {
            a[l+x] = t[x];
            record(a, l+x, l+x);
        }
    }
    private void record(int[] a, int x, int y) {
        steps.add(AnimationStep.arrayStep(a, x, y));
    }
}