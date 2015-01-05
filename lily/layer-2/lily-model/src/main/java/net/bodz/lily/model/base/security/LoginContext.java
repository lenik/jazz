package net.bodz.lily.model.base.security;

import net.bodz.bas.site.vhost.IVirtualHost;
import net.bodz.bas.t.preorder.PrefixMap;

public class LoginContext {

    public static final String ATTRIBUTE_KEY = LoginContext.class.getName();

    public IVirtualHost virtualHost;
    public User user;
    public PrefixMap<String> permissions;

}
