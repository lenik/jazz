package net.bodz.bas.vfs.context;

import java.io.File;

import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.ctx.scope.ScopedRef;
import net.bodz.bas.ctx.scope.Scopes;
import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.ctx.sys.UserDirScr;
import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.bas.vfs.path.BadPathException;

public class WorkingDirScr
        extends ScopedRef<IFile> {

    public WorkingDirScr() {
        super(IFile.class);
    }

    @Override
    public IFile getDefaultValue() {
        File systemWorkDir = UserDirScr.getInstance().get();
        return new PojfFile(systemWorkDir);
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

    static IFile join(IFile dir, String name) {
        IFile child = dir.getChild(name);
        return child;
    }

    public IFile join(IScopeDescriptor context, String name) {
        if (name == null)
            throw new NullPointerException("name");
        IFile cwd = get(context);
        return join(cwd, name);
    }

    /**
     * @throws IllegalArgumentException
     *             If <code>dir</code> isn't a {@link IFile#isDirectory() directory}.
     */
    public void chdir(IScopeDescriptor context, IFile dir) {
        if (dir == null)
            throw new NullPointerException("dir");
        if (!dir.getAttributes().isDirectory())
            throw new IllegalArgumentException("Not a directory: " + dir);
        set(context, dir);
    }

    /**
     * Change current workdir to the special path.
     * 
     * @param spec
     *            Followed from current cwd, if <code>spec</code> is relative.
     * @throws IllegalArgumentException
     *             If target <code>path</code> isn't a {@link IFile#isDirectory() directory}.
     * @throws BadPathException
     *             If the special path is illegal.
     * @throws FileResolveException
     *             If it's failed to resolve the special path.
     */
    public void chdir(IScopeDescriptor context, String spec) {
        if (spec == null)
            throw new NullPointerException("spec");
        IFile cwd = get(context);

        if (isRelativePath(spec))
            cwd = cwd.resolve(spec);
        else
            cwd = VFS.resolve(spec);

        chdir(context, cwd);
    }

    // Shortcuts for default context.

    public IFile join(String name) {
        return join(getCurrentScope(), name);
    }

    public void chdir(IFile dir) {
        chdir(getCurrentScope(), dir);
    }

    public void chdir(String path) {
        chdir(getCurrentScope(), path);
    }

    // Shortcuts for class context

    public IFile join(Class<?> classContext, String name) {
        return join(Scopes.from(classContext), name);
    }

    public void chdir(Class<?> classContext, IFile dir) {
        chdir(Scopes.from(classContext), dir);
    }

    public void chdir(Class<?> classContext, String path) {
        chdir(Scopes.from(classContext), path);
    }

    private static final WorkingDirScr instance = new WorkingDirScr();

    public static WorkingDirScr getInstance() {
        return instance;
    }

}
