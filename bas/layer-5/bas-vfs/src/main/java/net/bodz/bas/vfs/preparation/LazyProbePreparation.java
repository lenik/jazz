package net.bodz.bas.vfs.preparation;

import java.io.IOException;

import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.util.MIMEType;

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
        String extension = file.getExtension(false, 1);
        MIMEType mimeType = MIMEType.getInstanceByExtension(extension);
        return mimeType.getContentType();
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
