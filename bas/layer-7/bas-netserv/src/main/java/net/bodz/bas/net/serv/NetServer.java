package net.bodz.bas.net.serv;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.net.serv.session.StarterSession;

public class NetServer {

    static final Logger logger = LoggerFactory.getLogger(NetServer.class);

    List<InetSocketAddress> localAddrs = new ArrayList<>();

    Selector selector;
    List<ServerSocketChannel> serverChannels = new ArrayList<>();
    boolean exitRequest;

    void setupLocal() {
    }

    void start()
            throws IOException {
        selector = Selector.open();

        for (InetSocketAddress localAddr : localAddrs) {
            // Open a server socket channel and bind it to the port
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(localAddr);

            // Register the server socket with the selector for ACCEPT events
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            logger.info("Listen on " + localAddr.toString());

            serverChannels.add(serverChannel);
        }

        Map<SocketChannel, Session> sessions = new HashMap<>();

        while (!exitRequest) {
            int numSelectedKeys = selector.select();
            if (numSelectedKeys == 0)
                continue;

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            for (; keyIterator.hasNext(); keyIterator.remove()) {
                SelectionKey key = keyIterator.next();

                SelectableChannel _channel = key.channel();

                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) _channel;
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);

                    // Register the client channel for READ events
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    continue;
                }

                if (key.isReadable()) {
                    // Data is available to be read from a client
                    SocketChannel channel = (SocketChannel) _channel;
                    Session session = sessions.computeIfAbsent(channel, StarterSession::new);
                    try {
                        session.onDataReady(channel);
                    } catch (ParseException e) {
                        session.onError(channel, e);
                    }
                }
            }
        }
    }

}
