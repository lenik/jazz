package net.bodz.bas.net.serv;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.net.io.DefaultPoller;
import net.bodz.bas.net.io.ISocketAccepter;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.serv.session.ISocketSession;
import net.bodz.bas.net.serv.session.HubSession;

public class NetServer {

    static final Logger logger = LoggerFactory.getLogger(NetServer.class);

    List<InetSocketAddress> localAddrs = new ArrayList<>();

    DefaultPoller poller;

    ISessionManager sessionManager = new DefaultSessionManager();
    int nextId = 1;


    DefaultServiceManager serviceManager = new DefaultServiceManager();

    void setupLocal() {
    }

    void start()
            throws IOException, InterruptedException {
        poller = new DefaultPoller();

        for (InetSocketAddress localAddr : localAddrs) {
            // Open a server socket channel and bind it to the port
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(localAddr);

            logger.info("Listen on " + localAddr.toString());
            poller.registerAccept(serverChannel, (ISocketAccepter) this::onAccept);
        }

        poller.mainLoop();
    }

    boolean onAccept(ServerSocketChannel serverChannel)
            throws IOException {
        SocketChannel clientChannel = serverChannel.accept();
        logger.debug("onAccept: " + SocketChannels.addressInfo(clientChannel));

        clientChannel.configureBlocking(false);
        poller.registerRead(clientChannel, (ISocketReader) this::readClient);

        ISocketSession session = new HubSession(clientChannel, sessionManager, poller, serviceManager);
        sessionManager.addSession(session);

        return true;
    }

    long readClient(SocketChannel channel)
            throws IOException {
        ISocketSession session = sessionManager.getSessionByChannel(channel);
        if (session == null) {
            logger.error("invalid unmanaged channel: " + SocketChannels.addressInfo(channel));
            return 0;
        }

        long numBytesRead = session.read(channel);
        logger.debug("readClient: read " + numBytesRead + " bytes from " + SocketChannels.addressInfo(channel));

        if (session.isClosed()) {
            logger.debug("Session closed: #" + sessionManager.getSessionId(session));
            poller.cancelRead(channel);
            try {
                channel.socket().close();
            } catch (IOException e) {
                logger.error("error close the channel: " + e.getMessage(), e);
            }
            sessionManager.removeSession(session);
        }

        return numBytesRead;
    }

}
