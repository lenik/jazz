package net.bodz.bas.net.io;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.java.nio.channels.SelectableChannels;
import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.err.ErrorRecoverer;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.record.AutoIndexRecordMap;
import net.bodz.bas.t.record.IRecordMap;

public class DefaultPoller
        implements ISocketPoller,
                   Closeable {

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
    public SelectionKey registerAccept(@NotNull ServerSocketChannel channel, @NotNull ISocketAccepter accepter)
            throws IOException {
        ChannelLink link = map.computeIfAbsent(channel, ChannelLink::new);
        link.setAccepter(accepter);
        return SelectableChannels.registerOr(channel, selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public SelectionKey registerConnect(@NotNull SocketChannel channel, @NotNull ISocketConnector connector)
            throws IOException {
        ChannelLink link = map.computeIfAbsent(channel, ChannelLink::new);
        link.setConnector(connector);
        return SelectableChannels.registerOr(channel, selector, SelectionKey.OP_CONNECT);
    }

    @Override
    public SelectionKey registerRead(@NotNull SocketChannel channel, @NotNull ISocketReader reader)
            throws IOException {
        ChannelLink link = map.computeIfAbsent(channel, ChannelLink::new);
        link.setReader(reader);
        return SelectableChannels.registerOr(channel, selector, SelectionKey.OP_READ);
    }

    @Override
    public SelectionKey registerWrite(@NotNull SocketChannel channel, @NotNull ISocketWriter writer)
            throws IOException {
        ChannelLink link = map.computeIfAbsent(channel, ChannelLink::new);
        link.setWriter(writer);
        return SelectableChannels.registerOr(channel, selector, SelectionKey.OP_WRITE);
    }

    private void cancel(SelectableChannel channel, int interestOp) {
        SelectionKey key = channel.keyFor(selector);
        if (key != null) {
            int ops = key.interestOps();
            ops = ops & ~interestOp;
            key.interestOps(ops);
        }
    }

//    @Override
//    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketAccepter accepter) {
//        ChannelLink link = map.get(channel);
//        if (link != null)
//            link.removeAccepter(accepter);
//        cancel(channel, SelectionKey.OP_ACCEPT);
//    }
//
//    @Override
//    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketConnector connector) {
//        ChannelLink link = map.get(channel);
//        if (link != null)
//            link.removeConnector(connector);
//        cancel(channel, SelectionKey.OP_CONNECT);
//    }
//
//    @Override
//    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketReader reader) {
//        ChannelLink link = map.get(channel);
//        if (link != null)
//            link.removeReader(reader);
//        cancel(channel, SelectionKey.OP_READ);
//    }
//
//    @Override
//    public void cancel(@NotNull SelectableChannel channel, @NotNull ISocketWriter writer) {
//        ChannelLink link = map.get(channel);
//        if (link != null)
//            link.removeWriter(writer);
//        cancel(channel, SelectionKey.OP_WRITE);
//    }

    @Override
    public void cancelAccept(@NotNull SelectableChannel channel) {
        ChannelLink link = map.get(channel);
        if (link != null)
            link.setAccepter(null);
        cancel(channel, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void cancelConnect(@NotNull SelectableChannel channel) {
        ChannelLink link = map.get(channel);
        if (link != null)
            link.setConnector(null);
        cancel(channel, SelectionKey.OP_CONNECT);
    }

    @Override
    public void cancelRead(@NotNull SelectableChannel channel) {
        ChannelLink link = map.get(channel);
        if (link != null)
            link.setReader(null);
        cancel(channel, SelectionKey.OP_READ);
    }

    @Override
    public void cancelWrite(@NotNull SelectableChannel channel) {
        ChannelLink link = map.get(channel);
        if (link != null)
            link.setWriter(null);
        cancel(channel, SelectionKey.OP_WRITE);
    }

    @Override
    public void cancelAll(@NotNull SelectableChannel channel) {
        ChannelLink link = map.get(channel);
        if (link != null)
            link.setWriter(null);
        cancel(channel, -1);
    }

    @Override
    public void mainLoop()
            throws IOException, InterruptedException {
        exit = false;
        while (!exit) {
            int numSelectedKeys = selector.select();
            logger.debug("selected keys: " + numSelectedKeys);
            if (numSelectedKeys == 0) {
                logger.debug("sleep 1000");
                Thread.sleep(1000);
                continue;
            }

            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

            for (; keyIterator.hasNext(); keyIterator.remove()) {
                SelectionKey key = keyIterator.next();
                SelectableChannel _channel = key.channel();

                if (key.isValid() && key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) _channel;
                    handleAccept(serverChannel);
                }

                if (key.isValid() && key.isConnectable()) {
                    assert _channel instanceof SocketChannel;
                    SocketChannel channel = (SocketChannel) _channel;
                    handleConnect(channel);
                }

                if (key.isValid() && key.isReadable()) {
                    assert _channel instanceof SocketChannel;
                    SocketChannel channel = (SocketChannel) _channel;
                    handleRead(channel);
                }

                if (key.isValid() && key.isWritable()) {
                    assert _channel instanceof SocketChannel;
                    SocketChannel channel = (SocketChannel) _channel;
                    handleWrite(channel);
                }
            } // for key
        } // whilte not-exit
    }

    void handleAccept(ServerSocketChannel serverChannel) {
        logger.debug("selected-key is acceptable: " + SocketChannels.getLocalAddress(serverChannel));
        boolean accepted = false;
        for (ISocketAccepter accepter : getAccepters(serverChannel)) {
            if (accepter == null)
                continue;
            try {
                if (accepter.accept(serverChannel)) {
                    logger.debug("accept handled by " + accepter);
                    accepted = true;
                    break;
                }
            } catch (IOException e) {
                logger.error(e, "error accepting: " + e.getMessage());
            }
        }
        if (accepted) {
            return;
        }

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
        logger.debug("selected-key is connectable: " + SocketChannels.addressInfo(channel));
        boolean handled = false;
        for (ISocketConnector connector : getConnectors(channel)) {
            if (connector == null)
                continue;
            try {
                if (connector.connect(channel)) {
                    logger.debug("connect handled by " + connector);
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
        logger.debug("selected-key is readable: " + SocketChannels.addressInfo(channel));
        long totalBytesRead = 0;
        if (channel.socket().isClosed()) {
            handleClose(channel);
            return;
        }

        List<ISocketReader> readers = new ArrayList<>(getReaders(channel));
        for (ISocketReader reader : readers) {
            if (reader == null)
                continue;
            try {
                long numBytesRead = reader.read(channel);
                if (numBytesRead != 0)
                    logger.debug("read " + numBytesRead + " by " + reader);
                totalBytesRead += numBytesRead;
            } catch (IOException e) {
                logger.error(e, "error reading: " + e.getMessage());
            }
        }

        if (totalBytesRead != 0)
            logger.debug("read " + totalBytesRead + " bytes totally");
        else {
            if (channel.socket().isClosed()) {
                handleClose(channel);
                return;
            }
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
        logger.debug("selected-key is writable: " + SocketChannels.addressInfo(channel));
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
        if (totalBytesWritten != 0)
            logger.debug("wrote " + totalBytesWritten + " bytes totally");
        else
            logger.debug("no byte have been written. ");
    }

    void handleClose(SocketChannel channel) {
        logger.debug("channel closed, cancel from selector");
        SelectionKey key = channel.keyFor(selector);
        if (key != null)
            key.cancel();
        map.remove(channel);
    }

    @Override
    public void cancel() {
        logger.debug("cancel");
        try {
            close();
        } catch (IOException e) {
            logger.error("error close", e);
        }
        this.exit = true;
    }

    @Override
    public void close()
            throws IOException {
        for (SelectableChannel _channel : map.keySet()) {
            if (_channel instanceof SocketChannel) {
                SocketChannel channel = (SocketChannel) _channel;
                logger.debug("cancel channel from selector: " + SocketChannels.addressInfo(channel));
            }
            SelectionKey key = _channel.keyFor(selector);
            if (key != null)
                key.cancel();
        }
        map.clear();
        selector.close();
    }

}
