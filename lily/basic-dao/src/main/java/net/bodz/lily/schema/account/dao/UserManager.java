package net.bodz.lily.schema.account.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.site.json.IJsonResponse;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.security.auth.AuthData;

public class UserManager
        extends AbstractEntityManager<User, UserMapper> {

    UserManager_PhoneId _phoneId;

    public UserManager(DataContext dataContext) {
        super(dataContext, UserMapper.class);
        _phoneId = new UserManager_PhoneId(dataContext);
    }

    public AuthData register(String phone, String password) {
        return _phoneId.register(phone, password);
    }

    public AuthData resetPassword(String phone, String password) {
        return _phoneId.resetPassword(phone, password);
    }

    public IJsonResponse bind(int uid, String phone) {
        return _phoneId.bind(uid, phone);
    }

    public IJsonResponse unbind(int uid, String phone) {
        return _phoneId.unbind(uid, phone);
    }

}
