package net.bodz.lily.security.login;

import net.bodz.lily.security.User;

public interface ILoginTokenManager {

    LoginToken newToken(User user);

    LoginToken getToken(long id);

    LoginToken removeToken(long id);

}
