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

public class InputBytesFile
        extends AbstractFile {

    private final byte[] bytes;
    private String text;

    private long createdTime;
    private long modifiedTime;

    private IFolder parentFolder;

    public InputBytesFile(byte[] bytes) {
        this("(Unnamed)", bytes);
    }

    public InputBytesFile(String name, byte[] bytes) {
        super(name);
        if (bytes == null)
            throw new NullPointerException("bytes");
        this.bytes = bytes;
        createdTime = modifiedTime = System.currentTimeMillis();
    }

    @Override
    protected InputBytesFile clone()
            throws CloneNotSupportedException {
        InputBytesFile o = new InputBytesFile(getName(), bytes);
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
        if (text == null)
            text = new String(bytes, getCharset());
        return text;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public Long getLength() {
        return (long) bytes.length;
    }

    class ReadPreparation
            extends AbstractStreamReadPreparation {

        public ReadPreparation(IFile file) {
            super(InputBytesFile.this);
        }

        @Override
        public Reader newReader()
                throws IOException {
            String text = getText();
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
        return new ReadPreparation(InputBytesFile.this);
    }

    @Override
    public IStreamWritePreparation forWrite() {
        throw new UnsupportedOperationException();
    }

}
