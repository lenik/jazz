package net.bodz.bas.net.serv.session;

import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.t.record.BasicColumnType;
import net.bodz.bas.t.record.IColumnType;

public interface ISocketSession
        extends ISocketReader {

//    String getTitle();

    @NotNull
    SocketChannel getChannel();

    void close();

    boolean isClosed();

    IColumnType<ISocketSession, SocketChannel> CHANNEL //
            = BasicColumnType.ofProperty(ISocketSession.class, "channel");

}
