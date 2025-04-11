package net.bodz.bas.net.serv;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.net.io.DefaultPoller;
import net.bodz.bas.net.io.ISocketAccepter;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.serv.session.StarterSession;
import net.bodz.bas.parser.IErrorRecoverer;

public class NetServer {

    static final Logger logger = LoggerFactory.getLogger(NetServer.class);

    List<InetSocketAddress> localAddrs = new ArrayList<>();

    DefaultPoller poller;

    ISessionManager sessionManager = new DefaultSessionManager();
    int nextId = 1;

    void setupLocal() {
    }

    void start()
            throws IOException {
        poller = new DefaultPoller();

        for (InetSocketAddress localAddr : localAddrs) {
            // Open a server socket channel and bind it to the port
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(localAddr);

            logger.info("Listen on " + localAddr.toString());
            poller.register(serverChannel, (ISocketAccepter) this::onAccept);
        }

        poller.mainLoop();
    }

    boolean onAccept(ServerSocketChannel serverChannel)
            throws IOException {
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);

        poller.register(clientChannel, (ISocketReader) this::onReceive);

        String id = String.valueOf(nextId++);
        StarterSession session = new StarterSession(id, clientChannel, sessionManager, poller);
        sessionManager.addSession(session);

        return true;
    }

    boolean onReceive(SocketChannel channel)
            throws IOException {
        ISession session = sessionManager.getSession(channel);
        if (session == null)
            // already closed, or unmanaged channels.
            return true;

        while (true) {
            try {
                session.read(channel);
            } catch (ParseException e) {
                if (session instanceof IErrorRecoverer) {
                    IErrorRecoverer recoverer = (IErrorRecoverer) session;
                    if (recoverer.recoverError(e))
                        continue;
                }
                logger.error(e);
                session.close();
            }
            break;
        }

        if (session.isClosed())
            sessionManager.removeSession(session);

        return true;
    }

}
