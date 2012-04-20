package net.bodz.bas.io;

import java.io.File;

import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.context.ClassContext;
import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.context.DefaultContext;
import net.bodz.bas.context.IContext;

public class ContextDirectory
        extends ContextLocal<File> {

    @Override
    protected File getDefault() {
        String userDir = System.getProperty("user.dir");
        File defaultCwd = userDir == null ? new File(".") : new File(userDir);
        return defaultCwd;
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

    public File join(IContext context, String name) {
        if (context == null)
            throw new NullPointerException("context");
        if (name == null)
            throw new NullPointerException("name");
        File cwd = get(context);
        return join(cwd, name);
    }

    /**
     * @throws NullPointerException
     *             If <code>context</code> or <code>dir</code> is <code>null</code>.
     * @throws IllegalArgumentException
     *             If <code>dir</code> isn't a {@link File#isDirectory() directory}.
     */
    public void chdir(IContext context, File dir) {
        if (context == null)
            throw new NullPointerException("context");
        if (dir == null)
            throw new NullPointerException("dir");
        if (!dir.isDirectory())
            throw new IllegalArgumentException("Not a directory: " + dir);
        set(context, dir);
    }

    /**
     * @param path
     *            Followed from current cwd, if <code>path</code> is relative.
     * @throws NullPointerException
     *             If <code>context</code> or <code>path</code> is <code>null</code>.
     * @throws IllegalArgumentException
     *             If target <code>path</code> isn't a {@link File#isDirectory() directory}.
     */
    public void chdir(IContext context, String path) {
        if (context == null)
            throw new NullPointerException("context");
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
        return join(DefaultContext.getInstance(), name);
    }

    public void chdir(File dir) {
        chdir(DefaultContext.getInstance(), dir);
    }

    public void chdir(String path) {
        chdir(DefaultContext.getInstance(), path);
    }

    // Shortcuts for ClassContext

    public File join(Class<?> classContext, String name) {
        return join(ClassContext.getInstance(classContext), name);
    }

    public void chdir(Class<?> classContext, File dir) {
        chdir(ClassContext.getInstance(classContext), dir);
    }

    public void chdir(Class<?> classContext, String path) {
        chdir(ClassContext.getInstance(classContext), path);
    }

    private static final ContextDirectory instance = new ContextDirectory();

    public static ContextDirectory getInstance() {
        return instance;
    }

}
