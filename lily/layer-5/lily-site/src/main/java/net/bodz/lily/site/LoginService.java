package net.bodz.lily.site;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.servlet.ctx.CurrentHttpService;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.LoginData;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.security.login.ILoginListener;
import net.bodz.lily.security.login.ILoginResolver;
import net.bodz.lily.security.login.LoginResult;

public class LoginService
        implements IPathDispatchable {

    static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    DataContext dataContext;
    ILoginResolver loginResolver;
    List<ILoginListener> loginListeners = new ArrayList<>();

    public LoginService(DataContext dataContext, ILoginResolver loginResolver) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        if (loginResolver == null)
            throw new NullPointerException("loginResolver");
        this.dataContext = dataContext;
        this.loginResolver = loginResolver;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        // TODO file-scope
        HttpSession session = CurrentHttpService.getSession();

        LoginData loginData;
        loginData = LoginData.fromSession(session);

        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        AjaxResult result = new AjaxResult();

        switch (token) {
        case "status":
            target = status(loginData);
            break;

        case "login":
            result = login(loginData, q);
            if (result.isFailed()) {
                target = result;
                break;
            }

            break;

        case "logout":
            if (loginData == null) {
                target = LoginResult.fail("Not logged in.");
                break;
            }

            User user = loginData.getUser();
            for (ILoginListener listener : loginListeners)
                try {
                    listener.logout(user);
                } catch (Exception e) {
                    target = LoginResult.fail("Can't logged out: " + listener + ": " + e.getMessage());
                    break;
                }
            if (target == null) { // no error happens.
                LoginData.removeFromSession(session);
                target = result.succeed();
            }
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, target, tokens);
        else
            return null;
    }

    AjaxResult status(LoginData loginData) {
        AjaxResult result = new AjaxResult();
        if (loginData.getUser() == null)
            return result.fail("Not login");

        User user = loginData.getUser();
        result.set("id", user.getId());
        result.set("name", user.getName());
        result.set("fullName", user.getFullName());
        result.set("description", user.getDescription());
        return result.succeed();
    }

    AjaxResult login(LoginData loginData, IVariantMap<String> q) {
        AjaxResult result = new AjaxResult();

        String userName = q.getString("user");
        if (userName == null)
            userName = q.getString("username");
        if (userName == null)
            return result.fail(100, "用户名未指定.");

        try {
            UserMapper userMapper = dataContext.getMapper(UserMapper.class);
            User user = userMapper.selectByName(userName);
            if (user == null)
                return result.fail(101, "用户 %s 不存在", userName);

            if (!loginHandler.login(result, user, q))
                return result;

            loginData.setUser(user);
            loginData.saveInSession();
            result.succeed();
        } catch (Exception e) {
            logger.error(e, "Data access error: " + e.getMessage());
            return result.fail(e, "系统错误：" + e.getMessage());
        }
        return result;
    }

}
