package net.bodz.lily.security.login;

public interface ILoginTokenManager {

    void saveToken(LoginToken token);

    LoginToken getToken(long id);

    LoginToken removeToken(long id);

}
