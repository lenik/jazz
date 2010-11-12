package net.bodz.bas.vfs.impl;

import java.io.IOException;

import net.bodz.bas.io.resource.builtin.StringSource;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.StreamReadPreparation;
import net.bodz.bas.vfs.AbstractFile;
import net.bodz.bas.vfs.IFolder;

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
            bytes = text.getBytes(getPreferredCharset());
        return bytes;
    }

    @Override
    public long getLength() {
        int len = getBytes().length;
        return len;
    }

    @Override
    public IStreamReadPreparation forRead() {
        return new StreamReadPreparation(new StringSource(text));
    }

    @Override
    public IStreamWritePreparation forWrite() {
        throw new UnsupportedOperationException();
    }

}
