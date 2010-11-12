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

    private Charset preferredCharset = Charset.defaultCharset();

    public AbstractFile(IPath path) {
        super(null, path);
    }

    public AbstractFile(IVolume container, IPath path) {
        super(container, path);
    }

    @Override
    public abstract IFile clone();

    @Override
    public Charset getPreferredCharset() {
        return preferredCharset;
    }

    @Override
    public void setPreferredCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.preferredCharset = charset;
    }

    @Override
    public void setPreferredCharset(String charsetName) {
        if (charsetName == null)
            throw new NullPointerException("charsetName");
        setPreferredCharset(Charset.forName(charsetName));
    }

    /**
     * Implementation:
     * <p>
     * Returns <code>true</code>.
     */
    @Override
    public boolean isStream() {
        return true;
    }

    /**
     * Implementation:
     * <p>
     * Returns <code>false</code>.
     */
    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public IStreamReadPreparation forRead() {
        return new StreamReadPreparation(toSource());
    }

    @Override
    public IStreamWritePreparation forWrite() {
        return new StreamWritePreparation(toTarget());
    }

    @Override
    public ParseLoadPreparation forLoad() {
        return new ParseLoadPreparation(toSource());
    }

    @Override
    public FormatDumpPreparation forDump() {
        return new FormatDumpPreparation(toTarget());
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
        assert preferredCharset != null;
        hash += preferredCharset.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractFile))
            return false;
        AbstractFile o = (AbstractFile) obj;
        if (!preferredCharset.equals(o.preferredCharset))
            return false;
        return super.equals(o);
    }

}
