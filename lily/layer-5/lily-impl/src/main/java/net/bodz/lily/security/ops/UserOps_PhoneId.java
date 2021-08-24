package net.bodz.lily.security.ops;

import java.util.List;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.site.json.IJsonResponse;
import net.bodz.bas.site.json.IMutableJsonResponse;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserOtherId;
import net.bodz.lily.security.UserOtherIdTypes;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.security.impl.UserMask;
import net.bodz.lily.security.impl.UserOtherIdMapper;
import net.bodz.lily.security.impl.UserOtherIdMask;
import net.bodz.lily.security.impl.UserSecretMapper;
import net.bodz.lily.security.login.LoginManager;
import net.bodz.lily.security.login.LoginResult;

public class UserOps_PhoneId
        extends AbstractOps
        implements
            IDataContextAware {

    UserMapper userMapper;
    UserSecretMapper secretMapper;
    UserOtherIdMapper oidMapper;

    public UserOps_PhoneId(DataContext dataContext) {
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
        int dup = 0;
        do {
            String name = "user-" + phone;
            if (dup != 0)
                name += "-" + dup;
            List<User> dups = userMapper.filter(new UserMask().name(name), SelectOptions.ALL);
            if (dups.isEmpty()) {
                user.setName(name);
                break;
            }
            dup++;
        } while (true);

        UserSecret secret = user.makeSecret();
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
        UserSecret secret = user.makeSecret();
        secret.setPassword(password);
        secretMapper.update(secret);

        // 3. auto login
        resp.token = loginManager.newToken(user);
        return resp.succeed();
    }

    public IJsonResponse bind(int uid, String phone) {
        JsonResult resp = new JsonResult();

        User user = checkUserId(resp, uid);
        if (user == null)
            return resp;

        if (checkInUse(resp, phone))
            return resp;

        UserOtherId oid = new UserOtherId();
        oid.setUser(user);
        oid.setOtherId(phone);
        // priority...
        try {
            oidMapper.insert(oid);
        } catch (Exception e) {
            return resp.fail("failed to insert: " + e.getMessage(), e);
        }
        return resp.succeed();
    }

    public IJsonResponse unbind(int uid, String phone) {
        JsonResult resp = new JsonResult();
        // 1. check uid
        User user = checkUserId(resp, uid);
        if (user == null)
            return resp;

        try {
            oidMapper.deleteFor(new UserOtherIdMask()//
                    .user(user).otherId(phone));
        } catch (Exception e) {
            return resp.fail(e, "Failed to delete: " + e.getMessage());
        }
        return resp.succeed();
    }

    User checkUserId(IMutableJsonResponse resp, int uid) {
        // 1. check uid
        User user = userMapper.select(uid);
        if (user == null)
            resp.fail("No such user: id=%d.", uid);
        return user;
    }

    boolean checkInUse(IMutableJsonResponse resp, String phone) {
        // 2. check if the phone is in use.
        List<UserOtherId> oids = oidMapper.filter(//
                new UserOtherIdMask().otherId(phone), SelectOptions.ALL);
        if (oids.isEmpty())
            return false;

        UserOtherId oid = oids.get(0);
        resp.fail("Phone number is already in use: user = %s.", //
                oid.getUser().getName());
        return true;
    }

}
