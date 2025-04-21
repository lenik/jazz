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
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketConnector;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.io.ISocketWriter;
import net.bodz.bas.t.buffer.ByteArrayBuffer;

public abstract class PeerSession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(PeerSession.class);

    SocketChannel targetChannel;
    ISocketPoller poller;
    Consumer<? super ISocketSession> closer;

    Charset charset = StandardCharsets.UTF_8;
    ByteArrayBuffer sendBuffer = new ByteArrayBuffer(4096);

    ByteArrayBuffer targetBuffer = new ByteArrayBuffer(4096);
    Charset targetCharset;
    CharsetDecoder targetCharsetDecoder;
    ByteBuffer targetByteBuf = ByteBuffer.allocate(4096);
    CharBuffer targetDecodedBuffer = CharBuffer.allocate(4096);

    final String typeName;

    public PeerSession(@NotNull SocketChannel channel, @NotNull SocketChannel targetChannel, @NotNull ISocketPoller poller, Consumer<? super ISocketSession> closer) {
        super(channel);

        this.targetChannel = targetChannel;
        this.poller = poller;
        this.closer = closer;

        setTargetCharset(StandardCharsets.UTF_8);

        String typeName = getClass().getSimpleName();
        if (typeName.endsWith("Session"))
            typeName = typeName.substring(0, typeName.length() - 7);
        this.typeName = typeName;
    }

    void setTargetCharset(Charset charset) {
        this.targetCharset = charset;
        this.targetCharsetDecoder = targetCharset.newDecoder();
        this.targetCharsetDecoder.onMalformedInput(CodingErrorAction.REPLACE);
    }

    @Override
    protected String getTitle() {
        if (targetChannel.socket().isClosed())
            return typeName;
        String addr = SocketChannels.addressInfo(targetChannel);
        return typeName + "<" + addr + ">";
    }

    public void connect(InetSocketAddress addr)
            throws IOException {
        targetChannel.configureBlocking(false);
        poller.registerConnect(targetChannel, (ISocketConnector) this::connectTarget);
        targetChannel.connect(addr);
    }

    boolean connectTarget(SocketChannel targetChannel)
            throws IOException {
        logger.debug(getPrefix() + "/connectTarget");
        targetChannel.finishConnect();

        poller.cancelConnect(targetChannel);

        poller.registerRead(targetChannel, (ISocketReader) this::readTarget);
        enableTargetWriter();

        return true;
    }

    boolean decode = false;

    long readTarget(SocketChannel targetChannel)
            throws IOException {
        logger.debug(getPrefix() + "/readTarget");
        if (targetChannel.socket().isClosed()) {
            logger.debug(getPrefix() + "target is closed");
            closer.accept(this);
            return 0;
        }

        ByteBuffer byteBuf = ByteBuffer.allocate(4096);
        long totalBytesRead = 0;
        while (true) {
            int numBytesRead = targetChannel.read(byteBuf);
            logger.debug("    read " + numBytesRead + " bytes");

            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return totalBytesRead;
                default:
                    totalBytesRead += numBytesRead;
            }

            byteBuf.flip();
            if (decode) {
                targetByteBuf.put(byteBuf);
                targetByteBuf.flip();
                targetCharsetDecoder.decode(targetByteBuf, targetDecodedBuffer, false);
                targetByteBuf.compact();
                // targetBuffer.flip();
                targetDecodedBuffer.flip();
                String part = targetDecodedBuffer.toString();
                targetDecodedBuffer.clear();
            } else {
//                onReceivedFromTarget(byteBuf);
                targetBuffer.append(byteBuf);
            }
            byteBuf.clear();
        }
    }

    protected void onReceivedFromTarget()
            throws IOException {
    }

    void enableTargetWriter()
            throws IOException {
        logger.debug(getPrefix() + "enableTargetWriter");
        poller.registerWrite(targetChannel, (ISocketWriter) this::writeTarget);
    }

    void disableTargetWriter() {
        logger.debug(getPrefix() + "disableTargetWriter");
        poller.cancelWrite(targetChannel);
    }

    long writeTarget(SocketChannel targetChannel)
            throws IOException {
        int length = sendBuffer.length();
        if (length == 0) {
            disableTargetWriter();
            return 0;
        }

        byte[] backedArray = sendBuffer.getBackedArray();
        int backedArrayOffset = sendBuffer.getBackedArrayOffset();
        logger.debug(getPrefix() + "/writeTarget, available " + length);

        ByteBuffer buf = ByteBuffer.wrap(backedArray, backedArrayOffset, length);
        int numBytesWritten = targetChannel.write(buf);
        logger.debug("    " + numBytesWritten + " written");
        sendBuffer.delete(0, numBytesWritten);

        return numBytesWritten;
    }

    public void sendToTarget(String message)
            throws IOException {
        logger.debug(getPrefix() + "send(" + message + ")");
        byte[] bytes = message.getBytes(charset);
        sendToTarget(bytes);
    }

    public void sendToTarget(byte[] buf)
            throws IOException {
        sendToTarget(buf, 0, buf.length);
    }

    public void sendToTarget(byte[] buf, int off, int len)
            throws IOException {
        sendBuffer.append(buf, off, len);
        enableTargetWriter();
    }

}
