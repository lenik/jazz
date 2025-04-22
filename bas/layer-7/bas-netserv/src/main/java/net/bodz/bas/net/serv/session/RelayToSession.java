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
import net.bodz.bas.t.buffer.ByteArrayBuffer;

public class RelayToSession
        extends PeerSession {

    static final Logger logger = LoggerFactory.getLogger(RelayToSession.class);

    boolean initBlocking;
    boolean managed = true;

//    public final ByteArrayBuffer sendBuffer = new ByteArrayBuffer(4096);


    public RelayToSession(@NotNull SocketChannel channel, @NotNull ISocketPoller poller)
            throws IOException {
        super(channel, poller);
    }

    public RelayToSession(@NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull SocketChannel targetChannel)
            throws IOException {
        super(channel, poller, targetChannel);
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
                default:
                    totalBytesRead += numBytesRead;
            }
            byteBuf.flip();
            sendBuffer.append(byteBuf);
            byteBuf.clear();
        }
    }

    @Override
    protected void readTargetBuffer(ByteArrayBuffer targetBuffer)
            throws IOException {
        byte[] backedArray = targetBuffer.getBackedArray();
        int backedArrayOffset = targetBuffer.getBackedArrayOffset();
        int length = targetBuffer.length();

        ByteBuffer buf = ByteBuffer.wrap(backedArray, backedArrayOffset, length);
        int numBytesWritten = channel.write(buf);

        targetBuffer.delete(0, numBytesWritten);
    }

}
