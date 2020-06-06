package net.bodz.lily.security.login;

import java.util.HashMap;
import java.util.Map;

import net.bodz.lily.security.User;

public class LoginTokenManager
        implements ILoginTokenManager {

    long nextId = 1;

    /** token id => token */
    Map<Long, LoginToken> tokens = new HashMap<>();

    @Override
    public LoginToken newToken(User user) {
        long id = nextId++;
        LoginToken token = new LoginToken(this, id, user);
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
