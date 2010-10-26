package net.bodz.bas.vfs;

import java.io.IOException;

import net.bodz.bas.exceptions.ReadOnlyException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.builtin.ByteArrayResource;

public class InputBytesFile
        extends AbstractFile {

    private final byte[] bytes;
    private String text;

    private long createdTime;
    private long modifiedTime;

    private IFsFolderEntry parentFolder;

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
    public IFsFolderEntry getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(IFsFolderEntry parentFolder) {
        this.parentFolder = parentFolder;
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
    public long getLength() {
        return bytes.length;
    }

    @Override
    public IStreamInputSource asSource() {
        return new ByteArrayResource(bytes);
    }

    @Override
    public IStreamOutputTarget asTarget() {
        // return new ByteArrayResource(bytes);
        throw new ReadOnlyException();
    }

}
