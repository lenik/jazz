package net.bodz.bas.ctx.sys;

import java.io.File;

import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.ScopedRef;
import net.bodz.bas.ctx.scope.Scopes;

public class UserDirVars
        extends ScopedRef<File> {

    public UserDirVars() {
        super(File.class);
    }

    @Override
    public File getDefaultValue() {
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

    public File join(IScopeInstance scope, String name) {
        if (name == null)
            throw new NullPointerException("name");
        File cwd = get(scope);
        return join(cwd, name);
    }

    /**
     * @throws IllegalArgumentException
     *             If <code>dir</code> isn't a {@link File#isDirectory() directory}.
     */
    public void chdir(IScopeInstance scope, File dir) {
        if (dir == null)
            throw new NullPointerException("dir");
        if (!dir.isDirectory())
            throw new IllegalArgumentException("Not a directory: " + dir);
        set(scope, dir);
    }

    /**
     * @param path
     *            Followed from current cwd, if <code>path</code> is relative.
     * @throws IllegalArgumentException
     *             If target <code>path</code> isn't a {@link File#isDirectory() directory}.
     */
    public void chdir(IScopeInstance scope, String path) {
        if (path == null)
            throw new NullPointerException("path");
        File cwd = get(scope);
        if (isRelativePath(path))
            cwd = new File(cwd, path);
        else
            cwd = new File(path);
        chdir(scope, cwd);
    }

    // Shortcuts for DefaultContext

    public File join(String name) {
        return join(getCurrentScope(), name);
    }

    public void chdir(File dir) {
        chdir(getCurrentScope(), dir);
    }

    public void chdir(String path) {
        chdir(getCurrentScope(), path);
    }

    // Shortcuts for ClassContext

    public File join(Class<?> classContext, String name) {
        return join(Scopes.from(classContext), name);
    }

    public void chdir(Class<?> classContext, File dir) {
        chdir(Scopes.from(classContext), dir);
    }

    public void chdir(Class<?> classContext, String path) {
        chdir(Scopes.from(classContext), path);
    }

    private static final UserDirVars instance = new UserDirVars();

    public static UserDirVars getInstance() {
        return instance;
    }

}
