package net.bodz.bas.fs;

import java.nio.charset.Charset;

public abstract class AbstractFile
        extends AbstractFsEntry
        implements IFile {

    private Charset charset = Charset.defaultCharset();
    private boolean deleteOnExit;

    public AbstractFile(String name) {
        super(name);
    }

    protected <T extends AbstractFile> T clone(T o) {
        o.charset = charset;
        o.deleteOnExit = deleteOnExit;
        return super.clone(o);
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public void setCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.charset = charset;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean isDeleteOnExit() {
        return deleteOnExit;
    }

    @Override
    public void setDeleteOnExit(boolean deleteOnExit) {
        this.deleteOnExit = deleteOnExit;
    }

    @Override
    public DefaultLoadToolkit forLoad() {
        return new DefaultLoadToolkit(forRead());
    }

    @Override
    public DefaultDumpToolkit forDump() {
        return new DefaultDumpToolkit(forWrite());
    }

    @Override
    public IProbeToolkit forProbe(boolean heuristic) {
        if (heuristic)
            return new HeuristicProbeToolkit(this);
        else
            return new LazyProbeToolkit(this);
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        assert charset != null;
        hash += charset.hashCode();
        if (deleteOnExit)
            hash += 0x8876588d;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractFile))
            return false;
        AbstractFile o = (AbstractFile) obj;
        if (deleteOnExit != o.deleteOnExit)
            return false;
        if (!charset.equals(o.charset))
            return false;
        return super.equals(o);
    }

}
