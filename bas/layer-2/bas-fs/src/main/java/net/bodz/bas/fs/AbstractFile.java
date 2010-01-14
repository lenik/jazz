package net.bodz.bas.fs;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;

import net.bodz.bas.fs.preparation.DefaultFormatDumpPreparation;
import net.bodz.bas.fs.preparation.DefaultParseLoadPreparation;
import net.bodz.bas.fs.preparation.HeuristicProbePreparation;
import net.bodz.bas.fs.preparation.IProbePreparation;
import net.bodz.bas.fs.preparation.LazyProbePreparation;

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

    /**
     * Default to create a temporary file??
     */
    @Override
    public File getFile() {
        return null;
    }

    /**
     * Default return the path of corresponding {@link #getFile() File}.
     * 
     * @return {@link Path}
     */
    @Override
    public Path getPath() {
        return getFile().toPath();
    }

    @Override
    public DefaultParseLoadPreparation forLoad() {
        return new DefaultParseLoadPreparation(forRead());
    }

    @Override
    public DefaultFormatDumpPreparation forDump() {
        return new DefaultFormatDumpPreparation(forWrite());
    }

    @Override
    public IProbePreparation forProbe(boolean heuristic) {
        if (heuristic)
            return new HeuristicProbePreparation(this);
        else
            return new LazyProbePreparation(this);
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
