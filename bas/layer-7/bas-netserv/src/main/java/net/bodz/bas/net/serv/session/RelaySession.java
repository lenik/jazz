package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketConnector;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.serv.AbstractSession;
import net.bodz.bas.net.io.ISocketPoller;

public class RelaySession
        extends AbstractSession {

    InetSocketAddress targetAddr;

    public RelaySession(String id, SocketChannel channel, @NotNull InetSocketAddress targetAddr, ISocketPoller poller)
            throws IOException {
        super(id, channel);
        this.targetAddr = targetAddr;

        SocketChannel targetChannel = SocketChannel.open(targetAddr);
        targetChannel.configureBlocking(false);

        poller.register(targetChannel, (ISocketConnector) this::connectTarget);
        poller.register(targetChannel, (ISocketReader) this::readTarget);
    }

    boolean connectTarget(SocketChannel channel)
            throws IOException {
        return true;
    }

    boolean readTarget(SocketChannel channel)
            throws IOException {
        return true;
    }

    @Override
    public boolean read(SocketChannel channel)
            throws IOException, ParseException {
        ByteBuffer byteBuf = ByteBuffer.allocate(4096);
        int numBytesRead = channel.read(byteBuf);
        switch (numBytesRead) {
            case -1:
                close();
            case 0:
                return true;
        }

        byteBuf.flip();
        return true;
    }

}
