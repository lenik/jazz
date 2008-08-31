package net.bodz.bios.units.sources;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bios.units.SOSource;

public class ReaderSource extends SOSource {

    Iterable<char[]> blocks;
    Iterator<char[]> blockIter;

    public ReaderSource(Reader in, int blockSize) throws IOException {
        blocks = Files.readByLen(blockSize, in);
        reset();
    }

    public ReaderSource(Reader in) throws IOException {
        this(in, Files.blockSize);
    }

    @Override
    public void reset() throws IOException {
        blockIter = blocks.iterator();
    }

    @Override
    public void flush() throws IOException {
        // nothing to flush
    }

    @Override
    public boolean pump(int timeout) throws IOException, InterruptedException {
        if (!blockIter.hasNext())
            return false;
        char[] block = blockIter.next();
        send(block);
        return true;
    }

}
