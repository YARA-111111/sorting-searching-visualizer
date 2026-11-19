package ui.panes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ui.AnimationController;
import ui.AnimationStep;

import java.util.HashMap;
import java.util.Map;

public class BSTPane extends Pane implements AnimationController.AnimationPane {

    private static class VNode {
        int val;
        double x, y;
        Circle circle;
        Text label;
        VNode left, right;
        VNode(int v) { val = v; }
    }

    private VNode root;
    private final double R = 18, DX = 60, DY = 70, OX = 40, OY = 50;
    private final Map<Integer, VNode> map = new HashMap<>();

    public BSTPane() {
        setPrefSize(700, 400);
    }

    /** 使用 BSTVisualizer 构建可视化 */
    public void setTreeFromVisualizer(datastructures.BSTVisualizer bst) {
        map.clear();
        getChildren().clear();
        root = buildVNodeTree(bst);
        layoutTree(root, 0, new int[]{0});
        drawTree(root); // ✅ 一开始就绘制树节点和边
    }

    @Override
    public void renderStep(AnimationStep step) {
        if (!"bst".equals(step.getType())) return;

        resetColors(); // just change the color
        if (step.getBstVisited() != null) highlight(step.getBstVisited(), Color.ORANGE);
        if (step.getBstCurrent() != null) highlight(step.getBstCurrent(), Color.RED);
    }

    @Override
    public void reset() {
        map.values().forEach(v -> v.circle.setFill(Color.LIGHTBLUE));
    }

    /* ---------- 私有方法 ---------- */
    private VNode buildVNodeTree(datastructures.BSTVisualizer bst) {
        if (bst == null) return null;
        try {
            java.lang.reflect.Field f = bst.getClass().getDeclaredField("root");
            f.setAccessible(true);
            Object bstRoot = f.get(bst);
            return buildVNode(bstRoot);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private VNode buildVNode(Object treeNode) {
        if (treeNode == null) return null;
        try {
            java.lang.reflect.Field valF = treeNode.getClass().getDeclaredField("val");
            java.lang.reflect.Field lF = treeNode.getClass().getDeclaredField("l");
            java.lang.reflect.Field rF = treeNode.getClass().getDeclaredField("r");
            valF.setAccessible(true);
            lF.setAccessible(true);
            rF.setAccessible(true);

            int val = (int) valF.get(treeNode);
            Object l = lF.get(treeNode);
            Object r = rF.get(treeNode);

            VNode n = new VNode(val);
            n.circle = new Circle(R);
            n.label = new Text(String.valueOf(val));

            n.left = buildVNode(l);
            n.right = buildVNode(r);

            map.put(val, n);
            return n;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void layoutTree(VNode n, int depth, int[] idx) {
        if (n == null) return;
        layoutTreeLeftRight(n, depth, idx);
    }

    private void layoutTreeLeftRight(VNode n, int depth, int[] idx) {
        if (n == null) return;
        layoutTreeLeftRight(n.left, depth + 1, idx);
        n.x = OX + idx[0] * DX;
        n.y = OY + depth * DY;
        idx[0]++;
        layoutTreeLeftRight(n.right, depth + 1, idx);
    }

    private void drawTree(VNode n) {
        if (n == null) return;
        drawTreeEdges(n);
        drawTreeNodes(n);
    }

    private void drawTreeEdges(VNode n) {
        if (n.left != null) {
            Line l = new Line(n.x, n.y, n.left.x, n.left.y);
            l.setStroke(Color.GRAY);
            l.setStrokeWidth(2);
            getChildren().add(l);
            drawTreeEdges(n.left);
        }
        if (n.right != null) {
            Line l = new Line(n.x, n.y, n.right.x, n.right.y);
            l.setStroke(Color.GRAY);
            l.setStrokeWidth(2);
            getChildren().add(l);
            drawTreeEdges(n.right);
        }
    }

    private void drawTreeNodes(VNode n) {
        n.circle.setCenterX(n.x);
        n.circle.setCenterY(n.y);
        n.circle.setFill(Color.LIGHTBLUE);

        n.label.setFont(Font.font(12));
        n.label.setX(n.x - 7);
        n.label.setY(n.y + 5);

        getChildren().addAll(n.circle, n.label);

        if (n.left != null) drawTreeNodes(n.left);
        if (n.right != null) drawTreeNodes(n.right);
    }

    private void resetColors() {
        for (VNode n : map.values()) {
            n.circle.setFill(Color.LIGHTBLUE);
        }
    }

    private void highlight(int val, Color color) {
        VNode n = map.get(val);
        if (n != null) n.circle.setFill(color);
    }
}
