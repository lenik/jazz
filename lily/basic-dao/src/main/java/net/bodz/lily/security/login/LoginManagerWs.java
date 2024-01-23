package net.bodz.lily.security.login;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;

public class LoginManagerWs
        implements
            IPathDispatchable {

    public LoginManagerWs() {
    }

    ILoginManager getLoginManager() {
        return LoginManagers.requireLoginManager();
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;

        switch (token) {
        case "init":
            target = getLoginManager().initiateLogin(q);
            break;

        case "login":
            target = getLoginManager().login(q);
            break;

        case "login-by-phone":
            target = getLoginManager().loginByPhone(q.getString("phone"), q.getString("e_cr"));
            break;

        case "login-by-email":
            target = getLoginManager().loginByPhone(q.getString("email"), q.getString("e_cr"));
            break;

        case "status":
            target = LoginToken.fromRequest();
            if (target == null) {
                JsonResult notLogin = new JsonResult();
                notLogin.fail("Not logged in.");
                target = notLogin;
            }
            break;

        case "exit":
        case "quit":
        case "logout":
            target = getLoginManager().logout();
            break;

        case "verify-phone":
            target = getLoginManager().verifyPhone(q.getString("phone"), q.getString("usage"));
            break;

        case "verify-email":
            target = getLoginManager().verifyEmail(q.getString("email"), q.getString("usage"));
            break;

        case "register-by-phone":
            target = getLoginManager().registerByPhone(q.getString("phone"), q.getString("e_cr"),
                    q.getString("passwd"));
            break;

        case "register-by-email":
            target = getLoginManager().registerByEmail(q.getString("email"), q.getString("e_cr"),
                    q.getString("passwd"));
            break;

        case "reset-password-by-phone":
            target = getLoginManager().resetPasswordByPhone(q.getString("phone"), q.getString("e_cr"),
                    q.getString("passwd"));
            break;

        case "reset-password-by-email":
            target = getLoginManager().resetPasswordByEmail(q.getString("email"), q.getString("e_cr"),
                    q.getString("passwd"));
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        return null;
    }

}
