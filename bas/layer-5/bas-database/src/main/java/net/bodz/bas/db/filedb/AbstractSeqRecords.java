package net.bodz.bas.db.filedb;

import java.io.IOException;

import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.util.iter.AbstractMitablex;

public abstract class AbstractSeqRecords<T>
        extends AbstractMitablex<T, IOException>
        implements ISeqRecords<T> {

    protected final IStreamInputSource source;

    protected AbstractSeqRecords(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source.clone();
    }

}
