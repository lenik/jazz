package net.bodz.bas.shell.runner;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import net.bodz.bas.err.OutOfDomainException;

public abstract class AbstractRedirectedLauncher
        extends AbstractProgramRunner {

    public static final int CAPTURE_NONE = 0;
    public static final int CAPTURE_OUT_ONLY = 1;
    public static final int CAPTURE_ERR_ONLY = 2;
    public static final int CAPTURE_BOTH = 3;
    public static final int CAPTURE_BOTH_MIXED = 4;

    private InputStream redirectIn;
    private PrintStream redirectOut;
    private PrintStream redirectErr;

    private ByteArrayOutputStream outbuf;
    private ByteArrayOutputStream errbuf;

    // private static LogOut out = LogOuts.debug;

    protected abstract void runRedirected(Object context, String... args)
            throws Exception;

    @Override
    public void run(Object context, String... args)
            throws Exception {

        InputStream origIn = null;
        PrintStream origOut = null;
        PrintStream origErr = null;

        try {
            if (redirectIn != null) {
                origIn = System.in;
                System.setIn(redirectIn);
            }
            if (redirectOut != null) {
                origOut = System.out;
                System.setOut(redirectOut);
            }
            if (redirectErr != null) {
                origErr = System.err;
                System.setErr(redirectErr);
            }

            runRedirected(context, args);

        } finally {
            if (origIn != null)
                System.setIn(origIn);
            if (origOut != null)
                System.setOut(origOut);
            if (origErr != null)
                System.setErr(origErr);
        }

    }

    public void setRedirectIn(InputStream redirectIn) {
        this.redirectIn = redirectIn;
    }

    public void setRedirectOut(OutputStream redirectOut) {
        if (!(redirectOut instanceof PrintStream))
            redirectOut = new PrintStream(redirectOut);
        this.redirectOut = (PrintStream) redirectOut;
    }

    public void setRedirectErr(OutputStream redirectErr) {
        if (!(redirectErr instanceof PrintStream))
            redirectErr = new PrintStream(redirectErr);
        this.redirectErr = (PrintStream) redirectErr;
    }

    public void capture(int mode) {
        outbuf = null;
        errbuf = null;
        switch (mode) {
        case CAPTURE_NONE:
            break;
        case CAPTURE_OUT_ONLY:
            outbuf = new ByteArrayOutputStream();
            break;
        case CAPTURE_ERR_ONLY:
            errbuf = new ByteArrayOutputStream();
            break;
        case CAPTURE_BOTH:
            outbuf = new ByteArrayOutputStream();
            errbuf = new ByteArrayOutputStream();
            break;
        case CAPTURE_BOTH_MIXED:
            outbuf = errbuf = new ByteArrayOutputStream();
            break;
        default:
            throw new OutOfDomainException("mode", mode);
        }
        if (outbuf != null)
            setRedirectOut(outbuf);
        if (errbuf != null)
            setRedirectErr(errbuf);
    }

    public byte[] getCapturedOutput() {
        return outbuf.toByteArray();
    }

    public String getCapturedOutput(String charsetName)
            throws UnsupportedEncodingException {
        return new String(getCapturedOutput(), charsetName);
    }

    public byte[] getCapturedError() {
        return errbuf.toByteArray();
    }

    public String getCapturedError(String charsetName)
            throws UnsupportedEncodingException {
        return new String(getCapturedError(), charsetName);
    }

}
