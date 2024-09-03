package net.bodz.lily.security.auth;

import net.bodz.lily.security.login.LoginToken;

public interface ILoginTokenManager {

    LoginToken newToken(AuthData authData);

    LoginToken getToken(long id);

    LoginToken removeToken(long id);

}
