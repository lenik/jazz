package net.bodz.lily.security.login;

import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.servlet.ctx.CurrentHttpService;

public class LoginManagers {

    public static ILoginManager fromRequest() {
        HttpServletRequest request = CurrentHttpService.getRequestOpt();
        if (request == null)
            return null;

        return null;
    }

}
