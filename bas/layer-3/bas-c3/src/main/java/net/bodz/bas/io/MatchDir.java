package net.bodz.bas.io;

import java.io.File;
import java.util.Objects;

public class MatchDir
        implements
            IPathPattern {

    public static final int UNLIMIT = -1;

    final String startDir;
    final int startDirLen;
    final int maxDepth;

    public MatchDir(String startDir) {
        this(startDir, UNLIMIT);
    }

    public MatchDir(String startDir, int maxDepth) {
        if (startDir == null)
            throw new NullPointerException("startDir");

        while (startDir.endsWith("/"))
            startDir = startDir.substring(0, startDir.length() - 1);

        this.startDir = startDir;
        this.startDirLen = startDir == null ? 0 : startDir.length();
        this.maxDepth = maxDepth;
    }

    @Override
    public boolean accept(File pathname) {
        return accept(pathname.getPath());
    }

    @Override
    public boolean accept(File dir, String name) {
        return accept(dir.getPath(), name);
    }

    public boolean accept(String path) {
        return inStartDir(path);
    }

    public boolean accept(String dir, String name) {
        return inStartDir(dir);
    }

    boolean inStartDir(String path) {
        if (path.startsWith(startDir)) {
            int pathLen = path.length();
            if (pathLen == startDirLen) // equals
                return true;
            if (path.charAt(pathLen) == '/') { // dir_.startsWith
                if (maxDepth == UNLIMIT)
                    return true;
                int nSlash = count('/', path, pathLen + 1, path.length());
                if (maxDepth >= nSlash)
                    return true;
            }
        }
        return false;
    }

    static int count(char ch, String s) {
        return count(ch, s, 0, s.length());
    }

    static int count(char ch, String s, int start, int end) {
        int n = 0;
        for (int i = start; i < end; i++)
            if (s.charAt(i) == ch)
                n++;
        return n;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxDepth, startDir);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MatchDir other = (MatchDir) obj;
        return maxDepth == other.maxDepth && Objects.equals(startDir, other.startDir);
    }

    @Override
    public String toString() {
        return "MatchDir [startDir=" + startDir + ", maxDepth=" + maxDepth + "]";
    }

}
