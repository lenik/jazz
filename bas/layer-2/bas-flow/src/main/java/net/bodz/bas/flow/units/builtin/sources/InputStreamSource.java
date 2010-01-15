package net.bodz.bas.flow.units.builtin.sources;

import java.io.IOException;
import java.io.InputStream;

import net.bodz.bas.collection.iterator.ImmediateIterableX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.flow.units.SOSource;
import net.bodz.bas.flow.units.builtin.DefaultConfig;

public class InputStreamSource
        extends SOSource {

    private ImmediateIterableX<byte[], IOException> blocks;
    private ImmediateIteratorX<byte[], IOException> blockIter;

    public InputStreamSource(InputStream in, int blockSize)
            throws IOException {
        blocks = Files.readByBlock(blockSize, in);
        reset();
    }

    public InputStreamSource(InputStream in)
            throws IOException {
        this(in, DefaultConfig.defaultBlockSize);
    }

    @Override
    public void reset()
            throws IOException {
        blockIter = blocks.iterator(true);
    }

    @Override
    public void flush()
            throws IOException {
        // nothing to flush.
    }

    @Override
    public boolean pump(int timeout)
            throws IOException, InterruptedException {
        byte[] block = blockIter.next(); // XXX - timeout
        if (block == null && blockIter.isEnded())
            return false;
        send(block);
        return true;
    }

}
