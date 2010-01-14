package net.bodz.bas.text.util;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.ImmediateIterableX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.iterator.IterableX;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.exceptions.NotImplementedException;

public class DelimitedTokenizer
        implements ImmediateIterableX<String, IOException>, IterableX<String, IOException> {

    private List<Delimiter> delimiters;

    private int state;
    private Reader in;

    public DelimitedTokenizer(Reader in, Delimiter... delimiters) {
        this(in, Arrays.asList(delimiters));
    }

    public DelimitedTokenizer(Reader in, List<Delimiter> delimiters) {
        if (in == null)
            throw new NullPointerException("in");
        if (delimiters == null)
            throw new NullPointerException("delimiters");
        this.delimiters = delimiters;
    }

    private static final int TD_OFFSET = 100;
    private static final int TD_SPAN = 100;
    private static final int TD_ESC_OFFSET;
    private static final int TD_MAXLEN;
    static {
        // 如 TD_SPAN 是奇数，ESC_OFFSET右侧的1个状态将是多余不用的。
        TD_ESC_OFFSET = TD_SPAN / 2;
        // 如 TD_SPAN 是奇数，MAXLEN已经少掉一个，因此不必为了安全再减去1。
        TD_MAXLEN = TD_SPAN / 2;
    }

    void next()
            throws IOException {
        int c;
        while ((c = in.read()) != -1) {

        }
    }

    class Iter
            extends AbstractImmediateIteratorX<String, IOException> {

        @Override
        public String next()
                throws IOException {
            throw new NotImplementedException();
            // return end();
        }

    }

    @Override
    public IteratorX<String, IOException> iterator() {
        return new ImmIterIterator<String, IOException>(new Iter());
    }

    @Override
    public ImmediateIteratorX<String, ? extends IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

}
