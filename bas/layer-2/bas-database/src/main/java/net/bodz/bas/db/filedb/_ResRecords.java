package net.bodz.bas.db.filedb;

import java.io.IOException;

import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.IteratorTargetException;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.io.resource.IStreamInputSource;

public abstract class _ResRecords<T>
        implements ResRecords<T> {

    protected final IStreamInputSource source;

    protected _ResRecords(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source.clone();
    }

    /**
     * @throws IteratorTargetException
     */
    @Override
    public IteratorX<T, IOException> iterator() {
        try {
            return new ImmIterIterator<T, IOException>(iterator(true));
        } catch (IOException e) {
            throw new IteratorTargetException(e.getMessage(), e);
        }
    }

}
