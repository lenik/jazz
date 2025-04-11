package net.bodz.bas.net.serv;

import java.nio.channels.Channel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketReader;

public interface ISession
        extends ISocketReader {

    @NotNull
    String getId();

    @NotNull
    Channel getChannel();

    void close();

    boolean isClosed();

}
