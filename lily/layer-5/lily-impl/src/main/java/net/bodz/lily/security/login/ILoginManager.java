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
    LoginResult initiateLogin(IVariantMap<String> q);

    LoginResult login(IVariantMap<String> q);

    LoginResult loginByPhone(String phone, String code);

    LoginResult loginByEmail(String email, String code);

    JsonResponse logout();

    JsonResponse verifyPhone(String number, String usage);

    JsonResponse verifyEmail(String address, String usage);

    LoginResult registerByPhone(String phone, String ecr, String password);

    LoginResult registerByEmail(String email, String ecr, String password);

    LoginResult resetPasswordByPhone(String phone, String ecr, String password);

    LoginResult resetPasswordByEmail(String email, String ecr, String password);

}
