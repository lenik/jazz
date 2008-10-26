package net.bodz.bas.cli.util;

import static net.bodz.bas.types.util.ArrayOps.Strings;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

import net.bodz.bas.cli.CLIConfig;
import net.bodz.bas.cli._RunInfo;
import net.bodz.bas.cli.a.RunInfo;
import net.bodz.bas.io.Files;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.lang.RunnableThrows;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.sec.CatchExit;
import net.bodz.bas.types.util.Types;

@RunInfo(lib = { "bodz_bas" })
public abstract class JavaLauncher implements Launcher {

    static {
        Types.load(CLIConfig.class);
    }

    private Method                mainf;

    @SuppressWarnings("unused")
    private boolean               libLoaded;

    private InputStream           redirectIn;
    private PrintStream           redirectOut;
    private PrintStream           redirectErr;

    private ByteArrayOutputStream outbuf;
    private ByteArrayOutputStream errbuf;

    private static LogOut         out = LogOuts.debug;

    public JavaLauncher() {
        libLoaded = "1".equals(System
                .getProperty(CLIConfig.PROPERTY_LIB_LOADED));
    }

    protected abstract String getMainClassName();

    @Override
    public void launch(final String[] args) throws Exception {
        if (mainf == null)
            load();
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
            RunnableThrows<Exception> doMain = new RunnableThrows<Exception>() {
                @Override
                public void run() throws Exception {
                    try {
                        // (SecurityControl isnot-a Control)
                        Control.invoke(mainf, null, (Object) args);
                    } catch (Exception e) {
                        throw e;
                    }
                }
            };
            try {
                CatchExit.run(doMain);
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

    protected void load() throws Exception {
        Class<?> launcherClass = getClass();
        _RunInfo launcherInfo = _RunInfo.parse(launcherClass, true);
        if (launcherInfo != null) {
            out.P("load boot: ", launcherClass);
            launcherInfo.loadBoot();

            // if (!libLoaded)
            out.P("load libraries: ", launcherClass);
            launcherInfo.loadLibraries();
        }

        String mainClassName = getMainClassName();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        {
            Class<?> mainClass = Class.forName(mainClassName, false,
                    classLoader);

            _RunInfo runInfo = _RunInfo.parse(mainClass, true);
            if (runInfo != null) {
                out.P("load boot: ", mainClass);
                runInfo.loadBoot();

                // if (!libLoaded)
                out.P("load libraries: ", mainClass);
                runInfo.loadLibraries();
            }

            mainf = mainClass.getMethod("main", String[].class);

            if (runInfo != null) {
                out.P("load delayed: ", mainClass);
                runInfo.loadDelayed();
            }
        }

        if (launcherInfo != null) {
            out.P("load delayed: ", launcherClass);
            launcherInfo.loadDelayed();
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

    public static final int NONE       = 0;
    public static final int OUT_ONLY   = 1;
    public static final int ERR_ONLY   = 2;
    public static final int BOTH       = 3;
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
        Charset _charset = Files.getCharset(charset);
        return new String(getCapturedOutput(), _charset);
    }

    public byte[] getCapturedError() {
        return errbuf.toByteArray();
    }

    public String getCapturedError(Object charset) {
        Charset _charset = Files.getCharset(charset);
        return new String(getCapturedError(), _charset);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1)
            throw new IllegalArgumentException("classname expected");
        final String className = args[0];
        final String[] _args = Strings.shift(args);
        new JavaLauncher() {
            @Override
            protected String getMainClassName() {
                return className;
            }
        }.launch(_args);
    }

}
