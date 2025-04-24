package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketConnector;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.util.CloseEvent;
import net.bodz.bas.net.util.IClosedListener;
import net.bodz.bas.net.util.SmartBuffer;
import net.bodz.bas.net.util.SocketBuffer;

public abstract class PeerSession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(PeerSession.class);

    ISocketPoller poller;
    SocketChannel targetChannel;
    IClosedListener targetCloseListener;

    SocketBuffer target;

    public PeerSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller)
            throws IOException {
        this(name, channel, poller, SocketChannel.open());
    }

    public PeerSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller, @NotNull SocketChannel targetChannel) {
        super(name, channel, poller);
        this.poller = poller;
        this.targetChannel = targetChannel;
        this.target = new SocketBuffer(targetChannel, poller);
    }

    public SmartBuffer getTarget() {
        return target;
    }

    protected String getPrefix() {
        if (targetChannel.socket().isClosed())
            return "-";
        String conn = SocketChannels.getConnectionShortInfo(targetChannel);
        return "<" + conn + ">";
    }

    @NotNull
    public SocketChannel getTargetChannel() {
        return targetChannel;
    }

    public void connect(InetSocketAddress addr, IClosedListener targetCloseListener)
            throws IOException {
        if (targetChannel == null)
            targetChannel = SocketChannel.open();
        targetChannel.configureBlocking(false);
        poller.registerConnect(targetChannel, (ISocketConnector) this::connectTarget);
        targetChannel.connect(addr);
        this.targetCloseListener = targetCloseListener;
    }

    boolean connectTarget(SocketChannel targetChannel)
            throws IOException {
        logger.debug(getPrefix() + "/connectTarget");
        targetChannel.finishConnect();

        poller.cancelConnect(targetChannel);

        poller.registerRead(targetChannel, (ISocketReader) this::readTarget);
        target.enableWriter();

        return true;
    }

    void closeTarget() {
        poller.cancelAll(targetChannel);
        try {
            if (targetCloseListener != null) {
                CloseEvent event = new CloseEvent(targetChannel);
                targetCloseListener.onClosed(event);
            }
        } finally {
            try {
                targetChannel.socket().close();
            } catch (IOException e) {
                logger.error("error close socket of target channel: " + e.getMessage(), e);
            }
        }
    }

    long readTarget(SocketChannel targetChannel)
            throws IOException {
        return target.read(channel);
    }

    /**
     * Read targetBuffer or targetCharBuffer depends on decode mode.
     */
    protected abstract void readTargetBuffer();

    long writeTarget(SocketChannel targetChannel)
            throws IOException {
        return target.write(targetChannel);
    }

    public void sendToTarget(String message)
            throws IOException {
        logger.debug(getPrefix() + "send(" + message + ")");
        byte[] bytes = message.getBytes(target.getCharset());
        sendToTarget(bytes);
    }

    public void sendToTarget(byte[] buf)
            throws IOException {
        sendToTarget(buf, 0, buf.length);
    }

    public void sendToTarget(byte[] buf, int off, int len)
            throws IOException {
        target.writeBuffer.append(buf, off, len);
        target.enableWriter();
    }

}
