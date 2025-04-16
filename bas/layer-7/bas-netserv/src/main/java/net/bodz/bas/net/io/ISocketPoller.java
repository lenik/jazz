package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;

public interface ISocketPoller {

    void register(@NotNull ServerSocketChannel channel, @NotNull ISocketAccepter listener)
            throws IOException;

    void register(@NotNull SocketChannel channel, @NotNull ISocketConnector listener)
            throws IOException;

    void register(@NotNull SocketChannel channel, @NotNull ISocketReader listener)
            throws IOException;

    void register(@NotNull SocketChannel channel, @NotNull ISocketWriter writer)
            throws IOException;

    void cancel(@NotNull SelectableChannel channel, @NotNull ISocketAccepter accepter);

    void cancel(@NotNull SelectableChannel channel, @NotNull ISocketConnector connector);

    void cancel(@NotNull SelectableChannel channel, @NotNull ISocketReader reader);

    void cancel(@NotNull SelectableChannel channel, @NotNull ISocketWriter writer);

    void mainLoop()
            throws IOException;

    void cancel();

}
