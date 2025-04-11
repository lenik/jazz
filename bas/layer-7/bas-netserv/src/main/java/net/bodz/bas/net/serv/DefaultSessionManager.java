package net.bodz.bas.net.serv;

import java.nio.channels.Channel;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;

public class DefaultSessionManager
        implements ISessionManager {

    Map<String, ISession> sessionMap = new LinkedHashMap<>();
    Map<Channel, ISession> channelMap = new IdentityHashMap<>();

    @Override
    public ISession getSession(@NotNull String id) {
        return sessionMap.get(id);
    }

    @Override
    public ISession getSession(@NotNull Channel channel) {
        return channelMap.get(channel);
    }

    @Override
    public boolean addSession(@NotNull ISession session) {
        String id = session.getId();
        ISession oldSession = sessionMap.get(id);
        if (oldSession != null) {
            sessionMap.remove(oldSession.getId());
            channelMap.remove(oldSession.getChannel());
        }
        sessionMap.put(session.getId(), session);
        channelMap.put(session.getChannel(), session);
        return oldSession == null;
    }

    @Override
    public boolean removeSession(@NotNull String id) {
        ISession session = sessionMap.remove(id);
        if (session == null)
            return false;
        channelMap.remove(session.getChannel());
        return true;
    }

    @Override
    public boolean removeSession(Channel channel) {
        ISession session = channelMap.remove(channel);
        if (session == null)
            return false;
        sessionMap.remove(session.getId());
        return true;
    }

}