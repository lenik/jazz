package net.bodz.bas.vfs.impl.javaio;

import java.io.File;

import net.bodz.bas.vfs.AbstractFileSystem;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.path.BadPathException;
import net.bodz.bas.vfs.path.DefaultPath;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.path.PathFormat;

public class JavaioFileSystem
        extends AbstractFileSystem {

    @Override
    public IFile[] getRootFiles() {
        File[] roots = File.listRoots();
        IFile[] rootFiles = new IFile[roots.length];
        for (int i = 0; i < roots.length; i++) {
            IFile rootFile = new JavaioFile(roots[i]);
            rootFiles[i] = rootFile;
        }
        return rootFiles;
    }

    @Override
    public IPath parse(String localPath)
            throws BadPathException {
        return new DefaultPath(this, localPath);
    }

    @Override
    public JavaioFile resolve(String localPath)
            throws BadPathException {
        return new JavaioFile(localPath);
    }

    @Override
    public String format(IPath path, PathFormat pathFormat) {
        String localPath = path.getLocalPath();
        return localPath;
    }

    private static final JavaioFileSystem instance = new JavaioFileSystem();

    public static JavaioFileSystem getInstance() {
        return instance;
    }

}
