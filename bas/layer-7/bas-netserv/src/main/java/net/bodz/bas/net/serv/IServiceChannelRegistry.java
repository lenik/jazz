package net.bodz.bas.net.serv;

import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;

public interface IServiceChannelRegistry {

    String registerChannel(@NotNull SocketChannel channel, @NotNull String protocol);

    void removeChannel(@NotNull SocketChannel channel, String protocol);

    void removeChannel(@NotNull String id);

    default void removeChannel(@NotNull SocketChannel channel) {
        removeChannel(channel, null);
    }

}
