package net.bodz.lily.security.login;

import net.bodz.lily.security.User;

public interface ILoginListener {

    void onLoggedIn(User user);

    default void onLoggedOut(User user) {
    }

}
