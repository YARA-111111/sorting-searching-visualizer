package datastructures;
import ui.AnimationStep;
import java.util.*;

public class BSTSearchResult {
    public List<BSTStep> steps;
    public BSTSearchResult(List<BSTStep> s) { steps = s; }
    public List<AnimationStep> toAnimationSteps() {
        List<AnimationStep> res = new ArrayList<>();
        for (BSTStep s : steps) res.add(AnimationStep.bstStep(s.currentNode.value, null));
        return res;
    }
}