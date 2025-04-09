package net.bodz.bas.net.serv;

import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.meta.decl.NotNull;

public class DefaultSessionManager
        implements ISessionManager {

    Map<String, Session> sessionMap = new LinkedHashMap<>();
    Map<ISession, String> idMap = new IdentityHashMap<>();
    int nextId = 1;

    @NotNull
    public String addSession(@NotNull ISession _session) {
        if (!(_session instanceof Session))
            throw new IllegalArgumentException();
        Session session = (Session) _session;
        String id = String.valueOf(nextId++);
        idMap.put(_session, id);
        sessionMap.put(id, session);
        return id;
    }

    @NotNull
    @Override
    public String getSessionId(@NotNull ISession session) {
        return idMap.get(session);
    }

    @Override
    public ISession getSession(@NotNull String id) {
        return sessionMap.get(id);
    }

    @Override
    public void setSession(@NotNull String id, @NotNull ISession _session) {
        if (!(_session instanceof Session))
            throw new IllegalArgumentException();
        Session session = (Session) _session;

        ISession oldSession = sessionMap.get(id);
        if (oldSession != null)
            idMap.remove(oldSession);

        sessionMap.put(id, session);
        idMap.put(_session, id);
    }

    @Override
    public void removeSession(@NotNull String id) {
        ISession session = sessionMap.remove(id);
        if (session != null)
            idMap.remove(session);
    }

}
