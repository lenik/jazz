package net.bodz.lily.site;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.http.ctx.CurrentHttpService;
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

    DataContext dataContext;
    LoginData fileLoginData = new LoginData();

    public LoginService(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        HttpServletRequest request = CurrentHttpService.getRequest();
        String origin = request.getHeader("Origin");
        boolean fileOrigin = false;
        if (origin != null) {
            if (origin.equals("null") || origin.startsWith("file://"))
                fileOrigin = true;
        }

        Object target = null;

        String token = tokens.peek();
        if (token == null)
            return null;

        LoginData loginData;
        AjaxResult result = new AjaxResult();

        switch (token) {
        case "status":
            if (fileOrigin) {
                loginData = fileLoginData;
            } else {
                loginData = LoginData.fromSession(request.getSession());
            }

            if (loginData == null || loginData.getUser() == null) {
                result.fail("Not login");
            } else {
                result.setSuccess(true);
                User user = loginData.getUser();
                result.set("id", user.getId());
                result.set("name", user.getName());
                result.set("fullName", user.getFullName());
                result.set("description", user.getDescription());
            }
            target = result;
            break;

        case "login":
            target = result;

            String userName = q.getString("username");
            String password = q.getString("password");
            if (userName == null || password == null) {
                result.fail("Username or password not specified.");
                break;
            }

            UserMapper mapper = dataContext.getMapper(UserMapper.class);
            List<User> users = mapper.findForLogin(userName, password);
            if (users.isEmpty()) {
                result.fail("Bad user or password");
                break;
            }
            User user = users.get(0);
            loginData = new LoginData();
            loginData.setUser(user);
            if (fileOrigin)
                fileLoginData = loginData;
            else
                loginData.saveInSession(request.getSession());
            result.setSuccess(true);
            break;

        case "logout":
            LoginData.removeFromSession(request.getSession());
            result.setSuccess(true);
            target = result;
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, target, tokens);
        else
            return null;
    }

}
