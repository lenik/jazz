package net.bodz.bas.vfs.impl.fake;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.OutputStreamTarget;

public class OutputBytesFile
        extends FakeFile {

    private ByteArrayOutputStream buffer;

    public OutputBytesFile() {
        this("(no name)");
    }

    public OutputBytesFile(String name) {
        super(name);
        this.buffer = new ByteArrayOutputStream();
    }

    @Override
    public OutputBytesFile clone() {
        return new OutputBytesFile(getName()).populate(this);
    }

    @Override
    protected OutputBytesFile populate(Object obj) {
        super.populate(obj);
        if (obj instanceof OutputBytesFile) {
            OutputBytesFile o = (OutputBytesFile) obj;
            this.buffer = o.buffer;
        }
        return this;
    }

    @Override
    public Boolean exists() {
        return buffer != null;
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
    public boolean delete() {
        buffer = null;
        return true;
    }

    @Override
    public Long getLength() {
        if (buffer == null)
            return null;
        long size = buffer.size();
        return size;
    }

    @Override
    public IStreamResource getResource(Charset charset) {
        throw new UnsupportedOperationException("Write-Only");
    }

    /**
     * Use the written buffer as the input source.
     */
    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        throw new UnsupportedOperationException("Write-Only");
    }

    @Override
    public IStreamOutputTarget getOutputTarget(boolean appendMode, Charset charset) {
        OutputStreamTarget target = new OutputStreamTarget(buffer);
        target.setAppendMode(appendMode);
        target.setCharset(charset);
        return target;
    }

}
