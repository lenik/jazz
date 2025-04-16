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

    ListMap<SelectableChannel, ISocketAccepter> accepters = new ListMap<>();
    ListMap<SelectableChannel, ISocketConnector> connectors = new ListMap<>();
    ListMap<SelectableChannel, ISocketReader> readers = new ListMap<>();
    ListMap<SelectableChannel, ISocketWriter> writers = new ListMap<>();

    public DefaultPoller()
            throws IOException {
        selector = Selector.open();
    }

    List<ISocketAccepter> getAccepters(SelectableChannel channel) {
        return accepters.list(channel);
    }

    List<ISocketConnector> getConnectors(SelectableChannel channel) {
        return connectors.list(channel);
    }

    List<ISocketReader> getReaders(SelectableChannel channel) {
        return readers.list(channel);
    }

    List<ISocketWriter> getWriters(SelectableChannel channel) {
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

    private void cancel(SelectableChannel channel, int interestOp) {
        SelectionKey key = channel.keyFor(selector);
        if (key != null) {
            int ops = key.interestOps();
            ops = ops & ~interestOp;
            if (ops != 0)
                key.interestOps(ops);
            else
                key.cancel();
        }
    }

    @Override
    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketAccepter accepter) {
        getAccepters(channel).remove(accepter);
        cancel(channel, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketConnector connector) {
        getConnectors(channel).remove(connector);
        cancel(channel, SelectionKey.OP_CONNECT);
    }

    @Override
    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketReader reader) {
        getReaders(channel).remove(reader);
        cancel(channel, SelectionKey.OP_READ);
    }

    @Override
    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketWriter writer) {
        getWriters(channel).remove(writer);
        cancel(channel, SelectionKey.OP_WRITE);
    }

    @Override
    public void mainLoop()
            throws IOException {
        exit = false;
        while (!exit) {
            int numSelectedKeys = selector.select();
            logger.info("selected keys: " + numSelectedKeys);
            if (numSelectedKeys == 0)
                continue;

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            for (; keyIterator.hasNext(); keyIterator.remove()) {
                SelectionKey key = keyIterator.next();
                SelectableChannel _channel = key.channel();

                if (key.isValid() && key.isAcceptable()) {
                    logger.info("selected-key is acceptable");
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

                if (key.isValid() && key.isConnectable()) {
                    logger.info("selected-key is connectable");
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

                if (key.isValid() && key.isReadable()) {
                    logger.info("selected-key is readable");
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

                if (key.isValid() && key.isWritable()) {
                    logger.info("selected-key is writable");
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
