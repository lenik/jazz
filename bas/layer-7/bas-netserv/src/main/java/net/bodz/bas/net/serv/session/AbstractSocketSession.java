package net.bodz.bas.net.serv.session;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.bodz.bas.c.java.nio.channels.SocketChannels;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.io.ISocketPoller;
import net.bodz.bas.net.util.CloseSupport;
import net.bodz.bas.net.util.CompositeSettings;
import net.bodz.bas.net.util.IClosedListener;
import net.bodz.bas.net.util.IPausedListener;
import net.bodz.bas.net.util.IResumedListener;
import net.bodz.bas.net.util.ISettingParsable;
import net.bodz.bas.net.util.PauseEvent;
import net.bodz.bas.net.util.ResumeEvent;
import net.bodz.bas.net.util.SocketBuffer;
import net.bodz.bas.repr.path.IBasicTokenQueue;
import net.bodz.bas.t.variant.IVariant;

public abstract class AbstractSocketSession
        implements ISocketSession,
                   ISettingParsable {

    static final Logger logger = LoggerFactory.getLogger(AbstractSocketSession.class);

    String name;
    String description;

    protected final SocketChannel channel;
    protected final ISocketPoller poller;

    protected final SocketBuffer buffer;

    boolean paused;
    List<IPausedListener> pausedListeners = new ArrayList<>(1);
    List<IResumedListener> resumedListeners = new ArrayList<>(1);

    boolean closed;
    CloseSupport closeSupport = new CloseSupport(this);

    CompositeSettings cset = new CompositeSettings();

    public AbstractSocketSession(String name, @NotNull SocketChannel channel, @NotNull ISocketPoller poller) {
        this.name = name;
        this.channel = channel;
        this.poller = poller;
        this.buffer = new SocketBuffer(channel, poller);
        cset.add(null, buffer);
    }

    public String getTypeName() {
        String type = getClass().getSimpleName();
        if (type.endsWith("Session"))
            type = type.substring(0, type.length() - 7);
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected String getLogPrefix() {
        StringBuilder buf = new StringBuilder(40);
        buf.append(getName());
        // hub:2000 ⇀ :24689...
        String info = SocketChannels.getConnectionShortInfo(channel);
        buf.append(info);
        if (info.contains(SocketChannels.ARROW))
            buf.append("…");
        buf.append("  ");
        return buf.toString();
    }

    @NotNull
    @Override
    public SocketChannel getChannel() {
        return channel;
    }

    @Override
    public void pause() {
        if (!paused) {
            buffer.stopRead();
            buffer.disableWriter();
            paused = true;
            fireOnPaused();
        }
    }

    @Override
    public void resume()
            throws IOException {
        if (paused) {
            buffer.startRead();
            buffer.enableWriter();
            paused = false;
            fireOnResumed();
        }
    }

    @Override
    public boolean isRunning() {
        return !paused;
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

    @Override
    public void addPausedListener(@NotNull IPausedListener listener) {
        pausedListeners.add(listener);
    }

    @Override
    public void removePausedListener(@NotNull IPausedListener listener) {
        pausedListeners.remove(listener);
    }

    protected void fireOnPaused() {
        PauseEvent event = new PauseEvent(this);
        for (IPausedListener l : pausedListeners)
            l.onPaused(event);
    }

    @Override
    public void addResumedListener(@NotNull IResumedListener listener) {
        resumedListeners.add(listener);
    }

    @Override
    public void removeResumedListener(@NotNull IResumedListener listener) {
        resumedListeners.remove(listener);
    }

    protected void fireOnResumed() {
        ResumeEvent event = new ResumeEvent(this);
        for (IResumedListener l : resumedListeners)
            l.onResumed(event);
    }

    @Override
    public void open()
            throws IOException {
        if (closed)
            throw new IllegalStateException("can't reopen");
        buffer.startRead();
        buffer.enableWriter();
    }

    @Override
    public void close() {
        buffer.disableWriter();
        buffer.stopRead();

        try {
            channel.socket().close();
        } catch (IOException e) {
            logger.error("error close the socket: " + e.getMessage(), e);
        } finally {
            closed = true;
        }
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void addCloseListener(@NotNull IClosedListener listener) {
        closeSupport.addCloseListener(listener);
    }

    @Override
    public void removeCloseListener(@NotNull IClosedListener listener) {
        closeSupport.removeCloseListener(listener);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(100);

        String type = getClass().getSimpleName();
        if (type.endsWith("Session"))
            type = type.substring(0, type.length() - 7);
        buf.append(type);

        String local = SocketChannels.getLocalAddress(channel);
        String remote = SocketChannels.getRemoteAddress(channel);
        buf.append(local);
        buf.append(" -> ").append(remote);

        return buf.toString();
    }

    @NotNull
    @Override
    public Set<String> getSettingNames() {
        return cset.getSettingNames();
    }

    @Override
    public Object getSetting(@NotNull String name) {
        return cset.getSetting(name);
    }

    @Override
    public boolean setSetting(@NotNull String name, Object value) {
        return cset.setSetting(name, value);
    }

    @Override
    public boolean parseSetting(@NotNull String name, @NotNull IBasicTokenQueue args)
            throws ParseException {
        return cset.parseSetting(name, args);
    }

    @Override
    public boolean setSettingVar(@NotNull String name, @NotNull IVariant var) {
        return cset.setSettingVar(name, var);
    }

}