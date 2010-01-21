package net.bodz.bas.fs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;

import net.bodz.bas.io.preparation.AbstractStreamReadPreparation;
import net.bodz.bas.io.preparation.IStreamReadPreparation;
import net.bodz.bas.io.preparation.IStreamWritePreparation;

public class InputStringFile
        extends AbstractFile {

    private final String text;
    private byte[] bytes;

    private long createdTime;
    private long modifiedTime;

    private IFolder parentFolder;

    public InputStringFile(String text) {
        this("(Unnamed)", text);
    }

    public InputStringFile(String name, String text) {
        super(name);
        if (text == null)
            throw new NullPointerException("text");
        this.text = text;
        createdTime = modifiedTime = System.currentTimeMillis();
    }

    @Override
    protected InputStringFile clone()
            throws CloneNotSupportedException {
        InputStringFile o = new InputStringFile(getName(), text);
        o.createdTime = createdTime;
        o.modifiedTime = modifiedTime;
        return o;
    }

    @Override
    public Boolean exists() {
        return true;
    }

    @Override
    public IFolder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(IFolder parentFolder) {
        this.parentFolder = parentFolder;
    }

    @Override
    public URI getURI() {
        return null;
    }

    @Override
    public URL getURL() {
        return null;
    }

    @Override
    public boolean isDeletable() {
        return false;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isFolder() {
        return false;
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isWritable() {
        return false;
    }

    @Override
    public boolean isExecutable() {
        return false;
    }

    @Override
    public Long getCreatedTime() {
        return createdTime;
    }

    @Override
    public Long getLastModifiedTime() {
        return modifiedTime;
    }

    @Override
    public void setLastModifiedTime(long date)
            throws IOException {
        this.modifiedTime = date;
    }

    @Override
    public boolean delete()
            throws IOException {
        return false;
    }

    public String getText() {
        return text;
    }

    public byte[] getBytes() {
        if (bytes == null)
            bytes = text.getBytes(getCharset());
        return bytes;
    }

    @Override
    public Long getLength() {
        int len = getBytes().length;
        return Long.valueOf(len);
    }

    class ReadPreparation
            extends AbstractStreamReadPreparation {

        public ReadPreparation(IFile file) {
            super(InputStringFile.this);
        }

        @Override
        public Reader newReader()
                throws IOException {
            return new StringReader(text);
        }

        @Override
        public InputStream newInputStream()
                throws IOException {
            byte[] bytes = getBytes();
            return new ByteArrayInputStream(bytes);
        }

    }

    @Override
    public IStreamReadPreparation forRead() {
        return new ReadPreparation(InputStringFile.this);
    }

    @Override
    public IStreamWritePreparation forWrite() {
        throw new UnsupportedOperationException();
    }

}
