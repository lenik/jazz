package net.bodz.lily.security.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserMapper;

public class LoginManager
        implements IPathDispatchable {

    // long timeout = 3600_000;

    /** token id => token */
    Map<Long, LoginToken> tokens = new HashMap<>();

    UserMapper userMapper;

    public LoginManager(DataContext dataContext) {
        userMapper = dataContext.getMapper(UserMapper.class);
    }

    public synchronized LoginToken getToken(long id) {
        return tokens.get(id);
    }

    public synchronized LoginToken getToken(HttpSession session) {
        return (LoginToken) session.getAttribute(LoginToken.ATTRIBUTE_NAME);
    }

    public LoginToken getTokenFromSession() {
        HttpSession session = CurrentHttpService.getSession();
        if (session == null)
            return null;
        return getToken(session);
    }

    public synchronized void loginToSession(LoginToken token) {
        HttpSession session = CurrentHttpService.getSession();
        if (session == null)
            throw new NullPointerException("session");
        LoginToken prev = getToken(session);
        if (prev != null)
            tokens.remove(prev.getId()); // invalidates overrided tokens
        session.setAttribute(LoginToken.ATTRIBUTE_NAME, token);
        tokens.put(token.getId(), token);
    }

    public synchronized void logoutFromSession() {
        HttpSession session = CurrentHttpService.getSession();
        if (session == null)
            return;
        LoginToken token = (LoginToken) session.getAttribute(LoginToken.ATTRIBUTE_NAME);
        if (token == null)
            return;
        tokens.remove(token.getId());
        session.removeAttribute(LoginToken.ATTRIBUTE_NAME);
    }

    int saltTimeout = 10_000; // 10 seconds
    SaltGenerator saltgen = new SaltGenerator(saltTimeout);
    SecretChallenger secretChallenger = new SecretChallenger(saltgen, "", saltTimeout);

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        AjaxResult result = new AjaxResult();

        switch (token) {
        case "init":
            String cr = q.getString("cr");
            if (cr == null) { // pass 1:
                String sc = saltgen.compute();
                result.set("challengeCode", sc);
                target = result.succeed();
                break;
            }
            // pass 2:
            User attemptUser = getAttemptUser(q);
            if (attemptUser == null) {
                target = result.fail("No such user: " + q.getString("user.id"));
                break;
            }

            UserSecret secret = attemptUser.getSecret();
            if (secret == null) {
                target = result.fail("User password isn't set: " + attemptUser.getId());
                break;
            }

            int ridx = saltgen.rcheck(cr);
            if (ridx == -1) {
                target = result.fail("challenge failed.");
                break;
            }

            result.set("user", attemptUser);
            result.set("token", 0);
            result.set("next", 0); // TODO merge into token
            result.set("timeout", 0);

            target = result.succeed();
            break;

        case "exit":
        case "quit":
            logoutFromSession();
            target = result.succeed();
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, target, tokens);
        return null;
    }

    User getAttemptUser(IVariantMap<String> q) {
        Integer userId = q.getInt("user.id");
        if (userId == null)
            throw new NullPointerException("user.id");
        User user = userMapper.select(userId);
        return user;
    }

}
