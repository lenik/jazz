package net.bodz.bios.units.sources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bios.units.SOSource;

public class InputStreamSource extends SOSource {

    Iterable<byte[]> blocks;
    Iterator<byte[]> blockIter;

    public InputStreamSource(InputStream in, int blockSize) throws IOException {
        blocks = Files.readByBlock(blockSize, in);
        reset();
    }

    public InputStreamSource(InputStream in) throws IOException {
        this(in, Files.blockSize);
    }

    @Override
    public void reset() throws IOException {
        blockIter = blocks.iterator();
    }

    @Override
    public void flush() throws IOException {
        // nothing to flush.
    }

    @Override
    public boolean pump(int timeout) throws IOException, InterruptedException {
        if (!blockIter.hasNext())
            return false;
        byte[] block = blockIter.next();
        send(block);
        return true;
    }

}
