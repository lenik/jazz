package net.bodz.bas.net.serv;

import java.nio.channels.Channel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.session.ISocketSession;

public interface ISessionManager {

    /**
     * Retrieves a session from the manager using its ID.
     *
     * @param id The ID of the session to be retrieved.
     * @return The session associated with the given ID, or null if no such session exists.
     */
    ISocketSession getSession(@NotNull String id);

    String getSessionId(@NotNull ISocketSession session);

    default boolean contains(@NotNull ISocketSession session) {
        return getSessionId(session) != null;
    }

    ISocketSession getSessionByChannel(@NotNull Channel channel);

    /**
     * @param session The new value for the session.
     * @return session id, if the session is already added, the same id will be returned.
     */
    String addSession(@NotNull ISocketSession session);

    /**
     * Removes a session from the manager using its ID.
     *
     * @param id The ID of the session to be removed.
     * @return true if exists
     */
    boolean removeSession(@NotNull String id);

    /**
     * @return true if any exists.
     */
    default boolean removeSessionsForChannel(@NotNull Channel channel) {
        ISocketSession session = getSessionByChannel(channel);
        if (session != null)
            return removeSession(session);
        return false;
    }

    /**
     * Default method that removes a session from the manager using its instance. It first retrieves the ID associated
     * with the session and then calls {@link #removeSession(String)}.
     *
     * @param session The session to be removed.
     */
    default boolean removeSession(@NotNull ISocketSession session) {
        String id = getSessionId(session);
        return removeSession(id);
    }

    void switchSession(@NotNull String id, @NotNull ISocketSession newSession);

    default void switchSession(@NotNull ISocketSession oldsSession, @NotNull ISocketSession newSession) {
        String id = getSessionId(oldsSession);
        if (id == null)
            throw new IllegalArgumentException("the old session has not been added: " + oldsSession);
        switchSession(id, newSession);
    }

}
