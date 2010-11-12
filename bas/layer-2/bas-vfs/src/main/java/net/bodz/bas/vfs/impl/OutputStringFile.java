package net.bodz.bas.vfs.impl;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.StreamReadPreparation;
import net.bodz.bas.io.resource.preparation.StreamWritePreparation;
import net.bodz.bas.type.traits.IAttributes;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFolder;
import net.bodz.bas.vfs.path.IPath;

public class OutputStringFile
        extends AbstractFile {

    private IFolder parentFolder;
    private StringBuffer buffer;

    private long createdTime;
    private long modifiedTime;

    public OutputStringFile() {
        this("(unnamed)");
    }

    public OutputStringFile(String name) {
        super(name);
        this.buffer = new StringBuffer();
        this.createdTime = System.currentTimeMillis();
    }

    @Override
    protected OutputStringFile clone()
            throws CloneNotSupportedException {
        OutputStringFile o = new OutputStringFile(getName());
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
        int len = buffer.length();
        return len;
    }

    @Override
    public boolean isStream() {
        return true;
    }

    @Override
    public IAttributes getAttributes() {
        return null;
    }

    @Override
    public IPath getPath() {
        return null;
    }

    class ReadPreparation
            extends StreamReadPreparation {

        public ReadPreparation() {
            super(OutputStringFile.this);
        }

        @Override
        public InputStream newInputStream()
                throws IOException {
            if (buffer == null)
                throw new FileNotFoundException(getName());
            byte[] bytes = buffer.toString().getBytes(getPreferredCharset());
            return new ByteArrayInputStream(bytes);
        }

        @Override
        public Reader newReader()
                throws IOException {
            if (buffer == null)
                throw new FileNotFoundException(getName());
            String s = buffer.toString();
            return new StringReader(s);
        }

    }

    class WritePreparation
            extends StreamWritePreparation {

        public WritePreparation() {
            super(OutputStringFile.this);
        }

        @Override
        public OutputStream newOutputStream()
                throws IOException {
            if (buffer == null)
                buffer = new StringBuffer();
            boolean append = isAppendMode();
            if (!append)
                buffer.setLength(0);
            return null;
        }

        @Override
        public Writer newWriter()
                throws IOException {
            final StringBuffer buf = buffer;

            return new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len)
                        throws IOException {
                    buf.append(cbuf, off, len);
                }

                @Override
                public void flush()
                        throws IOException {
                }

                @Override
                public void close()
                        throws IOException {
                }
            };
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
