package net.bodz.lily.security.login;

import net.bodz.lily.security.User;

public interface ILoginAware {

    void setLoginContext(User loginUser);

}
