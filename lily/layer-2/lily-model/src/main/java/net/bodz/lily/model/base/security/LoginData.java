package net.bodz.lily.model.base.security;

import javax.servlet.http.HttpSession;

import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.t.preorder.PrefixMap;

public class LoginData {

    public static final String ATTRIBUTE_KEY = LoginData.class.getName();

    public IVirtualHost virtualHost;
    public User user;

    public PrefixMap<String> permissions;

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

}
