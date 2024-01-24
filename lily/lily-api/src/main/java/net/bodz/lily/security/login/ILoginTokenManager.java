package net.bodz.lily.security.login;

import net.bodz.lily.security.IUser;

public interface ILoginTokenManager {

    LoginToken newToken(IUser user);

    LoginToken getToken(long id);

    LoginToken removeToken(long id);

}
