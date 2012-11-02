package net.bodz.bas.vfs.context;

import java.io.File;

import net.bodz.bas.c.system.SystemColos;
import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.context.ClassContextId;
import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.context.IContextId;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;

public class WorkingDirColo
        extends ContextLocal<IFile> {

    @Override
    public IFile getRoot() {
        File systemWorkDir = SystemColos.workdir.get();
        return new JavaioFile(systemWorkDir);
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

    public IFile join(IContextId context, String name) {
        if (name == null)
            throw new NullPointerException("name");
        IFile cwd = get(context);
        return join(cwd, name);
    }

    /**
     * @throws IllegalArgumentException
     *             If <code>dir</code> isn't a {@link IFile#isDirectory() directory}.
     */
    public void chdir(IContextId context, IFile dir) {
        if (dir == null)
            throw new NullPointerException("dir");
        if (!dir.isTree())
            throw new IllegalArgumentException("Not a directory: " + dir);
        set(context, dir);
    }

    /**
     * @param path
     *            Followed from current cwd, if <code>path</code> is relative.
     * @throws IllegalArgumentException
     *             If target <code>path</code> isn't a {@link IFile#isDirectory() directory}.
     */
    public void chdir(IContextId context, String path) {
        if (path == null)
            throw new NullPointerException("path");
        IFile cwd = get(context);
        if (isRelativePath(path))
            cwd = cwd.getChild(path);
        else
            cwd = VFS.resolve(path);
        chdir(context, cwd);
    }

    // Shortcuts for DefaultContext

    public IFile join(String name) {
        return join(getDefaultContext(), name);
    }

    public void chdir(IFile dir) {
        chdir(getDefaultContext(), dir);
    }

    public void chdir(String path) {
        chdir(getDefaultContext(), path);
    }

    // Shortcuts for ClassContext

    public IFile join(Class<?> classContext, String name) {
        return join(ClassContextId.getInstance(classContext), name);
    }

    public void chdir(Class<?> classContext, IFile dir) {
        chdir(ClassContextId.getInstance(classContext), dir);
    }

    public void chdir(Class<?> classContext, String path) {
        chdir(ClassContextId.getInstance(classContext), path);
    }

    private static final WorkingDirColo instance = new WorkingDirColo();

    public static WorkingDirColo getInstance() {
        return instance;
    }

}
