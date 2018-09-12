package net.bodz.lily.site;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.http.ctx.CurrentHttpService;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.LoginData;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;

public class LoginService
        implements IPathDispatchable {

    static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    DataContext dataContext;
    ILoginHandler loginHandler;

    public LoginService(DataContext dataContext, ILoginHandler loginHandler) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        if (loginHandler == null)
            throw new NullPointerException("loginHandler");
        this.dataContext = dataContext;
        this.loginHandler = loginHandler;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        HttpServletRequest request = CurrentHttpService.getRequest();
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
            target = login(loginData, q);
            break;

        case "logout":
            if (loginHandler.logout(result, loginData)) {
                LoginData.removeFromSession(session);
                result.succeed();
            }
            target = result;
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
