package net.bodz.bas.sec.pki.util;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class SimpleCallbackHandler implements CallbackHandler {

    private final char[] password;

    public SimpleCallbackHandler(String password) {
        if (password == null)
            throw new NullPointerException("password");
        this.password = password.toCharArray();
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            handle(callback);
        }
    }

    void handle(Callback callback) throws IOException {
        if (callback instanceof PasswordCallback) {
            PasswordCallback pwdCall = (PasswordCallback) callback;
            pwdCall.setPassword(password);

        } else if (callback instanceof TextOutputCallback) {
            // TextOutputCallback textOutCall = (TextOutputCallback) callback;

        }
    }

}
