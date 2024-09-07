package net.bodz.lily.security.login;

import net.bodz.bas.site.json.IJsonResponse;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.IUser;

public interface ILoginManager {

    String ATTRIBUTE_NAME = ILoginManager.class.getName();

    String VERIFY = "verify";
    String LOGIN = "login";
    String REGISTER = "register";
    String RESET_PASSWORD = "reset-password";

    LoginToken allocateToken(IUser user);

    /**
     * @return server challenge
     */
    LoginResult initiateLogin(IVariantMap<String> q);

    LoginResult login(IVariantMap<String> q);

    LoginResult loginByPhone(String phone, String code);

    LoginResult loginByEmail(String email, String code);

    IJsonResponse logout();

    IJsonResponse verifyPhone(String number, String usage);

    IJsonResponse verifyEmail(String address, String usage);

    LoginResult registerByPhone(String phone, String ecr, String password);

    LoginResult registerByEmail(String email, String ecr, String password);

    LoginResult resetPasswordByPhone(String phone, String ecr, String password);

    LoginResult resetPasswordByEmail(String email, String ecr, String password);

}
