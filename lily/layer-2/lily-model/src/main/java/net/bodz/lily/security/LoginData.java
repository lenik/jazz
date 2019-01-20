package net.bodz.lily.security;

import javax.servlet.http.HttpSession;

import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.t.preorder.PrefixMap;

public class LoginData {

    public static final String ATTRIBUTE_KEY = LoginData.class.getName();

    User user;
    PrefixMap<String> permissions = new PrefixMap<>();

    public LoginData() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isIgnoreAcl() {
        if (user == null)
            return true;
        if (user.isSuperUser())
            return true;
        return false;
    }

    public PrefixMap<String> getPermissions() {
        return permissions;
    }

    public void saveInSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            throw new IllegalStateException("No session");
        else
            saveInSession(session);
    }

    public void saveInSession(HttpSession session) {
        synchronized (session) {
            session.setAttribute(LoginData.ATTRIBUTE_KEY, this);
        }
    }

    /**
     * @return Non-<code>null</code> instance.
     */
    public static LoginData fromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        else
            return fromSession(session);
    }

    /**
     * @return Non-<code>null</code> instance.
     */
    public static LoginData fromSession(HttpSession session) {
        synchronized (session) {
            LoginData data = (LoginData) session.getAttribute(LoginData.ATTRIBUTE_KEY);
            if (data == null) {
                data = new LoginData();
                session.setAttribute(LoginData.ATTRIBUTE_KEY, data);
            }
            return data;
        }
    }

    public static void removeFromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null)
            removeFromSession(session);
    }

    public static void removeFromSession(HttpSession session) {
        synchronized (session) {
            session.removeAttribute(LoginData.ATTRIBUTE_KEY);
        }
    }

}
