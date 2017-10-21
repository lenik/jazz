package net.bodz.lily.model.base.security;

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
        session.setAttribute(LoginData.ATTRIBUTE_KEY, this);
    }

    public static LoginData fromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        else
            return fromSession(session);
    }

    public static LoginData fromSession(HttpSession session) {
        return (LoginData) session.getAttribute(LoginData.ATTRIBUTE_KEY);
    }

    public static void removeFromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session != null)
            removeFromSession(session);
    }

    public static void removeFromSession(HttpSession session) {
        session.removeAttribute(LoginData.ATTRIBUTE_KEY);
    }

}
