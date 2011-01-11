package net.bodz.bas.vfs.impl.fake;

import java.io.ByteArrayOutputStream;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.ByteArrayResource;
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

    /**
     * Use the written buffer as the input source.
     */
    @Override
    public IStreamInputSource toSource() {
        byte[] byteArray = buffer.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(byteArray);
        return resource;
    }

    @Override
    public IStreamOutputTarget toTarget() {
        return new OutputStreamTarget(buffer);
    }

}
