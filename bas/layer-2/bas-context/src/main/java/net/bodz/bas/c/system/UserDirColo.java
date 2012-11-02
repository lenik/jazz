package net.bodz.bas.c.system;

import java.io.File;

import net.bodz.bas.context.ClassContextId;
import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.context.IContextId;

public class UserDirColo
        extends ContextLocal<File> {

    @Override
    public File getRoot() {
        String userDir = SystemProperties.getUserDir();
        File defaultUserDir = userDir == null ? new File(".") : new File(userDir);
        return defaultUserDir;
    }

    public static boolean isAbsolutePath(String path) {
        if (path == null)
            throw new NullPointerException("path");
        if (SystemInfo.isWin32())
            path = path.replace('\\', '/');
        if (path.startsWith("/"))
            return true;
        if (SystemInfo.isWin32()) {
            if (path.length() >= 2 && path.charAt(1) == ':') {
                String diskpath = path.substring(2);
                if (diskpath.startsWith("/"))
                    return true;
            }
        }
        return false;
    }

    public static boolean isRelativePath(String path) {
        return !isAbsolutePath(path);
    }

    static File join(File cwd, String name) {
        return new File(cwd, name);
    }

    public File join(IContextId context, String name) {
        if (name == null)
            throw new NullPointerException("name");
        File cwd = get(context);
        return join(cwd, name);
    }

    /**
     * @throws IllegalArgumentException
     *             If <code>dir</code> isn't a {@link File#isDirectory() directory}.
     */
    public void chdir(IContextId context, File dir) {
        if (dir == null)
            throw new NullPointerException("dir");
        if (!dir.isDirectory())
            throw new IllegalArgumentException("Not a directory: " + dir);
        set(context, dir);
    }

    /**
     * @param path
     *            Followed from current cwd, if <code>path</code> is relative.
     * @throws IllegalArgumentException
     *             If target <code>path</code> isn't a {@link File#isDirectory() directory}.
     */
    public void chdir(IContextId context, String path) {
        if (path == null)
            throw new NullPointerException("path");
        File cwd = get(context);
        if (isRelativePath(path))
            cwd = new File(cwd, path);
        else
            cwd = new File(path);
        chdir(context, cwd);
    }

    // Shortcuts for DefaultContext

    public File join(String name) {
        return join(getDefaultContext(), name);
    }

    public void chdir(File dir) {
        chdir(getDefaultContext(), dir);
    }

    public void chdir(String path) {
        chdir(getDefaultContext(), path);
    }

    // Shortcuts for ClassContext

    public File join(Class<?> classContext, String name) {
        return join(ClassContextId.getInstance(classContext), name);
    }

    public void chdir(Class<?> classContext, File dir) {
        chdir(ClassContextId.getInstance(classContext), dir);
    }

    public void chdir(Class<?> classContext, String path) {
        chdir(ClassContextId.getInstance(classContext), path);
    }

    private static final UserDirColo instance = new UserDirColo();

    public static UserDirColo getInstance() {
        return instance;
    }

}
