package net.bodz.bas.c.java.io.capture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.lang.ArrayUtils;

import net.bodz.bas.c.string.StringArray;

/**
 * @test {@link ProcessesTest}
 */
public class Processes {

    private static String[] shvec;
    private static String shprefix;
    static {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            String comspec = System.getenv("COMSPEC");
            if (comspec == null)
                comspec = "cmd";
            shvec = new String[] { comspec, "/c" };
        } else {
            String shell = System.getenv("SHELL");
            if (shell == null)
                shell = "sh";
            shvec = new String[] { shell, "-c" };
        }
        shprefix = StringArray.join(" ", shvec) + " ";
    }

    public static Process shellExec(String command)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command);
    }

    public static Process shellExec(String command, String[] envp)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command, envp);
    }

    public static Process shellExec(String command, String[] envp, File dir)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(shprefix + command, envp, dir);
    }

    public static Process shellExec(String[] cmdarray)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        cmdarray = (String[]) ArrayUtils.addAll(shvec, cmdarray);
        return runtime.exec(cmdarray);
    }

    public static Process shellExec(String[] cmdarray, String[] envp)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        cmdarray = (String[]) ArrayUtils.addAll(shvec, cmdarray);
        return runtime.exec(cmdarray, envp);
    }

    public static Process shellExec(String[] cmdarray, String[] envp, File dir)
            throws IOException {
        Runtime runtime = Runtime.getRuntime();
        cmdarray = (String[]) ArrayUtils.addAll(shvec, cmdarray);
        return runtime.exec(cmdarray, envp, dir);
    }

    /**
     * outSink and errSink may be same.
     * 
     * @return exit code returned by the <code>process</code>.
     */
    public static int iocap(Process process, final InputStream sendSrc, final OutputStream outSink,
            final OutputStream errSink)
            throws InterruptedException {
        ManagedProcess mp = new ManagedProcess(new _IOCallback() {

            @Override
            public void sendProc(OutputStream out)
                    throws IOException {
                if (sendSrc == null) {
                    out.close();
                    // super.sendProc(out, sendSrc);
                } else
                    super.sendProc(out, sendSrc);
            }

            @Override
            public void recvIn(byte[] buf, int off, int len)
                    throws IOException {
                if (outSink != null)
                    outSink.write(buf, off, len);
            }

            @Override
            public void recvErr(byte[] buf, int off, int len)
                    throws IOException {
                if (errSink != null)
                    errSink.write(buf, off, len);
            }
        });
        return mp.takeOver(process);
    }

    public static int iocap(Process process, OutputStream outErrSink)
            throws InterruptedException, IOException {
        return iocap(process, null, outErrSink, outErrSink);
    }

    static ThreadLocal<Integer> exitCode;
    static {
        exitCode = new ThreadLocal<Integer>();
    }

    public static int getLastExitCode() {
        Integer code = exitCode.get();
        if (code == null)
            throw new IllegalStateException("last exit code hasn\'t been set yet.");
        return code;
    }

    public static byte[] iocap(Process process)
            throws InterruptedException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int code = iocap(process, null, buf, buf);
        exitCode.set(code);
        return buf.toByteArray();
    }

    public static String iocap(Process process, String charset)
            throws InterruptedException {
        return iocap(process, Charset.forName(charset));
    }

    public static String iocap(Process process, Charset charset)
            throws InterruptedException {
        byte[] out = iocap(process);
        return new String(out, charset);
    }

}
