package net.bodz.lily.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.err.LoadException;
import net.bodz.bas.inject.AbstractServiceManager;
import net.bodz.bas.inject.Injector;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.lily.app.DataApps;
import net.bodz.lily.app.IDataApplication;

public class PamManager
        extends AbstractServiceManager<IAuthModule> {

    public static final String ATTRIBUTE_KEY = PamManager.class.getName();

    DataContext dataContext;

    PamManager(DataContext dataContext) {
        super(IAuthModule.class, createInjector(dataContext));
    }

    public static PamManager load(DataContext dataContext)
            throws LoadException {
        return new PamManager(dataContext);
    }

    static Injector createInjector(DataContext dataContext) {
        Injector injector = new Injector();
        injector.setExplicitConstructor(true); // require single constructor
        injector.setRequireAll(true); // require all parameters specified
        injector.provide(DataContext.class, dataContext);
        return injector;
    }

    @Override
    public String getName(IAuthModule provider) {
        return provider.getName();
    }

    /**
     * @return Non-<code>null</code> instance.
     */
    public static PamManager fromRequest() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        return fromRequest(request);
    }

    public static synchronized PamManager fromRequest(HttpServletRequest request) {
        if (request == null)
            return null;

        HttpSession session = request.getSession();
        PamManager manager = (PamManager) session.getAttribute(ATTRIBUTE_KEY);
        if (manager == null) {
            IDataApplication app = DataApps.fromRequest(request);
            DataContext dataContext = app.getDataContext();
            manager = PamManager.load(dataContext);
            session.setAttribute(ATTRIBUTE_KEY, manager);
        }
        return manager;
    }

}
