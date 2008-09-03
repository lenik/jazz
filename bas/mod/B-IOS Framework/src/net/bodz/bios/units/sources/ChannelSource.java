package net.bodz.bios.units.sources;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

import net.bodz.bas.io.Files;
import net.bodz.bios.units.SOSource;

public class ChannelSource extends SOSource {

    private ReadableByteChannel channel;
    private ByteBuffer          buffer;

    public ChannelSource() {
        this(Files.blockSize);
    }

    public ChannelSource(int blockSize) {
        buffer = ByteBuffer.allocate(blockSize);
    }

    @Override
    public void reset() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public boolean pump(int timeout) throws IOException, InterruptedException {
        buffer.clear();
        int cb = channel.read(buffer);
        if (cb == -1)
            return false;
        buffer.flip();
        send(buffer);
        return true;
    }

}
