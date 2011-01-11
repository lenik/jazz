package net.bodz.bas.vfs.preparation;

import java.io.IOException;

import net.bodz.bas.vfs.IFile;

public class LazyProbePreparation
        implements IProbePreparation {

    protected final IFile file;

    public LazyProbePreparation(IFile file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public String getMIMEType()
            throws IOException {
        String extension = file.getExtension(false, 1);
        return MIMETypes.guessByExtension(extension);
    }

    @Override
    public boolean isText()
            throws IOException {
        return getMIMEType().startsWith("text/");
    }

    @Override
    public boolean isBinary()
            throws IOException {
        return !isText();
    }

}
