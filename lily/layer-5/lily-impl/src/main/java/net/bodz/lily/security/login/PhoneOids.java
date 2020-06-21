package net.bodz.lily.security.login;

import java.util.List;

import net.bodz.bas.db.ctx.AbstractDataContextAware;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserOtherId;
import net.bodz.lily.security.UserOtherIdTypes;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.security.impl.UserMask;
import net.bodz.lily.security.impl.UserOtherIdMapper;
import net.bodz.lily.security.impl.UserOtherIdMask;
import net.bodz.lily.security.impl.UserSecretMapper;

public class PhoneOids
        extends AbstractDataContextAware {

    UserMapper userMapper;
    UserSecretMapper secretMapper;
    UserOtherIdMapper oidMapper;

    public PhoneOids(DataContext dataContext) {
        super(dataContext);
        userMapper = dataContext.getMapper(UserMapper.class);
        secretMapper = dataContext.getMapper(UserSecretMapper.class);
        oidMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    public LoginResult register(LoginManager loginManager, String phone, String password) {
        LoginResult resp = new LoginResult();
        // 1. check if phone is in use
        List<UserOtherId> oids = oidMapper.filter(//
                new UserOtherIdMask().otherId(phone), SelectOptions.ALL);
        if (!oids.isEmpty())
            return resp.fail("Phone is already used.");

        // 2. auto create a user (random name)
        User user = new User();
        long tmp = System.currentTimeMillis();
        do {
            String name = "user-" + tmp;
            List<User> dups = userMapper.filter(new UserMask().name(name), SelectOptions.ALL);
            if (dups.isEmpty()) {
                user.setName(name);
                break;
            }
            tmp++;
        } while (true);

        UserSecret secret = user.getOrCreateSecret();
        secret.setPassword(password);

        userMapper.insert(user);
        assert user.getId() != null;

        // 3. bind user with phone (other-id)
        UserOtherId oid = new UserOtherId();
        oid.setUser(user);
        oid.setType(UserOtherIdTypes.MOBILE);
        oid.setOtherId(phone);
        oidMapper.insert(oid);

        // 4. auto login
        resp.token = loginManager.newToken(user);
        return resp.succeed();
    }

    public LoginResult resetPassword(LoginManager loginManager, String phone, String password) {
        LoginResult resp = new LoginResult();
        // 1. find the corresponding user
        List<UserOtherId> oids = oidMapper.filter(//
                new UserOtherIdMask().otherId(phone), SelectOptions.ALL);
        if (oids.isEmpty())
            return resp.fail("Phone number isn't used by any user.");
        if (oids.size() > 1)
            return resp.fail("Phone number is used by multiple users.");
        UserOtherId oid = oids.get(0);

        int userId = oid.getUser().getId();
        User user = userMapper.select(userId);

        // 2. update password
        UserSecret secret = user.getOrCreateSecret();
        secret.setPassword(password);
        secretMapper.update(secret);

        // 3. auto login
        resp.token = loginManager.newToken(user);
        return resp.succeed();
    }

}
