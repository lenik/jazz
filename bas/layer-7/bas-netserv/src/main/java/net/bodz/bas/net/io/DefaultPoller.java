package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractInterruptibleChannel;
import java.util.Iterator;
import java.util.Set;

import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.err.ErrorRecoverer;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.record.AutoIndexRecordMap;
import net.bodz.bas.t.record.IRecordMap;

public class DefaultPoller
        implements ISocketPoller {

    static final Logger logger = LoggerFactory.getLogger(DefaultPoller.class);
    static final ErrorRecoverer LR = ErrorRecoverer.byLogging(logger);

    final Selector selector;
    boolean exit;

    IRecordMap<SelectableChannel, ChannelLink> map = new AutoIndexRecordMap<>(ChannelLink.TYPE);

    public DefaultPoller()
            throws IOException {
        selector = Selector.open();
    }

    Set<ISocketAccepter> getAccepters(SelectableChannel channel) {
        return map.makeIndex(ChannelLink.ACCEPTER).keySet();
    }

    Set<ISocketConnector> getConnectors(SelectableChannel channel) {
        return map.makeIndex(ChannelLink.CONNECTOR).keySet();
    }

    Set<ISocketReader> getReaders(SelectableChannel channel) {
        return map.makeIndex(ChannelLink.READER).keySet();
    }

    Set<ISocketWriter> getWriters(SelectableChannel channel) {
        return map.makeIndex(ChannelLink.WRITER).keySet();
    }

    @Override
    public void register(@NotNull ServerSocketChannel channel, @NotNull ISocketAccepter accepter)
            throws IOException {
        ChannelLink link = map.computeIfAbsent(channel, ChannelLink::new);
        link.addAccepter(accepter);
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void register(@NotNull SocketChannel channel, @NotNull ISocketConnector connector)
            throws IOException {
        ChannelLink link = map.computeIfAbsent(channel, ChannelLink::new);
        link.addConnector(connector);
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    @Override
    public void register(@NotNull SocketChannel channel, @NotNull ISocketReader reader)
            throws IOException {
        ChannelLink link = map.computeIfAbsent(channel, ChannelLink::new);
        link.addReader(reader);
        channel.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void register(@NotNull SocketChannel channel, @NotNull ISocketWriter writer)
            throws IOException {
        ChannelLink link = map.computeIfAbsent(channel, ChannelLink::new);
        link.addWriter(writer);
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
        ChannelLink link = map.get(channel);
        if (link != null)
            link.removeAccepter(accepter);
        cancel(channel, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketConnector connector) {
        ChannelLink link = map.get(channel);
        if (link != null)
            link.removeConnector(connector);
        cancel(channel, SelectionKey.OP_CONNECT);
    }

    @Override
    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketReader reader) {
        ChannelLink link = map.get(channel);
        if (link != null)
            link.removeReader(reader);
        cancel(channel, SelectionKey.OP_READ);
    }

    @Override
    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketWriter writer) {
        ChannelLink link = map.get(channel);
        if (link != null)
            link.removeWriter(writer);
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
                    handleAccept(serverChannel);
                }

                if (key.isValid() && key.isConnectable()) {
                    logger.info("selected-key is connectable");
                    assert _channel instanceof SocketChannel;
                    SocketChannel channel = (SocketChannel) _channel;
                    handleConnect(channel);
                }

                if (key.isValid() && key.isReadable()) {
                    logger.info("selected-key is readable");
                    assert _channel instanceof SocketChannel;
                    SocketChannel channel = (SocketChannel) _channel;
                    handleRead(channel);
                }

                if (key.isValid() && key.isWritable()) {
                    logger.info("selected-key is writable");
                    assert _channel instanceof SocketChannel;
                    SocketChannel channel = (SocketChannel) _channel;
                    handleWrite(channel);
                }
            }
        }

    }

    void handleAccept(ServerSocketChannel serverChannel) {
        boolean accepted = false;
        for (ISocketAccepter accepter : getAccepters(serverChannel)) {
            if (accepter == null)
                continue;
            try {
                if (accepter.accept(serverChannel)) {
                    accepted = true;
                    break;
                }
            } catch (IOException e) {
                logger.error(e, "error accepting: " + e.getMessage());
            }
        }
        if (accepted)
            return;

        SocketChannel discard;
        logger.error("not accepted, close with force.");
        try {
            discard = serverChannel.accept();
        } catch (IOException e) {
            logger.error(e, "error accept");
            return;
        }

        LR.run(() -> discard.configureBlocking(true), "error configure to blocking");

        LR.run(discard::close, "error close");
    }

    void handleConnect(SocketChannel channel) {
        boolean handled = false;
        for (ISocketConnector connector : getConnectors(channel)) {
            if (connector == null)
                continue;
            try {
                if (connector.connect(channel)) {
                    handled = true;
                    break;
                }
            } catch (IOException e) {
                logger.error(e, "error connecting: " + e.getMessage());
            }
        }

        if (!handled) {
            logger.error("connect not handled, force to close");
            LR.run(channel::close, "error to close");
        }
    }

    void handleRead(SocketChannel channel) {
        long totalBytesRead = 0;
        for (ISocketReader reader : getReaders(channel)) {
            if (reader == null)
                continue;
            try {
                long numBytesRead = reader.read(channel);
                totalBytesRead += numBytesRead;
            } catch (IOException e) {
                logger.error(e, "error reading: " + e.getMessage());
            }
        }

        if (totalBytesRead == 0) {
            logger.error("invalid usage: no byte have been read. discard the receive buffer forcely.");
            try {
                totalBytesRead = SocketChannels.discardReceivedBytes(channel);
            } catch (IOException e) {
                logger.error(e, "error discard received bytes");
                return;
            }
            logger.debug("    discarded " + totalBytesRead + " bytes.");
        }
    }

    void handleWrite(SocketChannel channel) {
        long totalBytesWritten = 0;
        for (ISocketWriter writer : getWriters(channel)) {
            if (writer == null)
                continue;
            try {
                long numBytesWritten = writer.write(channel);
                totalBytesWritten += numBytesWritten;
            } catch (IOException e) {
                logger.error(e, "error writing: " + e.getMessage());
                return;
            }
        }
        if (totalBytesWritten == 0) {
            logger.debug("no byte have been written. ");
        }
    }

    @Override
    public void cancel() {
        this.exit = true;
    }

}
