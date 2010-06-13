package net.bodz.bas.fs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.StreamReadPreparation;
import net.bodz.bas.io.resource.preparation.StreamWritePreparation;

public class OutputBytesFile
        extends AbstractFile {

    private IFsFolderEntry parentFolder;
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
    public IFsFolderEntry getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(IFsFolderEntry parentFolder) {
        this.parentFolder = parentFolder;
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
    public long getLength() {
        if (buffer == null)
            return 0L;
        return buffer.size();
    }

    @Override
    public boolean isStream() {
        return true;
    }

    class ReadPreparation
            extends StreamReadPreparation {

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
            extends StreamWritePreparation {

        public WritePreparation() {
            super(OutputBytesFile.this);
        }

        @Override
        public OutputStream newOutputStream()
                throws IOException {
            if (buffer == null)
                buffer = new ByteArrayOutputStream();
            boolean append = isAppendMode();
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
