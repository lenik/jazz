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
    public Result login(IVariantMap<String> q) {
        String userName = q.getString("user");
        if (userName == null)
            userName = q.getString("username");
        if (userName == null)
            return failed("User isn't specified.");

        String password = q.getString("password");
        if (password == null) {
            return failed("Password isn't specified.");
        }

        User namedUser = userMapper.selectByName(userName);
        if (namedUser == null)
            return failed("No such user: %s", userName);

        // userSecretMapper.filter(new UserSecretMask().setUserId(userId), opt)
        List<User> users = userMapper.findForLogin(userName, password);
        if (users.isEmpty())
            return failed("Bad user or password");

        if (users.size() != 1)
            return failed("Ambiguous users: " + users);

        User match = users.get(0);
        assert match.getId().intValue() == namedUser.getId().intValue();

        return new Result(namedUser);
    }

}
