package net.bodz.bas.files;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.io.ResLink;
import net.bodz.bas.types.util.DirectIterator;
import net.bodz.bas.types.util.Iterates;

public abstract class _ResRecords<T> implements ResRecords<T> {

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

    public abstract DirectIterator<? extends T, IOException> iterator();

    @Override
    public DirectIterator<? extends T, IOException> iterator(boolean allowOverlap) {
        return iterator();
    }

    public Iterable<? extends T> iterate() {
        return Iterates.iterate(this);
    }

}
