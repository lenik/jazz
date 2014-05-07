package net.bodz.bas.t.tree;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public interface IPathInfo {

    IPathInfo getParent();

    String getLocalPath();

    // String[] getPathTokens();

    class fn {

        static final PathStrBuilder absolutePathBuilder = new PathStrBuilder();

        public static String getAbsolutePath(IPathInfo obj) {
            return absolutePathBuilder.buildPath(obj);
        }

        public static String getFullPath(IPathInfo obj) {
            String path = absolutePathBuilder.buildPath(obj);
            if (path.startsWith("/"))
                path = path.substring(1);
            return path;
        }

        public static String getPathFrom(IPathInfo obj, IPathInfo current) {
            PathStrBuilder builder = new PathStrBuilder(current);
            return builder.buildPath(obj);
        }

    }

}

class PathStrBuilder {

    private final Map<IPathInfo, Integer> levelMap;

    public PathStrBuilder() {
        levelMap = null;
    }

    public PathStrBuilder(IPathInfo current) {
        this.levelMap = new IdentityHashMap<IPathInfo, Integer>();
        int level = 0;
        while (current != null) {
            levelMap.put(current, level);
            current = current.getParent();
            level++;
        }
    }

    public String buildPath(IPathInfo obj) {
        Integer level = null;
        List<String> list = new ArrayList<String>();
        int len = 0;
        while (obj != null) {
            if (levelMap != null) {
                level = levelMap.get(obj);
                if (level != null) {
                    len += level * 3;
                    break;
                }
            }

            String localPath = obj.getLocalPath();
            if (localPath != null) {
                list.add(localPath);
                len += localPath.length();
                len++;
            }

            obj = obj.getParent();
        }

        StringBuilder buf = new StringBuilder(len);

        if (level == null)
            buf.append('/');
        else {
            for (int i = level; i > 0; i--) {
                buf.append("..");
                if (i > 1)
                    buf.append('/');
            }
            if (level > 0 && !list.isEmpty())
                buf.append('/');
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            buf.append(list.get(i));
            if (i > 0)
                buf.append('/');
        }

        if (buf.length() == 0)
            return ".";
        else
            return buf.toString();
    }

}
