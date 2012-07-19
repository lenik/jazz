package net.bodz.bas.vfs.tools;

import java.io.IOException;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.util.ContentType;

public class LazyProbing
        implements IProbing {

    protected final IFile file;

    public LazyProbing(IFile file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public String getContentType()
            throws IOException {
        String extension = file.getPath().getExtension(false, 1);
        ContentType contentType = ContentType.getInstanceByExtension(extension);
        return contentType.getName();
    }

    @Override
    public boolean isText()
            throws IOException {
        return getContentType().startsWith("text/");
    }

    @Override
    public boolean isBinary()
            throws IOException {
        return !isText();
    }

}
