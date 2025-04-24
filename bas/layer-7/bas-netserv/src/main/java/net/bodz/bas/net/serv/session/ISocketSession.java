package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketReader;
import net.bodz.bas.net.util.IClosedListener;
import net.bodz.bas.net.util.IPausedListener;
import net.bodz.bas.net.util.IResumedListener;
import net.bodz.bas.t.record.BasicColumnType;
import net.bodz.bas.t.record.IColumnType;

public interface ISocketSession
        extends ISocketReader {

    String getName();

    String getDescription();

    @NotNull
    SocketChannel getChannel();

    void pause();

    void resume()
            throws IOException;

    boolean isRunning();

    boolean isPaused();

    void addPausedListener(@NotNull IPausedListener listener);

    void removePausedListener(@NotNull IPausedListener listener);

    void addResumedListener(@NotNull IResumedListener listener);

    void removeResumedListener(@NotNull IResumedListener listener);

    void open()
            throws IOException;

    void close()
            throws IOException;

    boolean isClosed();

    void addCloseListener(@NotNull IClosedListener listener);

    void removeCloseListener(@NotNull IClosedListener listener);

    IColumnType<ISocketSession, String> NAME //
            = BasicColumnType.ofProperty(ISocketSession.class, "name");
    IColumnType<ISocketSession, String> DESCRIPTION //
            = BasicColumnType.ofProperty(ISocketSession.class, "description");
    IColumnType<ISocketSession, SocketChannel> CHANNEL //
            = BasicColumnType.ofProperty(ISocketSession.class, "channel");

}
