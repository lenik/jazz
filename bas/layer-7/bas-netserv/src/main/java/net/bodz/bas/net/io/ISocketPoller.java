package net.bodz.bas.net.io;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;

public interface ISocketPoller {

//    SelectionKey register(@NotNull ServerSocketChannel channel, @NotNull ISocketAccepter listener)
//            throws IOException;
//
//    SelectionKey register(@NotNull SocketChannel channel, @NotNull ISocketConnector listener)
//            throws IOException;
//
//    SelectionKey register(@NotNull SocketChannel channel, @NotNull ISocketReader listener)
//            throws IOException;
//
//    SelectionKey register(@NotNull SocketChannel channel, @NotNull ISocketWriter writer)
//            throws IOException;
//
//    void cancel(@NotNull SelectableChannel channel, @NotNull ISocketAccepter accepter);
//
//    void cancel(@NotNull SelectableChannel channel, @NotNull ISocketConnector connector);
//
//    void cancel(@NotNull SelectableChannel channel, @NotNull ISocketReader reader);
//
//    void cancel(@NotNull SelectableChannel channel, @NotNull ISocketWriter writer);

    SelectionKey registerAccept(@NotNull ServerSocketChannel channel, @NotNull ISocketAccepter listener)
            throws IOException;

    SelectionKey registerConnect(@NotNull SocketChannel channel, @NotNull ISocketConnector listener)
            throws IOException;

    SelectionKey registerRead(@NotNull SocketChannel channel, @NotNull ISocketReader listener)
            throws IOException;

    SelectionKey registerWrite(@NotNull SocketChannel channel, @NotNull ISocketWriter writer)
            throws IOException;

    void cancelAccept(@NotNull SelectableChannel channel);

    void cancelConnect(@NotNull SelectableChannel channel);

    void cancelRead(@NotNull SelectableChannel channel);

    void cancelWrite(@NotNull SelectableChannel channel);

    void cancelAll(@NotNull SelectableChannel channel);

    void mainLoop()
            throws IOException, InterruptedException;

    void cancel();

}
