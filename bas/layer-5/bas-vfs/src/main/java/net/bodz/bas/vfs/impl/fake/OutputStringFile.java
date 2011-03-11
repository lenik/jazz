package net.bodz.bas.vfs.impl.fake;

public class OutputStringFile
        extends FakeFile {

    private StringBuilder buffer;

    public OutputStringFile() {
        this("(no name)");
    }

    public OutputStringFile(String name) {
        super(name);
        this.buffer = new StringBuilder();
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

}
