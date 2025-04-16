package net.bodz.bas.net.serv;

import java.nio.channels.Channel;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.net.serv.session.ISession;

public interface ISessionManager {

    /**
     * Retrieves a session from the manager using its ID.
     *
     * @param id The ID of the session to be retrieved.
     * @return The session associated with the given ID, or null if no such session exists.
     */
    ISession getSession(@NotNull String id);

    ISession getSession(@NotNull Channel channel);

    /**
     * @param session The new value for the session.
     * @return true if add new, false if updating
     */
    boolean addSession(@NotNull ISession session);

    /**
     * Removes a session from the manager using its ID.
     *
     * @param id The ID of the session to be removed.
     * @return true if exists
     */
    boolean removeSession(@NotNull String id);

    /**
     * @return true if exists
     */
    boolean removeSession(@NotNull Channel channel);

    /**
     * Default method that removes a session from the manager using its instance. It first retrieves the ID associated
     * with the session and then calls {@link #removeSession(String)}.
     *
     * @param session The session to be removed.
     */
    default boolean removeSession(@NotNull ISession session) {
        String id = session.getSessionId();
        return removeSession(id);
    }

    default void replaceSession(ISession session, ISession newSession) {
        String id = session.getSessionId();
        String newId = newSession.getSessionId();
        if (!id.equals(newId))
            throw new IllegalArgumentException("replace with a session different id");
        removeSession(id);
        addSession(newSession);
    }

}
