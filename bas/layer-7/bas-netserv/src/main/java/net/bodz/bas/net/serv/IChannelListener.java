package net.bodz.bas.net.serv;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

public interface IChannelListener {

    void onConnect(@NotNull SocketChannel channel);

    void onDataReady(@NotNull SocketChannel channel)
            throws IOException, ParseException;

    void onError(@NotNull SocketChannel channel, Throwable e);

    void onDisconnect(@NotNull SocketChannel channel);

}
