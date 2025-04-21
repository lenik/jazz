package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketConnector;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.io.ISocketWriter;
import net.bodz.bas.t.buffer.ByteArrayBuffer;

public class SendSession
        extends PeerSession {

    static final Logger logger = LoggerFactory.getLogger(SendSession.class);

    public SendSession(@NotNull SocketChannel channel, @NotNull SocketChannel targetChannel, @NotNull ISocketPoller poller, Consumer<? super ISocketSession> closer)
            throws IOException {
        super(channel, targetChannel, poller, closer);
    }

    @Override
    public long read(@NotNull SocketChannel channel)
            throws IOException {
        throw new NotImplementedException();
    }

    @Override
    protected void onReceivedFromTarget()
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
