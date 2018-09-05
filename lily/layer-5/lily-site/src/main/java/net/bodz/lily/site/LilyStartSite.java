package net.bodz.lily.site;

import java.util.List;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.BasicSite;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.site.org.ICrawler;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.codegen.doc.WsDocSite;
import net.bodz.lily.security.LoginData;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;

public abstract class LilyStartSite
        extends BasicSite
        implements ILoginHandler {

    static final Logger logger = LoggerFactory.getLogger(LilyStartSite.class);

    protected final DataContext dataContext;

    public LilyStartSite(DataContext dataContext) {
        this.dataContext = dataContext;
        setQueryContext(dataContext);

        setupServices();
    }

    @Override
    public synchronized IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        return super.dispatch(previous, tokens, q);
    }

    @Override
    public void crawlableIntrospect(ICrawler crawler) {
        // crawler.follow("TODO", null);
    }

    void setupServices() {
        pathMap.install(new CoIndexServiceGroup(dataContext).getNameMap());
        pathMap.install("session", new LoginService(dataContext, this));
        pathMap.install("ws-doc", new WsDocSite());
    }

    @Override
    public boolean login(AjaxResult result, User user, IVariantMap<String> q) {
        String userName = q.getString("username");
        String password = q.getString("password");
        if (userName == null || password == null) {
            result.fail("Username or password not specified.");
            return false;
        }

        UserMapper mapper = dataContext.getMapper(UserMapper.class);
        List<User> users = mapper.findForLogin(userName, password);
        if (users.isEmpty()) {
            result.fail("Bad user or password");
            return false;
        }

        if (users.size() != 1) {
            result.fail("Ambiguous user from " + users);
            return false;
        }

        User got = users.get(0);
        if (got.getId().intValue() != user.getId().intValue()) {
            result.fail("Unexpected: found another user: " + got);
        }
        return true;
    }

    @Override
    public final boolean logout(AjaxResult result, LoginData data) {
        return true;
    }

}
