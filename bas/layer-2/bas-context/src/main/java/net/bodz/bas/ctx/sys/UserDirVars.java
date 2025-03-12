package net.bodz.bas.ctx.sys;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.ScopedRef;
import net.bodz.bas.ctx.scope.Scopes;

public class UserDirVars
        extends ScopedRef<Path> {

    public UserDirVars() {
        super(Path.class);
    }

    @Override
    public Path getDefaultValue() {
        String userDir = SystemProperties.getUserDir();
        if (userDir == null)
            return Paths.get(".");
        else
            return Paths.get(userDir);
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

    @Override
    public void set(IScopeInstance scope, Path value) {
        super.set(scope, value);
    }

    public Path join(IScopeInstance scope, String name) {
        if (name == null)
            throw new NullPointerException("name");
        Path cwd = get(scope);
        return cwd.resolve(name);
    }

    /**
     * @throws IllegalArgumentException
     *             If <code>dir</code> isn't a {@link File#isDirectory() directory}.
     */
    public void chdir(IScopeInstance scope, Path dir) {
        if (dir == null)
            throw new NullPointerException("dir");
        if (!Files.isDirectory(dir))
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
        Path cwd = get(scope);
        if (isRelativePath(path))
            cwd = cwd.resolve(path);
        else
            cwd = Paths.get(path);
        chdir(scope, cwd);
    }

    // Shortcuts for DefaultContext

    public Path join(String name) {
        return join(getCurrentScope(), name);
    }

    public void chdir(Path dir) {
        chdir(getCurrentScope(), dir);
    }

    public void chdir(String path) {
        chdir(getCurrentScope(), path);
    }

    // Shortcuts for ClassContext

    public Path join(Class<?> classContext, String name) {
        return join(Scopes.from(classContext), name);
    }

    public void chdir(Class<?> classContext, Path dir) {
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
