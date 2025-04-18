package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketConnector;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.io.ISocketWriter;
import net.bodz.bas.t.buffer.ByteArrayBuffer;

public class RelayToSession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(RelayToSession.class);

    SocketChannel targetChannel;
    boolean initBlocking;
    boolean managed = true;

    public final ByteArrayBuffer sendBuffer = new ByteArrayBuffer(4096);

    public RelayToSession(@NotNull SocketChannel channel, @NotNull SocketChannel targetChannel, @NotNull ISocketPoller poller)
            throws IOException {
        super(channel);
        this.targetChannel = targetChannel;

        initBlocking = targetChannel.isBlocking();
        targetChannel.configureBlocking(false);

        poller.register(targetChannel, (ISocketConnector) this::connectTarget);
        poller.register(targetChannel, (ISocketReader) this::readTarget);
        poller.register(targetChannel, (ISocketWriter) this::writeTarget);
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        ByteBuffer byteBuf = ByteBuffer.allocate(4096);
        long totalBytesRead = 0;
        while (true) {
            int numBytesRead = channel.read(byteBuf);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return totalBytesRead;
            }
            totalBytesRead += numBytesRead;
            byteBuf.flip();
            sendBuffer.append(byteBuf);
            byteBuf.clear();
        }
    }

    boolean connectTarget(SocketChannel targetChannel)
            throws IOException {
        logger.info("connectTarget");
        logger.info("connectionPending: " + targetChannel.isConnectionPending());
        logger.info("connected: " + targetChannel.isConnected());
        writeTarget(targetChannel);
        return true;
    }

    long readTarget(SocketChannel targetChannel)
            throws IOException {
        logger.info("readTarget");
        ByteBuffer buf = ByteBuffer.allocate(4096);

        int totalBytesRead = 0;

        while (true) {
            int numBytesRead = targetChannel.read(buf);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return totalBytesRead;
            }
            totalBytesRead += numBytesRead;
            buf.flip();
            channel.write(buf);
            buf.clear();
        }
    }

    long writeTarget(@NotNull SocketChannel targetChannel)
            throws IOException {
        int pending = sendBuffer.length();
        if (pending == 0) {
            logger.info("-- writeTarget");
        }
        logger.info("writeTarget " + pending);

        byte[] backedArray = sendBuffer.getBackedArray();
        int backedArrayOffset = sendBuffer.getBackedArrayOffset();
        int length = sendBuffer.length();

        int numBytesWritten = targetChannel.write(ByteBuffer.wrap(backedArray, backedArrayOffset, length));
        if (numBytesWritten > 0)
            sendBuffer.delete(0, numBytesWritten);
        return Math.max(numBytesWritten, 0);
    }

}
