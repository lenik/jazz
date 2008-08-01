package net.bodz.bas.sys;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ManagedProcess {

    // private final Process process;
    protected OutputStream out;         // captured stdin of target process
    protected InputStream  in;          // captured stdout of target process
    protected InputStream  err;         // captured stderr of target process

    private Thread         sysInGrabber;
    private Thread         inGrabber;
    private Thread         errGrabber;

    public ManagedProcess() {
        sysInGrabber = new Thread() {
            @Override
            public void run() {
                try {
                    grabSysIn();
                } catch (IOException e) {
                    throw new Error(e.getMessage(), e);
                }
            }
        };
        inGrabber = new Thread() {
            @Override
            public void run() {
                try {
                    grabIn();
                } catch (IOException e) {
                    throw new Error(e.getMessage(), e);
                }
            }
        };
        errGrabber = new Thread() {
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

    public synchronized int takeOver(Process process)
            throws InterruptedException {
        this.out = process.getOutputStream();
        this.in = process.getInputStream();
        this.err = process.getErrorStream();
        try {
            inGrabber.start();
            errGrabber.start();
            sysInGrabber.start();
            start();
            return process.waitFor();
        } finally {
            sysInGrabber.interrupt();
            inGrabber.join();
            errGrabber.join();
        }
    }

    protected void start() {
    }

    private static final int BLOCK = 4096;

    void grabSysIn() throws IOException {
        byte[] buf = new byte[BLOCK];
        int cb;
        while ((cb = System.in.read(buf)) != -1)
            recvSysIn(buf, 0, cb);
        out.close();
    }

    void grabIn() throws IOException {
        byte[] buf = new byte[BLOCK];
        int cb;
        while ((cb = in.read(buf)) != -1)
            recvIn(buf, 0, cb);
    }

    void grabErr() throws IOException {
        byte[] buf = new byte[BLOCK];
        int cb;
        while ((cb = err.read(buf)) != -1)
            recvErr(buf, 0, cb);
    }

    protected void recvSysIn(byte[] buf, int off, int len) throws IOException {
        out.write(buf, off, len);
        out.flush();
    }

    protected void recvIn(byte[] buf, int off, int len) throws IOException {
        System.out.write(buf, off, len);
    }

    protected void recvErr(byte[] buf, int off, int len) throws IOException {
        System.err.write(buf, off, len);
    }

}
