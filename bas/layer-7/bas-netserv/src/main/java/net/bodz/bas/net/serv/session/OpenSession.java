package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketConnector;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.io.ISocketWriter;
import net.bodz.bas.std.TransportType;
import net.bodz.bas.t.buffer.ByteArrayBuffer;

public class OpenSession
        extends AbstractSocketSession
        implements ISocketWriter {

    static final Logger logger = LoggerFactory.getLogger(OpenSession.class);

    TransportType transport;
    String protocol;
    int localPort;

    InetSocketAddress targetAddr;
    SocketChannel remoteChannel;

    ByteArrayBuffer sourceBuffer = new ByteArrayBuffer(4096);
    ByteArrayBuffer targetBuffer = new ByteArrayBuffer(4096);

    public OpenSession(SocketChannel channel, TransportType transportType, @NotNull String protocol, int localPort, @NotNull InetSocketAddress remoteAddr, ISocketPoller poller)
            throws IOException {
        super(channel);
        this.transport = transportType;
        this.protocol = protocol;
        this.localPort = localPort;
        this.targetAddr = remoteAddr;

        logger.debug(getPrefix() + "open to " + remoteAddr);
        remoteChannel = SocketChannel.open(remoteAddr);
        remoteChannel.configureBlocking(false);

        setup(poller);
    }

    public OpenSession(SocketChannel channel, @NotNull SocketChannel targetChannel, ISocketPoller poller)
            throws IOException {
        super(channel);
        this.remoteChannel = targetChannel;
        setup(poller);
    }

    void setup(ISocketPoller poller)
            throws IOException {
        poller.registerConnect(remoteChannel, (ISocketConnector) this::connectTarget);
        poller.registerRead(remoteChannel, (ISocketReader) this::readTarget);
        poller.registerWrite(remoteChannel, (ISocketWriter) this::writeTarget);
    }

    boolean connectTarget(SocketChannel targetChannel)
            throws IOException {
        logger.debug(getPrefix() + "connectTarget");
        logger.debug(getPrefix() + "connectionPending: " + targetChannel.isConnectionPending());
        logger.debug(getPrefix() + "connected: " + targetChannel.isConnected());
        return true;
    }

    long readTarget(SocketChannel targetChannel)
            throws IOException {
        logger.info(getPrefix() + "readTarget");
        ByteBuffer buf = ByteBuffer.allocate(4096);
        long totalBytesRead = 0;

        while (true) {
            int numBytesRead = targetChannel.read(buf);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return totalBytesRead;
                default:
                    totalBytesRead += numBytesRead;
            }

            buf.flip();
            targetBuffer.append(buf);
            buf.clear();
        }
    }

    long writeTarget(SocketChannel targetChannel)
            throws IOException {
        logger.debug(getPrefix() + "writeTarget");
        byte[] backedArray = sourceBuffer.getBackedArray();
        int backedArrayOffset = sourceBuffer.getBackedArrayOffset();
        int length = sourceBuffer.length();
        int numBytesWritten = targetChannel.write(ByteBuffer.wrap(backedArray, backedArrayOffset, length));
        sourceBuffer.delete(0, numBytesWritten);
        return numBytesWritten;
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        logger.debug(getPrefix() + "read");
        ByteBuffer byteBuf = ByteBuffer.allocate(4096);
        long totalBytesRead = 0;

        while (true) {
            int numBytesRead = channel.read(byteBuf);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return totalBytesRead;
                default:
                    totalBytesRead += numBytesRead;
            }

            byteBuf.flip();
            sourceBuffer.append(byteBuf);
            byteBuf.clear();
        }
    }

    @Override
    public long write(@NotNull SocketChannel channel)
            throws IOException {
        logger.debug(getPrefix() + "write");
        byte[] backedArray = targetBuffer.getBackedArray();
        int backedArrayOffset = targetBuffer.getBackedArrayOffset();
        int length = targetBuffer.length();
        int numBytesWritten = channel.write(ByteBuffer.wrap(backedArray, backedArrayOffset, length));
        targetBuffer.delete(0, numBytesWritten);
        return numBytesWritten;
    }

}
