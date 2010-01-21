package net.bodz.bas.fs;

import java.io.File;
import java.nio.charset.Charset;

import net.bodz.bas.fs.preparation.HeuristicProbePreparation;
import net.bodz.bas.fs.preparation.IProbePreparation;
import net.bodz.bas.fs.preparation.LazyProbePreparation;
import net.bodz.bas.io.resource.preparation.FormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.ParseLoadPreparation;

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
    public void setCharset(String charsetName) {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        setCharset(Charset.forName(charsetName));
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

    @Override
    public ParseLoadPreparation forLoad() {
        return new ParseLoadPreparation(forRead());
    }

    @Override
    public FormatDumpPreparation forDump() {
        return new FormatDumpPreparation(forWrite());
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
