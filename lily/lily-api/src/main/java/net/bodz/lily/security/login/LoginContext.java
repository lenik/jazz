package net.bodz.lily.security.login;

import net.bodz.bas.t.preorder.PrefixMap;
import net.bodz.lily.security.auth.ILoginTokenManager;

public class LoginContext {

    ILoginTokenManager tokenManager;
    PrefixMap<String> permissions = new PrefixMap<>();

}
