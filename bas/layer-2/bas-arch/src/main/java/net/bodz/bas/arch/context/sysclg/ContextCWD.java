package net.bodz.bas.arch.context.sysclg;

import java.io.File;

import net.bodz.bas.arch.context.ContextLocal;
import net.bodz.bas.arch.context.IContext;
import net.bodz.bas.arch.context.sysctx.ContextCWDTest;
import net.bodz.bas.sysinfo.SystemInfo;

/**
 * @test {@link ContextCWDTest}
 */
public class ContextCWD
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
        File cwd = get(null);
        if (isRelativePath(path))
            cwd = new File(cwd, path);
        else
            cwd = new File(path);
        chdir(context, cwd);
    }

    static final ContextCWD instance = new ContextCWD();

    public static ContextCWD getInstance() {
        return instance;
    }

}
