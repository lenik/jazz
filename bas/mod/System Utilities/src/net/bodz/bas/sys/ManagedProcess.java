package net.bodz.bas.sys;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.bodz.bas.io.ByteOut;
import net.bodz.bas.io.ByteOuts;

public class ManagedProcess {

    private final String   name;

    // private final Process process;
    protected OutputStream out;       // captured stdin of target process
    protected InputStream  in;        // captured stdout of target process
    protected InputStream  err;       // captured stderr of target process

    private Thread         sender;
    private Thread         inGrabber;
    private Thread         errGrabber;

    private IOCallback     callback;

    private static int     _id = 0;

    static String getNextId() {
        return "MP_" + (++_id);
    }

    public ManagedProcess() {
        this(getNextId());
    }

    public ManagedProcess(IOCallback callback) {
        this(getNextId(), callback);
    }

    public ManagedProcess(String name) {
        this(name, null);
    }

    public ManagedProcess(String name, IOCallback callback) {
        assert name != null;
        this.name = name;
        if (callback == null)
            callback = sysAdapter;
        this.callback = callback;

        sender = new Thread(name + ":sender") {
            @Override
            public void run() {
                try {
                    ByteOut bout = ByteOuts.get(out);
                    ManagedProcess.this.callback.sendProc(bout);
                } catch (IOException e) {
                    throw new Error(e.getMessage(), e);
                }
            }
        };
        inGrabber = new Thread(name + ":inGrabber") {
            @Override
            public void run() {
                try {
                    grabIn();
                } catch (IOException e) {
                    throw new Error(e.getMessage(), e);
                }
            }
        };
        errGrabber = new Thread(name + ":errGraber") {
            @Override
            public void run() {
                try {
                    grabErr();
                } catch (IOException e) {
                    throw new Error(e.getMessage(), e);
                }
            }
        };
    }

    @Override
    public String toString() {
        return "ManagedProcess " + name;
    }

    public synchronized int takeOver(Process process)
            throws InterruptedException {
        this.out = process.getOutputStream();
        this.in = process.getInputStream();
        this.err = process.getErrorStream();
        try {
            inGrabber.start();
            errGrabber.start();
            sender.start();
            start();
            return process.waitFor();
        } finally {
            sender.interrupt();
            inGrabber.join();
            errGrabber.join();
        }
    }

    protected void start() {
    }

    private static final int BLOCK = 4096;

    void sendOut(byte[] buf, int off, int len) throws IOException {
        out.write(buf, off, len);
        out.flush();
    }

    private void grabIn() throws IOException {
        byte[] buf = new byte[BLOCK];
        int cb;
        while ((cb = in.read(buf)) != -1)
            callback.recvIn(buf, 0, cb);
    }

    private void grabErr() throws IOException {
        byte[] buf = new byte[BLOCK];
        int cb;
        while ((cb = err.read(buf)) != -1)
            callback.recvErr(buf, 0, cb);
    }

    static class SysAdapter extends _IOCallback {

        /**
         * Get data to send and send out by {@link #sendOut(byte[], int, int)}.
         * <p>
         * DEFAULT IMPLEMENTATION: <br>
         * get data from System.in
         */

        @Override
        public void sendProc(ByteOut out) throws IOException {
            sendProc(out, System.in);
        }

        /**
         * Process data received from stdout of the process
         * <p>
         * DEFAULT IMPLEMENTATION: <br>
         * write data to System.out
         */
        @Override
        public void recvIn(byte[] buf, int off, int len) throws IOException {
            System.out.write(buf, off, len);
        }

        /**
         * Process data received from stderr of the process
         * <p>
         * DEFAULT IMPLEMENTATION: <br>
         * write data to System.err
         */
        @Override
        public void recvErr(byte[] buf, int off, int len) throws IOException {
            System.err.write(buf, off, len);
        }

    }

    static IOCallback sysAdapter = new SysAdapter();

}
