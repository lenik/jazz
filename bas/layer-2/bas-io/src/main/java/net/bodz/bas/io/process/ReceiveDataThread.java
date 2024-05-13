package net.bodz.bas.io.process;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ReceiveDataThread
        extends Thread {

    static final int defaultBlockSize = 4096;

    InputStream in;
    boolean autoClose;
    private byte[] buf;

    private List<IDataReceiveListener> listeners = new ArrayList<>();

    private ReceiveDataThread(ThreadGroup threadGroup, String threadName, InputStream in, boolean autoClose,
            int blockSize) {
        super(threadGroup, threadName);
        if (in == null)
            throw new NullPointerException("in");
        this.in = in;
        this.autoClose = autoClose;
        this.buf = new byte[blockSize];
    }

    @Override
    public void run() {
        try {
            _run();
        } catch (IOException e) {
            throw new RuntimeException("Error reading: " + e.getMessage(), e);
        }
    }

    public void _run()
            throws IOException {
        try {
            while (true) {
                int cbRead = in.read(buf, 0, buf.length);
                if (cbRead == -1)
                    break;
                if (isInterrupted())
                    return;

                for (IDataReceiveListener listener : listeners) {
                    listener.onDataReceived(buf, 0, cbRead);
                    if (isInterrupted())
                        return;
                }
            }
        } finally {
            if (autoClose)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
    }

    public void addListener(IDataReceiveListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        this.listeners.add(listener);
    }

    public void removeListener(IDataReceiveListener listener) {
        this.listeners.remove(listener);
    }

    public static class Builder {

        ThreadGroup threadGroup;
        String name;
        InputStream source;
        boolean autoClose;
        int blockSIze = 4096;
        IDataReceiveListener listener;

        public Builder threadGroup(ThreadGroup threadGroup) {
            this.threadGroup = threadGroup;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder source(InputStream source) {
            this.source = source;
            return this;
        }

        public Builder autoClose() {
            this.autoClose = true;
            return this;
        }

        public Builder blockSIze(int blockSIze) {
            this.blockSIze = blockSIze;
            return this;
        }

        public Builder listener(IDataReceiveListener listener) {
            this.listener = listener;
            return this;
        }

        public ReceiveDataThread build() {
            String fullName = threadGroup.getName() + "-" + name;
            ReceiveDataThread o = new ReceiveDataThread(threadGroup, fullName, source, autoClose, blockSIze);
            if (listener != null)
                o.addListener(listener);
            return o;
        }

    }

}
