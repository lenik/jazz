package net.bodz.lily.security.login;

import javax.servlet.ServletException;

public class LoginException
        extends ServletException {

    private static final long serialVersionUID = 1L;

    public LoginException() {
        super();
    }

    public LoginException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Throwable rootCause) {
        super(rootCause);
    }

}
