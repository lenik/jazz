package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.t.buffer.ByteArrayBuffer;

public class SendSession
        extends PeerSession {

    static final Logger logger = LoggerFactory.getLogger(SendSession.class);

    public SendSession(@NotNull SocketChannel channel, @NotNull ISocketPoller poller)
            throws IOException {
        super(channel, poller);
    }

    public SendSession(@NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull SocketChannel targetChannel)
            throws IOException {
        super(channel, poller, targetChannel);
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        throw new NotImplementedException();
    }

    @Override
    protected void readTargetBuffer(ByteArrayBuffer targetBuffer)
            throws IOException {

        byte[] backedArray = targetBuffer.getBackedArray();
        int backedArrayOffset = targetBuffer.getBackedArrayOffset();
        int length = targetBuffer.length();

        String message = new String(backedArray, backedArrayOffset, length, targetCharset);
        if (linePrefix != null)
            message = linePrefix + message;

        byte[] data = message.getBytes(targetCharset);

        ByteBuffer buf = ByteBuffer.wrap(data);
        int numBytesWritten = channel.write(buf);

        targetBuffer.delete(0, numBytesWritten);
    }

}
