package ui.panes;

import java.util.HashMap;
import java.util.Map;

public class BSTConnections {

    private static Map<String, Object> map = new HashMap<>();

    public static void link(Object parent, Object child, String side) {
        map.put(parent + "-" + side, child);
    }

    public static Object get(Object parent, String side) {
        return map.get(parent + "-" + side);
    }

    public static void clear() {
        map.clear();
    }
}
