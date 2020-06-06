package net.bodz.lily.security.login;

import net.bodz.lily.security.User;

public class LoginResult {

    public static final int OK = 0;
    public static final int ERROR = 1;

    boolean success;
    User user;
    // Integer userId;
    int statusCode;
    String reason;

    public LoginResult(User user, int statusCode, String reason) {
        this.success = user != null;
        this.user = user;
        this.statusCode = statusCode;
        this.reason = reason;
    }

    public static LoginResult success(User user) {
        return success(OK, user);
    }

    public static LoginResult success(int statusCode, User user) {
        return new LoginResult(user, statusCode, null);
    }

    public static LoginResult fail(String reason) {
        return fail(ERROR, reason);
    }

    public static LoginResult fail(int statusCode, String reason) {
        return new LoginResult(null, statusCode, reason);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
