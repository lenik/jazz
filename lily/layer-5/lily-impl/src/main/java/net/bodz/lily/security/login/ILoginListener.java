package net.bodz.lily.security.login;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.lily.security.User;

@IndexedType
public interface ILoginListener {

    void login(User user);

    void logout(User user);

}
