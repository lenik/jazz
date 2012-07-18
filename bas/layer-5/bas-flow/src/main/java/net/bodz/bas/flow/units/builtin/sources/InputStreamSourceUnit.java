package net.bodz.bas.flow.units.builtin.sources;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.flow.units.SOSourceUnit;
import net.bodz.bas.flow.units.builtin.DefaultConfig;
import net.bodz.bas.io.resource.builtin.InputStreamSource;
import net.bodz.bas.io.resource.tools.IStreamReading;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.util.iter.Mitorx;

public class InputStreamSourceUnit
        extends SOSourceUnit {

    private final IStreamReading readPreparation;
    private final boolean allowOverlap;

    private Mitorx<byte[], ? extends IOException> blocks;

    public InputStreamSourceUnit(InputStream in, boolean allowOverlap, int blockSize)
            throws IOException {
        this.readPreparation = new InputStreamSource(in)//
                .tooling()._for(StreamReading.class).setBlockSize(blockSize);
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
        byte[] block = blocks._next(); // XXX - timeout
        if (block == null && blocks.isEnded())
            return false;
        send(block);
        return true;
    }

}
