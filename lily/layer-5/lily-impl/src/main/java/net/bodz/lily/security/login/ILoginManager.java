package net.bodz.lily.security.login;

import net.bodz.bas.site.json.JsonResponse;
import net.bodz.bas.t.variant.IVariantMap;

public interface ILoginManager {

    String USAGE_LOGIN = "login";
    String USAGE_REGISTER = "register";
    String USAGE_RESET_PASSWORD = "reset-password";

    /**
     * @return server challenge
     */
    LoginResult initiateLogin(IVariantMap<String> q)
            throws LoginException;

    LoginResult login(IVariantMap<String> q)
            throws LoginException;

    LoginResult loginByPhone(String phone, String code)
            throws LoginException;

    LoginResult loginByEmail(String email, String code)
            throws LoginException;

    JsonResponse logout()
            throws LoginException;

    void verifyPhone(String number, String usage)
            throws LoginException;

    void verifyEmail(String address, String usage)
            throws LoginException;

    void registerByPhone(String phone, String code)
            throws LoginException;

    void registerByEmail(String email, String code)
            throws LoginException;

    void resetPasswordByPhone(String phone, String code)
            throws LoginException;

    void resetPasswordByEmail(String email, String code)
            throws LoginException;

}
