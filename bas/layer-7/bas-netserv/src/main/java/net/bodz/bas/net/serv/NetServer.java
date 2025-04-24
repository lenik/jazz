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
        logger.debug("onAccept: " + SocketChannels.getConnectionShortInfo(clientChannel));

        clientChannel.configureBlocking(false);

        HubSession session = new HubSession("hub", clientChannel, poller, sessionManager, serviceManager);
        String id = sessionManager.addSession(session);
        session.setName("hub" + id);

        session.open();
        return true;
    }

}
