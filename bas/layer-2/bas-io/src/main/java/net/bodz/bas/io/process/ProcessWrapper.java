package net.bodz.bas.io.process;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.c.java.nio.Charsets;

public class ProcessWrapper {

    static final int defaultInputBlockSize = 4096;

    Process process;

    String threadGroupName;
    ThreadGroup threadGroup;
    List<Thread> threads = new ArrayList<>();
    byte[] supplyBuf = new byte[4096];

    boolean autoFlush = true;
    boolean mergeOutputAndError;

    public ProcessWrapper(Process process, String threadGroupName) {
        if (process == null)
            throw new NullPointerException("process");
        if (threadGroupName == null)
            throw new NullPointerException("threadGroupName");
        this.process = process;
        this.threadGroupName = threadGroupName;
        this.threadGroup = new ThreadGroup(threadGroupName);
    }

    public boolean isAutoFlush() {
        return autoFlush;
    }

    public ProcessWrapper noAutoFlush() {
        autoFlush = false;
        return this;
    }

    public ProcessWrapper autoFlush() {
        autoFlush = true;
        return this;
    }

    public void supplyInput(IDataSupplyListener listener) {
        OutputStream stdin = process.getOutputStream();
        SupplyDataThread thread = new SupplyDataThread.Builder()//
                .threadGroup(threadGroup).name("stdin")//
                .target(stdin).autoClose()//
                .listener(listener) //
                .build();
        threads.add(thread);
        thread.start();
    }

    public void supplyInput(InputStream in) {
        supplyInput(in, defaultInputBlockSize);
    }

    public void supplyInput(InputStream in, int blockSize) {
        final byte[] block = new byte[blockSize];
        supplyInput(out -> {
            int cbRead = in.read(block, 0, blockSize);
            if (cbRead == -1)
                return false;
            out.write(block, 0, cbRead);
            if (autoFlush)
                out.flush();
            return true;
        });
    }

    public void supplyInput(Reader in) {
        supplyInput(in, Charsets.UTF_8, defaultInputBlockSize);
    }

    public void supplyInput(Reader in, int blockSize) {
        supplyInput(in, Charsets.UTF_8, blockSize);
    }

    public void supplyInput(Reader in, Charset charset) {
        supplyInput(in, charset, defaultInputBlockSize);
    }

    public void supplyInput(Reader in, Charset charset, int blockSize) {
        // CharsetEncoder encoder = charset.newEncoder();
        final char[] block = new char[blockSize];
        supplyInput(out -> {
            int ccRead = in.read(block, 0, blockSize);
            if (ccRead == -1)
                return false;
            // encoder.reset();
            String str = new String(block, 0, ccRead);
            byte[] encoded = str.getBytes(charset);
            out.write(encoded);
            if (autoFlush)
                out.flush();
            return true;
        });
    }

    public void captureOutput(IDataReceiveListener listener) {
        InputStream stdout = process.getInputStream();
        ReceiveDataThread thread = new ReceiveDataThread.Builder() //
                .threadGroup(threadGroup).name("stdout")//
                .source(stdout) // .autoClose() //
                .listener(listener) //
                .build();
        threads.add(thread);
        thread.start();
    }

    public void captureError(IDataReceiveListener listener) {
        InputStream stderr = process.getErrorStream();
        ReceiveDataThread thread = new ReceiveDataThread.Builder() //
                .threadGroup(threadGroup).name("stderr")//
                .source(stderr) // .autoClose() //
                .listener(listener) //
                .build();
        threads.add(thread);
        thread.start();
    }

    public int waitFor()
            throws InterruptedException {
        return waitFor(true);
    }

    public int waitFor(boolean join)
            throws InterruptedException {
        int status = process.waitFor();
        for (Thread thread : threads)
            if (join)
                thread.join();
            else {
                if (thread.isAlive()) {
                    thread.interrupt();
                    // thread.stop(); // after timeout?
                }
            }
        return status;
    }

    public ProcessWrapper mergeOutputAndError() {
        mergeOutputAndError = true;
        return this;
    }

    public RunData waitForRunData()
            throws InterruptedException {
        return waitForRunData(true);
    }

    public RunData waitForRunData(boolean join)
            throws InterruptedException {
        RunData runData = new RunData();
        if (mergeOutputAndError) {
            runData.captureOutputAndError(this);
        } else {
            runData.captureOutput(this);
            runData.captureError(this);
        }
        int exitStatus = waitFor(join);
        runData.exitStatus = exitStatus;
        return runData;
    }

}
