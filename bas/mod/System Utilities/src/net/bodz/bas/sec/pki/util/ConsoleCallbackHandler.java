package net.bodz.bas.sec.pki.util;

import java.io.BufferedReader;
import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import net.bodz.bas.io.Files;
import net.bodz.bas.io.term.Terminal;
import net.bodz.bas.util.LogTerm;
import net.bodz.bas.util.LogTerms;

import com.sun.security.auth.callback.TextCallbackHandler;

/**
 * @see TextCallbackHandler
 */
@Deprecated
public class ConsoleCallbackHandler implements CallbackHandler {

    private LogTerm logger;
    private char[]  password;

    public ConsoleCallbackHandler(LogTerm logger) {
        this.logger = logger;
    }

    public ConsoleCallbackHandler() {
        this(LogTerms.console);
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
                logger.info(prompt);
                BufferedReader lineIn = Files.getBufferedReader(System.in);
                String line = lineIn.readLine();
                password = line.toCharArray();
            }
            pwdCall.setPassword(password);

        } else if (callback instanceof TextOutputCallback) {
            TextOutputCallback textOutCall = (TextOutputCallback) callback;
            Terminal out = logger.detail();
            switch (textOutCall.getMessageType()) {
            case TextOutputCallback.INFORMATION:
                out = logger.info();
                break;
            case TextOutputCallback.WARNING:
                out = logger.warn();
                break;
            case TextOutputCallback.ERROR:
                out = logger.error();
                break;
            }
            out.p(textOutCall.getMessage());
        }
    }

}
