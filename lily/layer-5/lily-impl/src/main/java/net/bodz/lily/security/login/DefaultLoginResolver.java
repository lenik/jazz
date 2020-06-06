package net.bodz.lily.security.login;

import java.util.List;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;

public class DefaultLoginResolver
        extends DataAccessLoginResolver {

    public DefaultLoginResolver(DataContext dataContext) {
        super(dataContext);
    }

    @Override
    public LoginResult login(IVariantMap<String> q) {
        String userName = q.getString("username");
        String password = q.getString("password");
        if (userName == null || password == null) {
            return LoginResult.fail("Username or password not specified.");
        }

        List<User> users = userMapper.findForLogin(userName, password);
        if (users.isEmpty()) {
            return LoginResult.fail("Bad user or password");
        }

        if (users.size() != 1) {
            return LoginResult.fail("Ambiguous users: " + users);
        }

        User user = users.get(0);
        // if (firstMatch.getId().intValue() != user.getId().intValue())
        // return LoginResult.fail("Unexpected: found another user: " + firstMatch);

        return LoginResult.success(user);
    }

}
