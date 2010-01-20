package net.bodz.bas.db.filedb;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.iterator.IteratorX;

public abstract class _ResRecords<T>
        implements ResRecords<T> {

    protected final ResLink resLink;
    protected final Charset charset;

    protected _ResRecords(ResLink resLink) {
        this(resLink, null);
    }

    protected _ResRecords(ResLink resLink, Charset charset) {
        if (resLink == null)
            throw new NullPointerException("resLink");
        this.resLink = resLink;
        this.charset = charset;
    }

    @Override
    public ImmediateIteratorX<T, IOException> iterator(boolean allowOverlap)
            throws IOException {
        return null;
    }

    @Override
    public IteratorX<T, IOException> iterator() {
        ImmediateIteratorX<T, IOException> immIter = iterator(false);
        return new ImmIterIterator<T, IOException>(immIter);
    }

}
