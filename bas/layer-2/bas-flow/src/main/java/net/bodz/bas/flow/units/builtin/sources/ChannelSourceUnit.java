package net.bodz.bas.flow.units.builtin.sources;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

import net.bodz.bas.flow.units.SOSourceUnit;
import net.bodz.bas.flow.units.builtin.DefaultConfig;

public class ChannelSourceUnit
        extends SOSourceUnit {

    private ReadableByteChannel channel;
    private ByteBuffer buffer;

    public ChannelSourceUnit() {
        this(DefaultConfig.defaultBlockSize);
    }

    public ChannelSourceUnit(int blockSize) {
        buffer = ByteBuffer.allocate(blockSize);
    }

    @Override
    public void reset()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public boolean pump(int timeout)
            throws IOException, InterruptedException {
        buffer.clear();
        int cb = channel.read(buffer);
        if (cb == -1)
            return false;
        buffer.flip();
        send(buffer);
        return true;
    }

}
