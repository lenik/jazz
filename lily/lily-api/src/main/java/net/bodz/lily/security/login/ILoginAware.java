package net.bodz.lily.security.login;

import net.bodz.lily.security.IUser;

public interface ILoginAware {

    void setLoginContext(IUser loginUser);

}
