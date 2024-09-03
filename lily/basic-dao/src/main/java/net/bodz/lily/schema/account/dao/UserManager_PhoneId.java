package net.bodz.lily.schema.account.dao;

import java.util.List;

import net.bodz.bas.db.ctx.AbstractDataService;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.site.json.IJsonResponse;
import net.bodz.bas.site.json.IMutableJsonResponse;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.UserOtherId;
import net.bodz.lily.schema.account.UserOtherIdTypes;
import net.bodz.lily.schema.account.UserSecret;
import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.login.LoginResult;

class UserManager_PhoneId
        extends AbstractDataService
        implements
            IDataContextAware {

    UserMapper userMapper;
    UserSecretMapper secretMapper;
    UserOtherIdMapper oidMapper;

    public UserManager_PhoneId(DataContext dataContext) {
        super(dataContext);
        userMapper = dataContext.getMapper(UserMapper.class);
        secretMapper = dataContext.getMapper(UserSecretMapper.class);
        oidMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    public AuthData register(String phone, String password) {
        LoginResult resp = new LoginResult();
        // 1. check if phone is in use
        List<UserOtherId> oids = oidMapper.filter(new UserOtherIdCriteriaBuilder()//
                .otherId.eq(phone).get());
        if (! oids.isEmpty())
            throw new IllegalArgumentException("Phone is already used.");

        // 2. auto create a user (random name)
        User user = new User();
        int dup = 0;
        do {
            String name = "user-" + phone;
            if (dup != 0)
                name += "-" + dup;
            List<User> dups = userMapper.filter(new UserCriteriaBuilder().name.eq(name).get(), //
                    SelectOptions.TOP10);
            if (dups.isEmpty()) {
                user.setName(name);
                break;
            }
            dup++;
        } while (true);

        UserSecret secret = user.makeSecret();
        secret.setPassword(password);

        userMapper.insert(user);
        assert user.id() != null;

        // 3. bind user with phone (other-id)
        UserOtherId oid = new UserOtherId();
        oid.setUser(user);
        oid.setType(UserOtherIdTypes.MOBILE);
        oid.setOtherId(phone);
        oidMapper.insert(oid);

        return AuthData.complete(this, user);
    }

    public AuthData resetPassword(String phone, String password) {
        LoginResult resp = new LoginResult();
        // 1. find the corresponding user
        List<UserOtherId> oids = oidMapper.filter(//
                new UserOtherIdCriteriaBuilder().otherId.eq(phone).get(), //
                SelectOptions.TOP10);
        if (oids.isEmpty())
            throw new IllegalArgumentException("Phone number isn't used by any user.");
        if (oids.size() > 1)
            throw new IllegalArgumentException("Phone number is used by multiple users.");
        UserOtherId oid = oids.get(0);

        int userId = oid.getUser().id();
        User user = userMapper.select(userId);

        // 2. update password
        UserSecret secret = user.makeSecret();
        secret.setPassword(password);
        secretMapper.update(secret);

        return AuthData.complete(this, user);
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
            oidMapper.deleteFor(new UserOtherIdCriteriaBuilder()//
                    .userId.eq(user.id()).otherId.eq(phone).get());
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
                new UserOtherIdCriteriaBuilder().otherId.eq(phone).get(), SelectOptions.TOP10);
        if (oids.isEmpty())
            return false;

        UserOtherId oid = oids.get(0);
        resp.fail("Phone number is already in use: user = %s.", //
                oid.getUser().getName());
        return true;
    }

}
