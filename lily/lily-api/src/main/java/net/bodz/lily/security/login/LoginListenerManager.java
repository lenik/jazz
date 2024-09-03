package net.bodz.lily.security.login;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.inject.AbstractServiceManager;
import net.bodz.bas.inject.Injector;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.lily.security.IUser;

public class LoginListenerManager
        extends AbstractServiceManager<ILoginListener> {

    static final Logger logger = LoggerFactory.getLogger(LoginListenerManager.class);

    LoginListenerManager(DataContext dataContext) {
        super(ILoginListener.class, createInjector(dataContext));
    }

    public static LoginListenerManager load(DataContext dataContext)
            throws LoadException {
        return new LoginListenerManager(dataContext);
    }

    static Injector createInjector(DataContext dataContext) {
        Injector injector = new Injector();
        injector.setExplicitConstructor(true); // require single constructor
        injector.setRequireAll(true); // require all parameters specified
        injector.provide(DataContext.class, dataContext);
        return injector;
    }

    public void fireLogIn(LoginToken token) {
        for (ILoginListener listener : getProviders()) {
            IUser user = token.getUser();
            try {
                listener.onLoggedIn(token);
            } catch (Exception e) {
                logger.errorf(e, "Can't logged out %s: %s", //
                        user.toString(), listener + ": " + e.getMessage());
            }
        }
    }

    public void fireLogOut(LoginToken token) {
        for (ILoginListener listener : getProviders()) {
            IUser user = token.authData.getUser();
            try {
                listener.onLoggedOut(token);
            } catch (Exception e) {
                logger.errorf(e, "Can't logged out %s: %s", //
                        user.toString(), listener + ": " + e.getMessage());
            }
        }
    }

}
