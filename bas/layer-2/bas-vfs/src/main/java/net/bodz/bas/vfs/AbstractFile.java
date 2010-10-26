package net.bodz.bas.vfs;

import java.nio.charset.Charset;

import net.bodz.bas.io.resource.preparation.FormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.io.resource.preparation.ParseLoadPreparation;
import net.bodz.bas.io.resource.preparation.StreamReadPreparation;
import net.bodz.bas.io.resource.preparation.StreamWritePreparation;
import net.bodz.bas.vfs.path.IPath;
import net.bodz.bas.vfs.preparation.HeuristicProbePreparation;
import net.bodz.bas.vfs.preparation.IProbePreparation;
import net.bodz.bas.vfs.preparation.LazyProbePreparation;

public abstract class AbstractFile
        extends AbstractFsEntry
        implements IFile {

    private Charset charset = Charset.defaultCharset();
    private boolean deleteOnExit;

    public AbstractFile(IPath path) {
        super(null, path);
    }

    public AbstractFile(IFileContainer container, IPath path) {
        super(container, path);
    }

    @Override
    public abstract IFile clone();

    // protected <T extends AbstractFile> T clone(T o) {
    // o.charset = charset;
    // o.deleteOnExit = deleteOnExit;
    // return super.clone(o);
    // }

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
    public boolean isStream() {
        return false;
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
    public IStreamReadPreparation forRead() {
        return new StreamReadPreparation(asSource());
    }

    @Override
    public IStreamWritePreparation forWrite() {
        return new StreamWritePreparation(asTarget());
    }

    @Override
    public ParseLoadPreparation forLoad() {
        return new ParseLoadPreparation(asSource());
    }

    @Override
    public FormatDumpPreparation forDump() {
        return new FormatDumpPreparation(asTarget());
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
