package net.bodz.bas.db.filedb;

import java.io.IOException;

import net.bodz.bas.collection.iterator.AbstractIterableMX;
import net.bodz.bas.collection.iterator.IteratorM2X;
import net.bodz.bas.collection.iterator.IteratorTargetException;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.io.resource.IStreamInputSource;

public abstract class _ResRecords<T>
        extends AbstractIterableMX<T, IOException>
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
    public IteratorX<T, IOException> iteratorX() {
        try {
            return new IteratorM2X<T, IOException>(iterator(true));
        } catch (IOException e) {
            throw new IteratorTargetException(e.getMessage(), e);
        }
    }

}
