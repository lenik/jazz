package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.IReadResult;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.t.buffer.IByteBuffer;

public class RelayToSession
        extends PeerSession {

    static final Logger logger = LoggerFactory.getLogger(RelayToSession.class);

    boolean initBlocking;
    boolean managed = true;

//    public final ByteArrayBuffer sendBuffer = new ByteArrayBuffer(4096);


    public RelayToSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller)
            throws IOException {
        super(name, channel, poller);
    }

    public RelayToSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull SocketChannel targetChannel)
            throws IOException {
        super(name, channel, poller, targetChannel);
    }

    @Override
    public IReadResult read(@NotNull SocketChannel channel)
            throws IOException {
        IReadResult rr = buffer.read(channel);
        buffer.moveTo(target);
        return rr;
    }

    @Override
    protected void readTargetBuffer() {
        IByteBuffer targetBuffer = target;

        byte[] backedArray = targetBuffer.getBackedArray();
        int backedArrayOffset = targetBuffer.getBackedArrayOffset();
        int length = targetBuffer.length();

        ByteBuffer buf = ByteBuffer.wrap(backedArray, backedArrayOffset, length);
        int numBytesWritten = 0;
        try {
            numBytesWritten = channel.write(buf);
        } catch (IOException e) {
            logger.error("error writing to source channel", e);
        }

        targetBuffer.delete(0, numBytesWritten);
    }

}
