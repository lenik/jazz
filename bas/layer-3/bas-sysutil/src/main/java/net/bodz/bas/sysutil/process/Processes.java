package net.bodz.bas.sysutil.process;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import net.bodz.bas.commons.collection.util.Arrays2;
import net.bodz.bas.io.Files;
import net.bodz.bas.nls.SysNLS;
import net.bodz.bas.sys.ProcessesTest;
import net.bodz.bas.text.util.Strings;

/**
 * @test {@link ProcessesTest}
 */
public class Processes {

    private static String[] shvec;
    private static String shprefix;
    static {
        String os = System.getProperty("os.name"); //$NON-NLS-1$
        if (os.startsWith("Windows")) { //$NON-NLS-1$
            String comspec = System.getenv("COMSPEC"); //$NON-NLS-1$
            if (comspec == null)
                comspec = "cmd"; //$NON-NLS-1$
            shvec = new String[] { comspec, "/c" }; //$NON-NLS-1$
        } else {
            String shell = System.getenv("SHELL"); //$NON-NLS-1$
            if (shell == null)
                shell = "sh"; //$NON-NLS-1$
            shvec = new String[] { shell };
        }
        shprefix = Strings.join(" ", shvec) + " "; //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static Process shellExec(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command);
    }

    public static Process shellExec(String command, String[] envp) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command, envp);
    }

    public static Process shellExec(String command, String[] envp, File dir) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command, envp, dir);
    }

    public static Process shellExec(String[] cmdarray) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(Arrays2.concat(shvec, cmdarray));
    }

    public static Process shellExec(String[] cmdarray, String[] envp) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(Arrays2.concat(shvec, cmdarray), envp);
    }

    public static Process shellExec(String[] cmdarray, String[] envp, File dir) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(Arrays2.concat(shvec, cmdarray), envp, dir);
    }

    /**
     * outSink and errSink may be same.
     * 
     * @return exit code returned by the <code>process</code>.
     */
    public static int iocap(Process process, final InputStream sendSrc, final OutputStream outSink,
            final OutputStream errSink) throws InterruptedException {
        ManagedProcess mp = new ManagedProcess(new _IOCallback() {

            @Override
            public void sendProc(OutputStream out) throws IOException {
                if (sendSrc == null) {
                    out.close();
                    // super.sendProc(out, sendSrc);
                } else
                    super.sendProc(out, sendSrc);
            }

            @Override
            public void recvIn(byte[] buf, int off, int len) throws IOException {
                if (outSink != null)
                    outSink.write(buf, off, len);
            }

            @Override
            public void recvErr(byte[] buf, int off, int len) throws IOException {
                if (errSink != null)
                    errSink.write(buf, off, len);
            }
        });
        return mp.takeOver(process);
    }

    public static int iocap(Process process, Object sendSrc, Object outSink, Object errSink)
            throws InterruptedException, IOException {
        InputStream send = Files.getInputStream(sendSrc);
        boolean sendc = Files.shouldClose(send);
        try {
            OutputStream out = Files.getOutputStream(outSink);
            boolean outc = Files.shouldClose(out);
            try {
                OutputStream err = Files.getOutputStream(errSink);
                boolean errc = Files.shouldClose(err);
                try {
                    return iocap(process, send, out, err);
                } finally {
                    if (errc)
                        err.close();
                }
            } finally {
                if (outc)
                    out.close();
            }
        } finally {
            if (sendc)
                send.close();
        }
    }

    public static int iocap(Process process, Object outerrSink) throws InterruptedException, IOException {
        OutputStream outerr = Files.getOutputStream(outerrSink);
        boolean outerrc = Files.shouldClose(outerrSink);
        try {
            return iocap(process, null, outerr, outerr);
        } finally {
            if (outerrc)
                outerr.close();
        }
    }

    static ThreadLocal<Integer> exitCode;
    static {
        exitCode = new ThreadLocal<Integer>();
    }

    public static int getLastExitCode() {
        Integer code = exitCode.get();
        if (code == null)
            throw new IllegalStateException(SysNLS.getString("Processes.noExitCode")); //$NON-NLS-1$
        return code;
    }

    public static byte[] iocap(Process process) throws InterruptedException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int code = iocap(process, null, buf, buf);
        exitCode.set(code);
        return buf.toByteArray();
    }

    public static String iocap(Process process, String charset) throws InterruptedException {
        return iocap(process, Charset.forName(charset));
    }

    public static String iocap(Process process, Charset charset) throws InterruptedException {
        byte[] out = iocap(process);
        return new String(out, charset);
    }

}
