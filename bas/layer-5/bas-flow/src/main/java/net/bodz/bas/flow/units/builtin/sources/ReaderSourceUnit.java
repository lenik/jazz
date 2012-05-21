package net.bodz.bas.flow.units.builtin.sources;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.flow.units.SOSourceUnit;
import net.bodz.bas.flow.units.builtin.DefaultConfig;
import net.bodz.bas.io.resource.builtin.ReaderSource;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;
import net.bodz.bas.meta.codehint.GeneratedByCopyPaste;
import net.bodz.bas.util.iter.Mitorx;

public class ReaderSourceUnit
        extends SOSourceUnit {

    private final IStreamReadPreparation readPreparation;
    private final boolean allowOverlap;

    private Mitorx<char[], ? extends IOException> blocks;

    public ReaderSourceUnit(Reader in, boolean allowOverlap, int blockSize)
            throws IOException {
        this.readPreparation = new ReaderSource(in).forRead().setBlockSize(blockSize);
        this.allowOverlap = allowOverlap;
        reset();
    }

    public ReaderSourceUnit(Reader in)
            throws IOException {
        this(in, true, DefaultConfig.defaultBlockSize);
    }

    @Override
    public void reset()
            throws IOException {
        blocks = readPreparation.charBlocks(allowOverlap);
    }

    @Override
    public void flush()
            throws IOException {
        // nothing to flush
    }

    /**
     * @seecopy {@link InputStreamSourceUnit#pump(int)}
     */
    @GeneratedByCopyPaste
    @Override
    public boolean pump(int timeout)
            throws IOException, InterruptedException {
        char[] block = blocks._next(); // XXX - timeout??
        if (block == null && blocks.isEnded())
            return false;
        send(block);
        return true;
    }

}
