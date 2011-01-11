package net.bodz.bas.flow.units.builtin.sources;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.flow.units.SOSourceUnit;
import net.bodz.bas.flow.units.builtin.DefaultConfig;
import net.bodz.bas.io.resource.builtin.InputStreamSource;
import net.bodz.bas.io.resource.preparation.IStreamReadPreparation;

public class InputStreamSourceUnit
        extends SOSourceUnit {

    private final IStreamReadPreparation readPreparation;
    private final boolean allowOverlap;

    private ImmediateIteratorX<byte[], ? extends IOException> blocks;

    public InputStreamSourceUnit(InputStream in, boolean allowOverlap, int blockSize)
            throws IOException {
        this.readPreparation = new InputStreamSource(in).forRead().setBlockSize(blockSize);
        this.allowOverlap = allowOverlap;
        reset();
    }

    public InputStreamSourceUnit(InputStream in)
            throws IOException {
        this(in, true, DefaultConfig.defaultBlockSize);
    }

    @Override
    public void reset()
            throws IOException {
        blocks = readPreparation.byteBlocks(allowOverlap);
    }

    @Override
    public void flush()
            throws IOException {
        // nothing to flush.
    }

    @Override
    public boolean pump(int timeout)
            throws IOException, InterruptedException {
        byte[] block = blocks.next(); // XXX - timeout
        if (block == null && blocks.isEnded())
            return false;
        send(block);
        return true;
    }

}
