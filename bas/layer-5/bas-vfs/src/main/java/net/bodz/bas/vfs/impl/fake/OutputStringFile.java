package net.bodz.bas.vfs.impl.fake;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.IStreamResource;
import net.bodz.bas.io.resource.builtin.StringBufferTarget;

public class OutputStringFile
        extends FakeFile {

    private StringBuffer buffer;

    public OutputStringFile() {
        this("(no name)");
    }

    public OutputStringFile(String name) {
        super(name);
        this.buffer = new StringBuffer();
    }

    @Override
    public OutputStringFile clone() {
        return new OutputStringFile(pathString).populate(this);
    }

    @Override
    protected OutputStringFile populate(Object obj) {
        super.populate(obj);
        if (obj instanceof OutputStringFile) {
            OutputStringFile o = (OutputStringFile) obj;
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
        long len = buffer.length();
        return len;
    }

    @Override
    public IStreamResource getResource(Charset charset) {
        throw new UnsupportedOperationException("Write-Only");
    }

    @Override
    public IStreamInputSource getInputSource(Charset charset) {
        throw new UnsupportedOperationException("Write-Only");
    }

    @Override
    public IStreamOutputTarget getOutputTarget(boolean appendMode, Charset charset) {
        StringBufferTarget target = new StringBufferTarget(buffer);
        target.setAppendMode(appendMode);
        target.setCharset(charset);
        return target;
    }

}
