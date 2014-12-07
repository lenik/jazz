package net.bodz.bas.fmt.records;

import java.io.IOException;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.t.iterator.immed.AbstractMitablex;

public abstract class AbstractSeqRecords<T>
        extends AbstractMitablex<T, IOException>
        implements ISeqRecords<T> {

    protected final IStreamInputSource source;

    protected AbstractSeqRecords(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
    }

}