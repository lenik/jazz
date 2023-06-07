package net.bodz.lily.security;

import javax.persistence.Table;

@Table(schema = "lily", name = "usersec")
public class UserSecret
        extends _UserSecret_stuff {

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
