package net.bodz.lily.security.login;

import net.bodz.bas.site.json.JsonResponse;
import net.bodz.bas.t.variant.IVariantMap;

public interface ILoginManager {

    String VERIFY = "verify";
    String LOGIN = "login";
    String REGISTER = "register";
    String RESET_PASSWORD = "reset-password";

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

    LoginResult registerByPhone(String phone, String code)
            throws LoginException;

    LoginResult registerByEmail(String email, String code)
            throws LoginException;

    LoginResult resetPasswordByPhone(String phone, String code, String password)
            throws LoginException;

    LoginResult resetPasswordByEmail(String email, String code, String password)
            throws LoginException;

}
