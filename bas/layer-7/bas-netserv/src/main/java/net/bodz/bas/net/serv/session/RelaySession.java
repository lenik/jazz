package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketConnector;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.io.ISocketReader;

public class RelaySession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(RelaySession.class);

    InetSocketAddress targetAddr;
    SocketChannel targetChannel;

    public RelaySession(SocketChannel channel, @NotNull InetSocketAddress targetAddr, ISocketPoller poller)
            throws IOException {
        super(channel);
        this.targetAddr = targetAddr;

        logger.info("Open target: " + targetAddr);
        targetChannel = SocketChannel.open(targetAddr);
        targetChannel.configureBlocking(false);

        setup(poller);
    }

    public RelaySession(SocketChannel channel, @NotNull SocketChannel targetChannel, ISocketPoller poller)
            throws IOException {
        super(channel);
        this.targetChannel = targetChannel;
        setup(poller);
    }

    void setup(ISocketPoller poller)
            throws IOException {
        poller.register(targetChannel, (ISocketConnector) this::connectTarget);
        poller.register(targetChannel, (ISocketReader) this::readTarget);
        // poller.register(targetChannel, (ISocketWriter) this::writeTarget);
    }

    boolean connectTarget(SocketChannel targetChannel)
            throws IOException {
        logger.info("connectTarget");
        logger.info("connectionPending: " + targetChannel.isConnectionPending());
        logger.info("connected: " + targetChannel.isConnected());
        return true;
    }

    boolean readTarget(SocketChannel targetChannel)
            throws IOException {
        logger.info("readTarget");
        ByteBuffer buf = ByteBuffer.allocate(4096);

        while (true) {
            int numBytesRead = targetChannel.read(buf);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return true;
            }

            buf.flip();
            channel.write(buf);
            buf.clear();
        }
    }

    @Override
    public boolean read(@NotNull SocketChannel channel)
            throws IOException, ParseException {
        ByteBuffer byteBuf = ByteBuffer.allocate(4096);

        while (true) {
            int numBytesRead = channel.read(byteBuf);
            switch (numBytesRead) {
                case -1:
                    close();
                case 0:
                    return true;
            }

            byteBuf.flip();
            targetChannel.write(byteBuf);
            byteBuf.clear();
        }
    }

}
