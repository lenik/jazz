package net.bodz.lily.site;

import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.LoginData;
import net.bodz.lily.security.User;

public interface ILoginHandler {

    boolean login(AjaxResult result, User user, IVariantMap<String> q);

    boolean logout(AjaxResult result, LoginData data);

}
