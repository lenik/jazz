package net.bodz.bas.fs;

import java.io.IOException;

public class LazyProbeToolkit
        implements IProbeToolkit {

    protected final IFile file;

    public LazyProbeToolkit(IFile file) {
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
