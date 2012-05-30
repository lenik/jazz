package net.bodz.bas.vfs.preparation;

import java.io.IOException;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.util.ContentType;

public class LazyProbePreparation
        implements IProbePreparation {

    protected final IFile file;

    public LazyProbePreparation(IFile file) {
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
