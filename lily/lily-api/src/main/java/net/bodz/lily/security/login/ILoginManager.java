package net.bodz.lily.security.login;

import java.util.function.Predicate;

import net.bodz.lily.security.auth.AuthData;

public interface ILoginManager {

    String ATTRIBUTE_NAME = ILoginManager.class.getName();

    LoginToken logIn(AuthData user);

    void logOut(LoginToken token);

    void logOutFor(Predicate<LoginToken> p);

    default void logOutAll() {
        logOutFor(token -> true);
    }

}
