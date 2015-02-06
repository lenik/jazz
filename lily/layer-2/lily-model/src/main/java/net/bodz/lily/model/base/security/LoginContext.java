package net.bodz.lily.model.base.security;

import javax.servlet.http.HttpSession;

import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.t.preorder.PrefixMap;

public class LoginContext {

    // public static final String ATTRIBUTE_KEY = "loginContext";
    public static final String ATTRIBUTE_KEY = LoginContext.class.getName();

    public IVirtualHost virtualHost;
    public User user;

    public PrefixMap<String> permissions;

    public static LoginContext fromSession() {
        HttpSession session = CurrentHttpService.getSessionOpt();
        if (session == null)
            return null;
        else
            return fromSession(session);
    }

    public static LoginContext fromSession(HttpSession session) {
        return (LoginContext) session.getAttribute(LoginContext.ATTRIBUTE_KEY);
    }

}
