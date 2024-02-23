package net.bodz.lily.schema.account;

import javax.persistence.Table;

import net.bodz.lily.security.IUserSecret;

@Table(schema = UserSecret.SCHEMA_NAME, name = UserSecret.TABLE_NAME)
public class UserSecret
        extends _UserSecret_stuff
        implements
            IUserSecret {

    private static final long serialVersionUID = 1L;

    public UserSecret user(User user) {
        setUser(user);
        return this;
    }

    public UserSecret password(String password) {
        setPassword(password);
        return this;
    }

    public boolean isPasswordMatched(String s) {
        if (password == null || s == null)
            return false;
        return password.equals(s);
    }

}
