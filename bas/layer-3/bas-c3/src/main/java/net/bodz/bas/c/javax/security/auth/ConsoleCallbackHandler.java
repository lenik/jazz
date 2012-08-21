package net.bodz.bas.c.javax.security.auth;

import java.io.BufferedReader;
import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import net.bodz.bas.io.resource.builtin.InputStreamSource;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;

/**
 * @see com.sun.security.auth.callback.TextCallbackHandler
 */
@SuppressWarnings("restriction")
@Deprecated
public class ConsoleCallbackHandler
        implements CallbackHandler {

    private IPrintOut stdout;
    private IPrintOut stderr;
    private char[] password;

    public ConsoleCallbackHandler(IPrintOut stdout, IPrintOut stderr) {
        this.stdout = stdout;
    }

    public ConsoleCallbackHandler() {
        this(Stdio.cout, Stdio.cerr);
    }

    public void clearPassword() {
        password = null;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public void handle(Callback[] callbacks)
            throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            handle(callback);
        }
    }

    void handle(Callback callback)
            throws IOException {
        if (callback instanceof PasswordCallback) {
            PasswordCallback pwdCall = (PasswordCallback) callback;
            if (password == null) {
                String prompt = pwdCall.getPrompt();
                stdout.println(prompt);
                BufferedReader lineIn = new InputStreamSource(System.in).newBufferedReader();
                String line = lineIn.readLine();
                password = line.toCharArray();
            }
            pwdCall.setPassword(password);

        } else if (callback instanceof TextOutputCallback) {
            TextOutputCallback textOutCall = (TextOutputCallback) callback;
            IPrintOut out = stdout;
            switch (textOutCall.getMessageType()) {
            case TextOutputCallback.INFORMATION:
                out = stdout;
                break;
            case TextOutputCallback.WARNING:
            case TextOutputCallback.ERROR:
                out = stderr;
                break;
            }
            out.println(textOutCall.getMessage());
        }
    }

}
