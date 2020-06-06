package net.bodz.lily.security.login;

import net.bodz.lily.security.User;

public interface ILoginListener {

    void login(User user);

    void logout(User user);

}
