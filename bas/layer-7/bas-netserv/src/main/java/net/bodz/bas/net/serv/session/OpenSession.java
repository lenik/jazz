package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.IReadResult;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketWriter;
import net.bodz.bas.net.io.ReadResult;
import net.bodz.bas.std.TransportType;
import net.bodz.bas.t.buffer.ByteArrayBuffer;
import net.bodz.bas.t.buffer.IByteBuffer;

public class OpenSession
        extends PeerSession
        implements ISocketWriter {

    static final Logger logger = LoggerFactory.getLogger(OpenSession.class);

    TransportType transport;
    String protocol;
    int localPort;

    IByteBuffer sourceBuffer = new ByteArrayBuffer(4096);
    IByteBuffer sourceWriteBuffer = new ByteArrayBuffer(4096);

    public OpenSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller, //
            TransportType transportType, @NotNull String protocol, int localPort)
            throws IOException {
        super(name, channel, poller);
        this.transport = transportType;
        this.protocol = protocol;
        this.localPort = localPort;
    }

    public OpenSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull SocketChannel targetChannel, //
            TransportType transportType, @NotNull String protocol, int localPort) {
        super(name, channel, poller, targetChannel);
        this.transport = transportType;
        this.protocol = protocol;
        this.localPort = localPort;
    }

    @Override
    public IReadResult read(@NotNull SocketChannel channel)
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
                    return ReadResult.numOfBytes(totalBytesRead, numBytesRead == -1);
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
    protected void readTargetBuffer() {
        IByteBuffer targetBuffer = target;
        byte[] backedArray = targetBuffer.getBackedArray();
        int backedArrayOffset = targetBuffer.getBackedArrayOffset();
        int length = targetBuffer.length();
        sourceWriteBuffer.append(backedArray, backedArrayOffset, length);
        targetBuffer.delete(0, length);
        // enableSourceWriter();
    }

}
