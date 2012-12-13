package net.bodz.bas.flow.unit.builtins;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.flow.StreamConfig;
import net.bodz.bas.flow.unit.GenericUnit_z1;
import net.bodz.bas.io.resource.builtin.ReaderSource;
import net.bodz.bas.io.resource.tools.IStreamReading;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.meta.codegen.GeneratedByCopyPaste;
import net.bodz.bas.t.iterator.immed.Mitorx;

public class ReaderSourceUnit
        extends GenericUnit_z1 {

    private final IStreamReading readPreparation;
    private final boolean allowOverlap;

    private Mitorx<char[], ? extends IOException> blocks;

    public ReaderSourceUnit(Reader in, boolean allowOverlap, int blockSize)
            throws IOException {
        this.readPreparation = new ReaderSource(in)//
                .tooling()._for(StreamReading.class).setBlockSize(blockSize);
        this.allowOverlap = allowOverlap;
        reset();
    }

    public ReaderSourceUnit(Reader in)
            throws IOException {
        this(in, true, StreamConfig.defaultBlockSize);
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
