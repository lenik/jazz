package net.bodz.bas.c.java.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileRelation {

    public static Path getCommonParentFile(Path... files) {
        if (files.length == 0)
            return null;
        Path base = files[0];
        for (int i = 1; i < files.length; i++) {
            base = getCommonParentFile(base, files[i]);
            if (base == null)
                return null;
        }
        return base;
    }

    public static Path getCommonParentFile(Path a, Path b) {
        if (a == null || b == null)
            return null;
        if (a.equals(b))
            return a;
        final Path _a = a.getParent();
        for (Path i = a; i != null; i = i.getParent())
            if (i.equals(b))
                return b;
        final Path _b = b.getParent();
        for (Path i = b; i != null; i = i.getParent())
            if (i.equals(a))
                return a;
        return getCommonParentFile(_a, _b);
    }

    public static File getCommonParentFile(File... files) {
        if (files.length == 0)
            return null;
        File base = files[0];
        for (int i = 1; i < files.length; i++) {
            base = getCommonParentFile(base, files[i]);
            if (base == null)
                return null;
        }
        return base;
    }

    public static File getCommonParentFile(File a, File b) {
        if (a == null || b == null)
            return null;
        if (a.equals(b))
            return a;
        final File _a = a.getParentFile();
        for (File i = a; i != null; i = i.getParentFile())
            if (i.equals(b))
                return b;
        final File _b = b.getParentFile();
        for (File i = b; i != null; i = i.getParentFile())
            if (i.equals(a))
                return a;
        return getCommonParentFile(_a, _b);
    }

    public static boolean isTrueChildOf(Path child, Path parent) {
        if (child == null)
            throw new NullPointerException("child");
        if (parent == null)
            throw new NullPointerException("parent");

        if (child.equals(parent))
            return true;

        return isChildOf(child, parent);
    }

    public static boolean isTrueChildOf(File child, File parent) {
        if (child == null)
            throw new NullPointerException("child");
        if (parent == null)
            throw new NullPointerException("parent");

        if (child.equals(parent))
            return true;

        return isChildOf(child, parent);
    }

    public static boolean isChildOf(Path child, Path parent) {
        if (child == null)
            throw new NullPointerException("child");
        if (parent == null)
            throw new NullPointerException("parent");

        if (!Files.isDirectory(parent))
            return false;

        child = child.toAbsolutePath();
        parent = parent.toAbsolutePath();
        return _isChildOf(child, parent);
    }

    public static boolean isChildOf(File child, File parent) {
        if (child == null)
            throw new NullPointerException("child");
        if (parent == null)
            throw new NullPointerException("parent");

        if (!parent.isDirectory())
            return false;

        child = child.getAbsoluteFile();
        parent = parent.getAbsoluteFile();
        return _isChildOf(child, parent);
    }

    private static boolean _isChildOf(Path child, Path parent) {
        Path f = child.getParent();
        if (f.equals(parent))
            return true;
        return _isChildOf(f, parent);
    }

    private static boolean _isChildOf(File child, File parent) {
        File f = child.getParentFile();
        if (f.equals(parent))
            return true;
        return _isChildOf(f, parent);
    }

}
