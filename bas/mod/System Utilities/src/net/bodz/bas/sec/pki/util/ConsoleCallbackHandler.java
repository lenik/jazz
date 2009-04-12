package net.bodz.bas.sec.pki.util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import net.bodz.bas.io.Files;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;
import net.bodz.bas.log.LogOut;

import com.sun.security.auth.callback.TextCallbackHandler;

/**
 * @see TextCallbackHandler
 */
@Deprecated
public class ConsoleCallbackHandler implements CallbackHandler {

    private ALog   L;
    private char[] password;

    public ConsoleCallbackHandler(ALog L) {
        this.L = L;
    }

    public ConsoleCallbackHandler() {
        this(ALogs.stderr);
    }

    public void clearPassword() {
        password = null;
    }

    public void setPassword(char[] password) {
        this.password = password;
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
            if (password == null) {
                String prompt = pwdCall.getPrompt();
                L.m.P(prompt);
                BufferedReader lineIn = Files.getBufferedReader(System.in);
                String line = lineIn.readLine();
                password = line.toCharArray();
            }
            pwdCall.setPassword(password);

        } else if (callback instanceof TextOutputCallback) {
            TextOutputCallback textOutCall = (TextOutputCallback) callback;
            LogOut out = L.d;
            switch (textOutCall.getMessageType()) {
            case TextOutputCallback.INFORMATION:
                out = L.m;
                break;
            case TextOutputCallback.WARNING:
                out = L.w;
                break;
            case TextOutputCallback.ERROR:
                out = L.e;
                break;
            }
            out.P(textOutCall.getMessage());
        }
    }

}
