package net.bodz.lily.security.login;

import java.util.HashMap;
import java.util.Map;

import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.auth.ILoginTokenManager;

public class LoginTokenManager
        implements
            ILoginTokenManager {

    long nextId = 1;

    /** token id => token */
    Map<Long, LoginToken> tokens = new HashMap<>();

    @Override
    public synchronized LoginToken newToken(AuthData authData) {
        LoginToken token = new LoginToken(authData);
        long id = nextId++;
        token.id = id;
        tokens.put(id, token);
        return token;
    }

    @Override
    public LoginToken getToken(long id) {
        return tokens.get(id);
    }

    @Override
    public LoginToken removeToken(long id) {
        return tokens.remove(id);
    }

}
