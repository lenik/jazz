package net.bodz.lily.security.login;

import org.apache.commons.codec.digest.DigestUtils;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.variant.IVariantMap;

public class TokenizedLoginResolver
        extends DataAccessLoginResolver {

    public TokenizedLoginResolver(DataContext dataContext) {
        super(dataContext);
    }

    @Override
    public LoginResult login(IVariantMap<String> q) {
        String password = user.getSecret().getPassword();
        String auth = q.getString("auth");
        if (auth == null) {
            int salt = Salts.getSalt(0);
            result.set("salt", salt);
            result.succeed();
            return false;
        }

        int theSalt = -1;
        for (int salt : Salts.getSalt(0, 5)) {
            String expected = DigestUtils.shaHex(salt + password + salt);
            if (auth.equals(expected)) {
                theSalt = salt;
                break;
            }
        }
        if (theSalt == -1) {
            return LoginResult.fail(200, "登录信息认证失败。");
        }

        LoginToken token = new LoginToken(user, password);
        tokenManager.register(token);
        token.next(result);
        return null;
    }

}
