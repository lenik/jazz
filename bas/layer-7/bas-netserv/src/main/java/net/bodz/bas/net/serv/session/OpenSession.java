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
import net.bodz.bas.std.TransportType;

public class OpenSession
        extends AbstractSocketSession {

    static final Logger logger = LoggerFactory.getLogger(OpenSession.class);

    TransportType transport;
    String protocol;
    int localPort;

    InetSocketAddress targetAddr;
    SocketChannel remoteChannel;

    public OpenSession(SocketChannel channel, TransportType transportType, @NotNull String protocol, int localPort, @NotNull InetSocketAddress remoteAddr, ISocketPoller poller)
            throws IOException {
        super(channel);
        this.transport = transportType;
        this.protocol = protocol;
        this.localPort = localPort;
        this.targetAddr = remoteAddr;

        logger.info("Remote: " + remoteAddr);
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
        poller.register(remoteChannel, (ISocketConnector) this::connectTarget);
        poller.register(remoteChannel, (ISocketReader) this::readTarget);
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
            remoteChannel.write(byteBuf);
            byteBuf.clear();
        }
    }

}
