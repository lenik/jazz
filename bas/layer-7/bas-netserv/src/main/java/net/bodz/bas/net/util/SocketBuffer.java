package net.bodz.bas.net.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.io.ISocketWriter;

public class SocketBuffer
        extends SmartBuffer
        implements ISocketReader,
                   ISocketWriter {

    static final Logger logger = LoggerFactory.getLogger(SocketBuffer.class);

    final SocketChannel channel;
    final ISocketPoller poller;

    boolean readStarted;

    CloseSupport closeSupport = new CloseSupport(this);

    public SocketBuffer(SocketChannel channel, ISocketPoller poller) {
        this.channel = channel;
        this.poller = poller;
        addReadStoppedListener(event -> {
        });
    }

    String getLogPrefix() {
        StringBuilder buf = new StringBuilder(40);
        String info = SocketChannels.getConnectionShortInfo(channel);
        buf.append(info);
        if (info.contains(SocketChannels.ARROW))
            buf.append("…");
        buf.append("  ");
        return buf.toString();
    }

    public void startRead()
            throws IOException {
        if (!readStarted) {
            poller.registerRead(channel, this);
            readStarted = true;
        }
    }

    @Override
    protected void stopReadImpl() {
        if (readStarted) {
            poller.cancelRead(channel, this);
            readStarted = false;
        }
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        String info = SocketChannels.getConnectionShortInfo(channel);
        logger.debug(info + "read");
        if (channel.socket().isClosed()) {
            logger.debug("socket is closed");
            detectClosed();
            return 0;
        }

        if (readStopped)
            return 0;

        ByteBuffer byteBuf = ByteBuffer.allocate(4096);
        long totalBytesRead = 0;
        while (!readStopped) {
            int numBytesRead = channel.read(byteBuf);
            logger.debug("    read " + numBytesRead + " bytes");

            switch (numBytesRead) {
                case -1:
                    // the channel input has reached end-of-stream
                    // but you can still write to the channel.. ?
                    // channel.socket().close();
                    stopRead();

                case 0:
                    return totalBytesRead;
                default:
                    totalBytesRead += numBytesRead;
            }

            byteBuf.flip();
            read(byteBuf);
            byteBuf.clear();
        }
        return totalBytesRead;
    }

    public void enableWriter()
            throws IOException {
        logger.debug(getLogPrefix() + "enable writer");
        poller.registerWrite(channel, (ISocketWriter) this);
    }

    public void disableWriter() {
        logger.debug(getLogPrefix() + "disable writer");
        poller.cancelWrite(channel);
    }

    @Override
    public long write(@NotNull SocketChannel channel)
            throws IOException {
        int length = writeBuffer.length();
        if (length == 0) {
            disableWriter();
            return 0;
        }

        byte[] backedArray = writeBuffer.getBackedArray();
        int backedArrayOffset = writeBuffer.getBackedArrayOffset();
        logger.debug("write available " + length);

        ByteBuffer buf = ByteBuffer.wrap(backedArray, backedArrayOffset, length);
        int numBytesWritten = channel.write(buf);
        logger.debug("    " + numBytesWritten + " written");
        writeBuffer.delete(0, numBytesWritten);

        return numBytesWritten;
    }

    void detectClosed() {
        stopRead();
        closeSupport.fireClose(false);
    }

}
