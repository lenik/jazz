package net.bodz.lily.security.login;

import java.util.HashMap;
import java.util.Map;

public class LoginTokenManager
        implements
            ILoginTokenManager {

    long nextId = 1;

    /** token id => token */
    Map<Long, LoginToken> tokens = new HashMap<>();

    @Override
    public synchronized void saveToken(LoginToken token) {
        long id = nextId++;
        token.id = id;
        tokens.put(id, token);
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
