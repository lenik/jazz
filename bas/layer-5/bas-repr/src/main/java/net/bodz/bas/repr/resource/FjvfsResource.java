package net.bodz.bas.repr.resource;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.vfs.FileResolveException;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;

public class FjvfsResource
        extends AbstractResource {

    private final String path;
    private final IFile file;

    public FjvfsResource(String path)
            throws FileResolveException {
        if (path == null)
            throw new NullPointerException("path");
        this.path = path;
        this.file = VFS.resolveFile(path);
    }

    @Override
    public InputStream openBinary()
            throws IOException {
        return file.getInputSource().newInputStream();
    }

    @Override
    public String getPath() {
        String path = file.getPath().toString();

        if (path != null)
            return file.getPath().toString();
        return null;
    }

}
