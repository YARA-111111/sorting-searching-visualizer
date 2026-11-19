package datastructures;

public class NodeInfo {
    public final int value;
    public final int parentIndex; // -1 root
    public final boolean isLeft; // true 如果是父节点的左孩子
    public final double x;
    public final double y;

    public NodeInfo(int value, int parentIndex, boolean isLeft, double x, double y) {
        this.value = value;
        this.parentIndex = parentIndex;
        this.isLeft = isLeft;
        this.x = x;
        this.y = y;
    }
}
