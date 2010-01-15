package net.bodz.bas.flow.units.builtin.sources;

import java.io.IOException;
import java.io.Reader;

import net.bodz.bas.collection.iterator.ImmediateIterableX;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.flow.units.SOSource;
import net.bodz.bas.flow.units.builtin.DefaultConfig;

public class ReaderSource
        extends SOSource {

    private ImmediateIterableX<char[], IOException> blocks;
    private ImmediateIteratorX<char[], IOException> blockIter;

    public ReaderSource(Reader in, int blockSize)
            throws IOException {
        blocks = Files.readByLen(blockSize, in);
        reset();
    }

    public ReaderSource(Reader in)
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
        // nothing to flush
    }

    @Override
    public boolean pump(int timeout)
            throws IOException, InterruptedException {
        char[] block = blockIter.next(); // XXX - timeout??
        if (block == null && blockIter.isEnded())
            return false;
        send(block);
        return true;
    }

}
