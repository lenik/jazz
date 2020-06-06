package net.bodz.lily.security.login;

import net.bodz.bas.t.variant.IVariantMap;

public interface ILoginResolver {

    LoginResult login(IVariantMap<String> q);

}
