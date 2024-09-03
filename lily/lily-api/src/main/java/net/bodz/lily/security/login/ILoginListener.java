package net.bodz.lily.security.login;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;

@IndexedType(publishDir = PublishDir.features)
public interface ILoginListener {

    void onLoggedIn(LoginToken token);

    void onLoggedOut(LoginToken token);

}
