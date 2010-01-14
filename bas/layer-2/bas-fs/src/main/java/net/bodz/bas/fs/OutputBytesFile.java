package net.bodz.bas.fs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import net.bodz.bas.fs.preparation.AbstractStreamReadPreparation;
import net.bodz.bas.fs.preparation.AbstractStreamWritePreparation;
import net.bodz.bas.fs.preparation.IStreamReadPreparation;
import net.bodz.bas.fs.preparation.IStreamWritePreparation;

public class OutputBytesFile
        extends AbstractFile {

    private IFolder parentFolder;
    private ByteArrayOutputStream buffer;

    private long createdTime;
    private long modifiedTime;

    public OutputBytesFile() {
        this("(unnamed)");
    }

    public OutputBytesFile(String name) {
        super(name);
        this.buffer = new ByteArrayOutputStream();
        this.createdTime = System.currentTimeMillis();
    }

    @Override
    protected OutputBytesFile clone()
            throws CloneNotSupportedException {
        OutputBytesFile o = new OutputBytesFile(getName());
        o.createdTime = createdTime;
        o.modifiedTime = modifiedTime;
        o.buffer = buffer;
        return o;
    }

    @Override
    public Boolean exists() {
        return buffer != null;
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
        return true;
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
        return true;
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
        modifiedTime = date;
    }

    @Override
    public boolean delete()
            throws IOException {
        if (buffer == null)
            return false;
        buffer = null;
        return true;
    }

    @Override
    public Long getLength() {
        if (buffer == null)
            return null;
        return (long) buffer.size();
    }

    class ReadPreparation
            extends AbstractStreamReadPreparation {

        public ReadPreparation() {
            super(OutputBytesFile.this);
        }

        @Override
        public InputStream newInputStream()
                throws IOException {
            if (buffer == null)
                throw new FileNotFoundException(getName());
            byte[] bytes = buffer.toByteArray();
            return new ByteArrayInputStream(bytes);
        }

    }

    class WritePreparation
            extends AbstractStreamWritePreparation {

        public WritePreparation() {
            super(OutputBytesFile.this);
        }

        @Override
        public OutputStream newOutputStream()
                throws IOException {
            if (buffer == null)
                buffer = new ByteArrayOutputStream();
            boolean append = getAppendMode();
            if (!append)
                buffer.reset();
            return null;
        }

    }

    @Override
    public IStreamReadPreparation forRead() {
        return new ReadPreparation();
    }

    @Override
    public IStreamWritePreparation forWrite() {
        return new WritePreparation();
    }

}
