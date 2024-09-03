package net.bodz.lily.security.login;

import java.util.function.Predicate;

import net.bodz.bas.crypto.trans.IFlyingSupport;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.auth.AuthData;

public class LoginManager
        extends LoginTokenManager
        implements
            ILoginManager,
            IFlyingSupport {

    // long timeout = 3600_000;
    DataContext dataContext;
    LoginListenerManager listeners;

    public LoginManager(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
        this.listeners = LoginListenerManager.load(dataContext);
    }

    @Override
    public LoginToken logIn(AuthData authData) {
        LoginToken token = newToken(authData);
        listeners.fireLogIn(token);
        token.saveInSession();
        return token;
    }

    @Override
    public void logOut(LoginToken token) {
        listeners.fireLogOut(token);
        LoginToken.removeFromRequest();
    }

    @Override
    public void logOutFor(Predicate<LoginToken> p) {
        LoginToken token = LoginToken.fromRequest();
        if (token != null) {
            listeners.fireLogOut(token); // ignore any failure.
            LoginToken.removeFromRequest();
        }
    }

}
