package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

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
        extends PeerSession
        implements ISocketWriter {

    static final Logger logger = LoggerFactory.getLogger(OpenSession.class);

    TransportType transport;
    String protocol;
    int localPort;

    ByteArrayBuffer sourceBuffer = new ByteArrayBuffer(4096);
    ByteArrayBuffer sourceWriteBuffer = new ByteArrayBuffer(4096);

    public OpenSession(@NotNull SocketChannel channel, @NotNull ISocketPoller poller, //
            TransportType transportType, @NotNull String protocol, int localPort)
            throws IOException {
        super(channel, poller);
        this.transport = transportType;
        this.protocol = protocol;
        this.localPort = localPort;
    }

    public OpenSession(@NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull SocketChannel targetChannel, //
            TransportType transportType, @NotNull String protocol, int localPort) {
        super(channel, poller, targetChannel);
        this.transport = transportType;
        this.protocol = protocol;
        this.localPort = localPort;
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
        byte[] backedArray = sourceWriteBuffer.getBackedArray();
        int backedArrayOffset = sourceWriteBuffer.getBackedArrayOffset();
        int length = sourceWriteBuffer.length();
        int numBytesWritten = channel.write(ByteBuffer.wrap(backedArray, backedArrayOffset, length));
        sourceWriteBuffer.delete(0, numBytesWritten);
        return numBytesWritten;
    }

    @Override
    protected void readTargetBuffer(ByteArrayBuffer targetBuffer)
            throws IOException {
        byte[] backedArray = targetBuffer.getBackedArray();
        int backedArrayOffset = targetBuffer.getBackedArrayOffset();
        int length = targetBuffer.length();
        sourceWriteBuffer.append(backedArray, backedArrayOffset, length);
        targetBuffer.delete(0, length);
        // enableSourceWriter();
    }

}
