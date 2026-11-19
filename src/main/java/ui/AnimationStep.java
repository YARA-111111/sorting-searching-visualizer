package ui;

public class AnimationStep {
    private String type;
    private int[] arrayState;
    private Integer highlightA;
    private Integer highlightB;
    private Integer bstCurrent;
    private Integer bstVisited;

    /* ---------- 工厂方法 ---------- */
    public static AnimationStep arrayStep(int[] arr, int a, int b) {
        AnimationStep s = new AnimationStep();
        s.type = "array";
        s.arrayState = arr.clone();
        s.highlightA = a;
        s.highlightB = b;
        return s;
    }

    public static AnimationStep bstStep(int cur, Integer prev) {
        AnimationStep s = new AnimationStep();
        s.type = "bst";
        s.bstCurrent = cur;
        s.bstVisited = prev;
        return s;
    }

    /* ---------- getters ---------- */
    public String getType()          { return type; }
    public int[] getArrayState()     { return arrayState; }
    public Integer getHighlightA()   { return highlightA; }
    public Integer getHighlightB()   { return highlightB; }
    public Integer getBstCurrent()   { return bstCurrent; }
    public Integer getBstVisited()   { return bstVisited; }
}