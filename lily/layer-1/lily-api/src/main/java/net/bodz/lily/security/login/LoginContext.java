package net.bodz.lily.security.login;

import net.bodz.bas.t.preorder.PrefixMap;

public class LoginContext {

    ILoginTokenManager tokenManager;
    PrefixMap<String> permissions = new PrefixMap<>();

}
