package net.bodz.bios.units.sinks;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

import net.bodz.bas.io.Files;

public class ChannelSink extends ByteProcessSink {

    private WritableByteChannel channel;
    private ByteBuffer buffer;

    public ChannelSink() {
        this(Files.blockSize);
    }

    public ChannelSink(int blockSize) {
        buffer = ByteBuffer.allocate(blockSize);
    }

    public WritableByteChannel getChannel() {
        return channel;
    }

    public void setChannel(WritableByteChannel channel) {
        this.channel = channel;
    }

    public int getBlockSize() {
        return buffer.capacity();
    }

    public void setBufferSize(int blockSize) {
        this.buffer = ByteBuffer.allocate(blockSize);
    }

    @Override
    public void reset() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void recv(byte[] bytes, int start, int end) throws IOException {
        int length = end - start;
        while (length > 0) {
            int block = Math.min(length, buffer.capacity());
            buffer.clear();
            buffer.put(bytes, start, block);
            buffer.flip();
            channel.write(buffer);
            start += block;
            length -= block;
        }
    }

}
