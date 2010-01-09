package net.bodz.bas.fs;

import java.io.IOException;
import java.nio.charset.Charset;

public abstract class AbstractFile
        extends AbstractEntry
        implements IFile {

    private boolean deleteOnExit;
    private Charset charset = Charset.defaultCharset();

    public AbstractFile(IFolder parentFolder, String name) {
        super(parentFolder, name);
    }

    @Override
    public boolean isDeleteOnExit() {
        return deleteOnExit;
    }

    @Override
    public void setDeleteOnExit(boolean deleteOnExit) {
        this.deleteOnExit = deleteOnExit;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public void setCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.charset = charset;
    }

    @Override
    public String getMIMEType() {
        String extension = getExtension(false, 1);
        return MIMETypes.guessByExtension(extension);
    }

    /**
     * Treat inaccessible file as binary treat inaccessible file as binary
     * 
     * @return <code>true</code> if file is text like.
     */
    @Override
    public boolean isText()
            throws IOException {
        byte[] block;
        block = forRead().readBytes(TextOrBinary.textLookSize);
        return TextOrBinary.isText(block);
    }

    @Override
    public boolean isBinary()
            throws IOException {
        return !isText();
    }

    protected <T extends AbstractFile> T clone(T o) {
        o.charset = charset;
        o.deleteOnExit = deleteOnExit;
        return super.clone(o);
    }

}
