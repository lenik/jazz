package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.map.ListMap;

public class DefaultPoller
        implements ISocketPoller {

    static final Logger logger = LoggerFactory.getLogger(DefaultPoller.class);

    final Selector selector;
    boolean exit;

    ListMap<ServerSocketChannel, ISocketAccepter> accepters = new ListMap<>();
    ListMap<SocketChannel, ISocketConnector> connectors = new ListMap<>();
    ListMap<SocketChannel, ISocketReader> readers = new ListMap<>();
    ListMap<SocketChannel, ISocketWriter> writers = new ListMap<>();

    public DefaultPoller()
            throws IOException {
        selector = Selector.open();
    }

    List<ISocketAccepter> getAccepters(ServerSocketChannel channel) {
        return accepters.list(channel);
    }

    List<ISocketConnector> getConnectors(SocketChannel channel) {
        return connectors.list(channel);
    }

    List<ISocketReader> getReaders(SocketChannel channel) {
        return readers.list(channel);
    }

    List<ISocketWriter> getWriters(SocketChannel channel) {
        return writers.list(channel);
    }

    @Override
    public void register(@NotNull ServerSocketChannel channel, @NotNull ISocketAccepter accepter)
            throws IOException {
        getAccepters(channel).add(accepter);
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void register(@NotNull SocketChannel channel, @NotNull ISocketConnector connector)
            throws IOException {
        getConnectors(channel).add(connector);
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    @Override
    public void register(@NotNull SocketChannel channel, @NotNull ISocketReader reader)
            throws IOException {
        getReaders(channel).add(reader);
        channel.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void register(@NotNull SocketChannel channel, @NotNull ISocketWriter writer)
            throws IOException {
        getWriters(channel).add(writer);
        channel.register(selector, SelectionKey.OP_WRITE);
    }

    @Override
    public void cancel(@NotNull ISocketAccepter accepter) {
        accepters.removeFromAllLists(accepter);
    }

    @Override
    public void cancel(@NotNull ISocketConnector connector) {
        connectors.removeFromAllLists(connector);
    }

    @Override
    public void cancel(@NotNull ISocketReader reader) {
        readers.removeFromAllLists(reader);
    }

    @Override
    public void cancel(@NotNull ISocketWriter writer) {
        writers.removeFromAllLists(writer);
    }

    @Override
    public void mainLoop()
            throws IOException {
        exit = false;
        while (!exit) {
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
                    for (ISocketAccepter accepter : getAccepters(serverChannel)) {
                        try {
                            if (accepter.accept(serverChannel))
                                break;
                        } catch (IOException e) {
                            logger.error(e, "error accepting: " + e.getMessage());
                        }
                    }
                }

                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) _channel;
                    for (ISocketConnector connector : getConnectors(channel)) {
                        try {
                            if (connector.connect(channel))
                                break;
                        } catch (IOException e) {
                            logger.error(e, "error connecting: " + e.getMessage());
                        }
                    }
                }

                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) _channel;
                    for (ISocketReader reader : getReaders(channel)) {
                        try {
                            if (reader.read(channel))
                                break;
                        } catch (IOException e) {
                            logger.error(e, "error reading: " + e.getMessage());
                        } catch (ParseException e) {
                            logger.error(e, "error parsing: " + e.getMessage());
                        }
                    }
                }

                if (key.isWritable()) {
                    SocketChannel channel = (SocketChannel) _channel;
                    for (ISocketWriter writer : getWriters(channel)) {
                        try {
                            if (writer.write(channel))
                                break;
                        } catch (IOException e) {
                            logger.error(e, "error writing: " + e.getMessage());
                        }
                    }
                }

            }
        }
    }

    @Override
    public void cancel() {
        this.exit = true;
    }

}
