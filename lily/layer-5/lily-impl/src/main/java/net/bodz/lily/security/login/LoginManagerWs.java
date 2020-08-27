package net.bodz.lily.security.login;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

public class LoginManagerWs
        implements IPathDispatchable {

    LoginManager impl;

    public LoginManagerWs(LoginManager impl) {
        this.impl = impl;
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
            target = impl.initiateLogin(q);
            break;

        case "login":
            target = impl.login(q);
            break;

        case "login-by-phone":
            target = impl.loginByPhone(q.getString("phone"), q.getString("e_cr"));
            break;

        case "login-by-email":
            target = impl.loginByPhone(q.getString("email"), q.getString("e_cr"));
            break;

        case "exit":
        case "quit":
        case "logout":
            target = impl.logout();
            break;

        case "verify-phone":
            target = impl.verifyPhone(q.getString("phone"), q.getString("usage"));
            break;

        case "verify-email":
            target = impl.verifyEmail(q.getString("email"), q.getString("usage"));
            break;

        case "register-by-phone":
            target = impl.registerByPhone(q.getString("phone"), q.getString("e_cr"), q.getString("passwd"));
            break;

        case "register-by-email":
            target = impl.registerByEmail(q.getString("email"), q.getString("e_cr"), q.getString("passwd"));
            break;

        case "reset-password-by-phone":
            target = impl.resetPasswordByPhone(q.getString("phone"), q.getString("e_cr"), q.getString("passwd"));
            break;

        case "reset-password-by-email":
            target = impl.resetPasswordByEmail(q.getString("email"), q.getString("e_cr"), q.getString("passwd"));
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        return null;
    }

}
