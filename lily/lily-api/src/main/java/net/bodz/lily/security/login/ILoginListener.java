package net.bodz.lily.security.login;

import net.bodz.lily.security.IUser;

public interface ILoginListener {

    void onLoggedIn(IUser user);

    default void onLoggedOut(IUser user) {
    }

}
