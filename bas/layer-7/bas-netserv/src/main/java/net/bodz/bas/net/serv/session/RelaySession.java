package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.net.serv.Session;
import net.bodz.bas.parser.Command;

public class RelaySession
        extends Session {

    InetSocketAddress destAddr;

    public RelaySession(SocketChannel channel, InetSocketAddress destAddr) {
        super(channel);
        this.destAddr = destAddr;
    }

    @Override
    public void onDataReady(SocketChannel channel)
            throws IOException, ParseException {
        ByteBuffer aByte = ByteBuffer.allocate(1);
        int numBytesRead = channel.read(aByte);
        if (numBytesRead == -1)                        // The client has closed the connection
        {
            stop();
            return;
        }
        if (numBytesRead == 0)
            return;

        byte b = aByte.get();
    }

}
