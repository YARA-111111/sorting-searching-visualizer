package datastructures;

import ui.AnimationStep;
import java.util.*;

public class BSTVisualizer {

    public static class TreeNode {
        int val;
        TreeNode l, r;
        TreeNode(int v) { val = v; }
    }

    private TreeNode root;

    public void insert(int v) {
        root = insert(root, v);
    }

    private TreeNode insert(TreeNode n, int v) {
        if (n == null) return new TreeNode(v);
        if (v < n.val) n.l = insert(n.l, v);//go left
        else if (v > n.val) n.r = insert(n.r, v);//go right
        return n;
    }

    public List<AnimationStep> searchSteps(int target) {
        List<AnimationStep> list = new ArrayList<>();
        search(root, target, list, null);
        return list;
    }

    private TreeNode search(TreeNode n, int t, List<AnimationStep> list, Integer prev) {
        if (n == null) return null;
        list.add(AnimationStep.bstStep(n.val, prev));
        if (n.val == t) return n;
        if (t < n.val) return search(n.l, t, list, n.val);//go left
        else return search(n.r, t, list, n.val);//go right
    }
}
