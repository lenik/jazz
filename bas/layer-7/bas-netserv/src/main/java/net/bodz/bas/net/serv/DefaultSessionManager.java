package net.bodz.bas.net.serv;

import java.nio.channels.Channel;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.session.ISocketSession;
import net.bodz.bas.t.pool.IPool;
import net.bodz.bas.t.pool.IntSetPool;

public class DefaultSessionManager
        implements ISessionManager {

    final IPool<Integer> idPool = new IntSetPool(10000);

    final Map<String, ISocketSession> byId = new LinkedHashMap<>();
    final Map<Channel, ISocketSession> byChannel = new IdentityHashMap<>();
    final Map<ISocketSession, String> rindex = new IdentityHashMap<>();

    @Override
    public ISocketSession getSession(@NotNull String id) {
        return byId.get(id);
    }

    @Override
    public String getSessionId(@NotNull ISocketSession session) {
        return rindex.get(session);
    }

    @Override
    public ISocketSession getSessionByChannel(@NotNull Channel channel) {
        return byChannel.get(channel);
    }

    @Override
    public String addSession(@NotNull ISocketSession session) {
        String id = rindex.get(session);
        if (id != null)
            return id;

        ISocketSession prev = byChannel.get(session.getChannel());
        if (prev != null)
            throw new IllegalStateException("channel duplicated");

        id = idPool.allocate().toString();
        byId.put(id, session);
        byChannel.put(session.getChannel(), session);
        rindex.put(session, id);
        return id;
    }

    @Override
    public boolean removeSession(@NotNull String id) {
        ISocketSession session = byId.remove(id);
        if (session == null)
            return false;
        byChannel.remove(session.getChannel());
        rindex.remove(session);
        return true;
    }

    @Override
    public boolean removeSessionsForChannel(@NotNull Channel channel) {
        return ISessionManager.super.removeSessionsForChannel(channel);
    }

    @Override
    public void replaceSession(@NotNull String id, @NotNull ISocketSession newSession) {
        ISocketSession oldSession = byId.get(id);
        if (oldSession == newSession)
            return;
        if (oldSession == null)
            throw new IllegalArgumentException("id wasn't used: " + id);

        ISocketSession prev = byChannel.get(newSession.getChannel());
        if (prev != null)
            throw new IllegalArgumentException("channel duplicated");

        byId.put(id, newSession);

        byChannel.remove(oldSession.getChannel());
        rindex.remove(oldSession);

        byChannel.put(newSession.getChannel(), newSession);
        rindex.put(newSession, id);
    }

}