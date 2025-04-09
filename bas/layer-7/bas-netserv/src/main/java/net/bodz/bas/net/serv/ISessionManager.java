package net.bodz.bas.net.serv;

import net.bodz.bas.meta.decl.NotNull;

public interface ISessionManager {

    @NotNull
    String addSession(@NotNull ISession session);

    /**
     * Gets the ID associated with a given session.
     *
     * @param session The session for which to retrieve the ID.
     * @return The ID associated with the session.
     */
    @NotNull
    String getSessionId(@NotNull ISession session);

    /**
     * Retrieves a session from the manager using its ID.
     *
     * @param id The ID of the session to be retrieved.
     * @return The session associated with the given ID, or null if no such session exists.
     */
    ISession getSession(@NotNull String id);

    /**
     * Sets a session in the manager using its ID and new value.
     *
     * @param id      The ID of the session to be set.
     * @param session The new value for the session.
     */
    void setSession(@NotNull String id, @NotNull ISession session);

    /**
     * Removes a session from the manager using its ID.
     *
     * @param id The ID of the session to be removed.
     */
    void removeSession(@NotNull String id);

    /**
     * Default method that removes a session from the manager using its instance. It first retrieves the ID associated
     * with the session and then calls {@link #removeSession(String)}.
     *
     * @param session The session to be removed.
     */
    default void removeSession(@NotNull ISession session) {
        String id = getSessionId(session);
        removeSession(id);
    }

    default void replaceSession(ISession session, ISession newSession) {
        String id = getSessionId(session);
        setSession(id, newSession);
    }

}
