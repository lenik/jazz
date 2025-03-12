package net.bodz.bas.c.loader.scan;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassFileMap
        extends LinkedHashMap<Class<?>, Path> {

    private static final long serialVersionUID = 1L;

    public ClassFileMap subMap(Path parent) {
        ClassFileMap subMap = new ClassFileMap();
        for (Map.Entry<Class<?>, Path> entry : this.entrySet()) {
            Path file = entry.getValue();
            if (isAncestor(file, parent))
                subMap.put(entry.getKey(), file);
        }
        return subMap;
    }

    public static boolean isAncestor(Path ancestor, Path child) {
        if (ancestor.equals(child))
            return true;
        Path parent = child.getParent();
        if (parent == null)
            return false;
        else
            return isAncestor(ancestor, parent);
    }

}
