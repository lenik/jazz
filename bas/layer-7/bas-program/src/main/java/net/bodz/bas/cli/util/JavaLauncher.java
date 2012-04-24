package net.bodz.bas.cli.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.Charset;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.jvm.exit.CatchExit;
import net.bodz.bas.jvm.exit.ExitableProgram;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.loader.Classpath;
import net.bodz.bas.loader.DefaultBooter;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.loader.UCL;
import net.bodz.bas.loader.boot.BootInfo;
import net.bodz.bas.loader.boot.BootProc;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.text.charset.BasCharsetProvider;

import org.apache.commons.lang.ArrayUtils;

@BootInfo(syslibs = "bodz_bas")
public abstract class JavaLauncher
        implements Launcher {

    private Class<?> mainClass;

    private InputStream redirectIn;
    private PrintStream redirectOut;
    private PrintStream redirectErr;

    private ByteArrayOutputStream outbuf;
    private ByteArrayOutputStream errbuf;

    // private static LogOut out = LogOuts.debug;

    public JavaLauncher() {
        ClassLoader initSysLoader = Caller.getCallerClassLoader(0);
        ClassLoader sysLoader = initSysLoader;

        try {
            BootProc bootProc = BootProc.get(getClass());
            if (bootProc != null) {
                // sysLoader = bootProc.configSysLoader();
                // sysLoader = bootProc.configLoader(sysLoader);
                URL[] urls = LoadUtil.find(bootProc.getSysLibs());
                Classpath.addURL(urls);
            }
            if (LOAD_DUMP)
                UCL.dump(sysLoader, Stdio.cerr);

            String mainClassName = getMainClassName();
            mainClass = DefaultBooter.loadFix(sysLoader, mainClassName);
        } catch (Exception e) {
            throw new Error(e.getMessage(), e);
        }
    }

    protected abstract String getMainClassName();

    protected Class<?> getMainClass() {
        return mainClass;
    }

    protected Method getMainMethod()
            throws SecurityException, NoSuchMethodException {
        Class<?> clazz = getMainClass();
        Method main = clazz.getMethod("main", String[].class);
        return main;
    }

    @Override
    public void launch(final String[] args)
            throws Exception {
        final Method main = getMainMethod();

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

            ExitableProgram<Exception> doMain = new ExitableProgram<Exception>() {
                @Override
                public void execute()
                        throws Exception {
                    try {
                        // (SecurityControl isnot-a Control)
                        Control.invoke(main, null, (Object) args);
                    } catch (Exception e) {
                        throw e;
                    }
                }
            };

            CatchExit catcher = new CatchExit();
            try {
                catcher.catchExit(doMain);
            } catch (ControlExit e) {
                _exit(e.getStatus());
            }
        } finally {
            if (origIn != null)
                System.setIn(origIn);
            if (origOut != null)
                System.setOut(origOut);
            if (origErr != null)
                System.setErr(origErr);
        }
    }

    protected void _exit(int status) {
        System.exit(status);
    }

    static boolean LOAD_DUMP = false;

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

    public static final int NONE = 0;
    public static final int OUT_ONLY = 1;
    public static final int ERR_ONLY = 2;
    public static final int BOTH = 3;
    public static final int BOTH_MIXED = 4;

    public void capture(int mode) {
        outbuf = null;
        errbuf = null;
        switch (mode) {
        case NONE:
            break;
        case OUT_ONLY:
            outbuf = new ByteArrayOutputStream();
            break;
        case ERR_ONLY:
            errbuf = new ByteArrayOutputStream();
            break;
        case BOTH:
            outbuf = new ByteArrayOutputStream();
            errbuf = new ByteArrayOutputStream();
            break;
        case BOTH_MIXED:
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

    public String getCapturedOutput(Object charset) {
        Charset _charset = BasCharsetProvider.get(charset);
        return new String(getCapturedOutput(), _charset);
    }

    public byte[] getCapturedError() {
        return errbuf.toByteArray();
    }

    public String getCapturedError(Object charset) {
        Charset _charset = BasCharsetProvider.get(charset);
        return new String(getCapturedError(), _charset);
    }

    public static void main(String[] args)
            throws Exception {
        if (args.length < 1)
            throw new IllegalArgumentException("classname expected");
        final String className = args[0];
        final String[] _args = (String[]) ArrayUtils.remove(args, 0);
        new JavaLauncher() {
            @Override
            protected String getMainClassName() {
                return className;
            }
        }.launch(_args);
    }

}
