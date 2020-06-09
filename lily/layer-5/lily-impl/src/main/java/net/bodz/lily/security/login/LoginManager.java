package net.bodz.lily.security.login;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.apache.commons.codec.digest.DigestUtils;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.json.JsonResponse;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserSecretMapper;
import net.bodz.lily.security.impl.UserSecretMask;
import net.bodz.lily.security.login.ILoginResolver.Result;

public class LoginManager
        extends LoginTokenManager
        implements IPathDispatchable {

    // long timeout = 3600_000;
    DataContext dataContext;

    ILoginResolverProvider resolverProvider;
    List<ILoginListener> loginListeners = new ArrayList<>();

    public LoginManager(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;

        resolverProvider = new IndexedLoginResolverProvider(dataContext);
        for (ILoginListener listener : ServiceLoader.load(ILoginListener.class)) {
            loginListeners.add(listener);
        }
    }

    int saltTimeout = 10_000; // 10 seconds
    SaltGenerator saltgen = new SaltGenerator(saltTimeout);

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;

        switch (token) {
        case "init":
            target = initiate(q);
            break;

        case "exit":
        case "quit":
            target = logout();
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, target, tokens);
        return null;
    }

    LoginResult initiate(IVariantMap<String> q) {
        LoginResult result = new LoginResult();
        String clientResp = q.getString("cr");
        if (clientResp == null) { // pass 1:
            String serverChallenge = saltgen.getCodeForNow();
            result.setServerChallenge(serverChallenge);

            Integer userId = q.getInt("user");
            if (userId != null) {
                String password = getAnyPassword(userId);
                if (password != null) {
                    String ecr = DigestUtils.shaHex(serverChallenge + password + serverChallenge);
                    result.setExpectedClientResp(ecr);
                }
            }
            return result;
        }

        // pass 2:
        SecretCrChecker signChecker = new SecretCrChecker();
        for (ILoginResolver resolver : resolverProvider.getResolvers()) {
            Result rr = resolver.login(signChecker, q);
            if (rr.isSuccess()) {
                User user = rr.getUser();
                result.token = new LoginToken(this, 123, user);
                result.succeed();
            }
        }
        return result.fail();
    }

    String getAnyPassword(int userId) {
        UserSecretMapper secretMapper = dataContext.getMapper(UserSecretMapper.class);
        List<UserSecret> secrets = secretMapper.filter(new UserSecretMask().userId(userId), SelectOptions.ALL);
        for (UserSecret secret : secrets) {
            String password = secret.getPassword();
            if (password != null)
                break;
        }
        return null;
    }

    JsonResponse logout() {
        JsonResponse r = new JsonResponse();
        LoginToken token = LoginToken.fromSession();
        if (token != null) {
            User user = token.getUser();
            for (ILoginListener listener : loginListeners)
                try {
                    listener.logout(user);
                } catch (Exception e) {
                    return r.fail(e, "Can't logged out %s: %s", user.toString(), listener + ": " + e.getMessage());
                }
            LoginToken.clearSession();
        }
        return r.succeed();
    }

}
