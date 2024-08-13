package net.bodz.bas.c.loader.scan;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassFileMap
        extends LinkedHashMap<Class<?>, File> {

    private static final long serialVersionUID = 1L;

    public ClassFileMap subMap(File parent) {
        ClassFileMap subMap = new ClassFileMap();
        for (Map.Entry<Class<?>, File> entry : this.entrySet()) {
            File file = entry.getValue();
            if (isAncestor(file, parent))
                subMap.put(entry.getKey(), file);
        }
        return subMap;
    }

    public static boolean isAncestor(File ancestor, File child) {
        if (ancestor.equals(child))
            return true;
        File parent = child.getParentFile();
        if (parent == null)
            return false;
        else
            return isAncestor(ancestor, parent);
    }

}
