package net.bodz.lily.security.login;

public interface ILoginService {

    String USAGE_LOGIN = "login";
    String USAGE_REGISTER = "register";
    String USAGE_RESET_PASSWORD = "reset-password";

    /**
     * @return server challenge
     */
    String initLogin()
            throws LoginException;

    /**
     * @param mode
     *            means features to request to be used in the logged in session.
     */
    void login(String user, String cr, String mode)
            throws LoginException;

    void logout()
            throws LoginException;

    void verifyPhone(String number, String usage)
            throws LoginException;

    void verifyEmail(String address, String usage)
            throws LoginException;

    boolean loginByPhone(String number, String code)
            throws LoginException;

    boolean loginByEmail(String email, String code)
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
