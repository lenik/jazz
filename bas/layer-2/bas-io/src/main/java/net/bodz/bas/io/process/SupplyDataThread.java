package net.bodz.bas.io.process;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SupplyDataThread
        extends Thread {

    static final int defaultBlockSize = 4096;

    final OutputStream out;
    final boolean autoClose;
    final byte[] buf;
    final List<IDataSupplyListener> listeners = new ArrayList<>();

    private SupplyDataThread(ThreadGroup threadGroup, String threadName, OutputStream out, boolean autoClose,
            int blockSize) {
        super(threadGroup, threadName);
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
        this.autoClose = autoClose;
        this.buf = new byte[blockSize];
    }

    @Override
    public void run() {
        try {
            _run();
        } catch (IOException e) {
            throw new RuntimeException("Error writing: " + e.getMessage(), e);
        }
    }

    public void _run()
            throws IOException {
        try {
            boolean anymore = true;
            while (anymore) {
                anymore = false;
                for (IDataSupplyListener listener : listeners) {
                    anymore |= listener.onDataRequired(out);
                    if (isInterrupted())
                        return;
                }
            }
        } finally {
            if (autoClose)
                try {
                    out.close();
                } catch (IOException e) {
                }
        }
    }

    public void addListener(IDataSupplyListener listener) {
        if (listener == null)
            throw new NullPointerException("listener");
        this.listeners.add(listener);
    }

    public void removeListener(IDataSupplyListener listener) {
        this.listeners.remove(listener);
    }

    public static class Builder {

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        String name;
        OutputStream target;
        boolean autoClose;
        int blockSize = 4096;
        IDataSupplyListener listener;

        public Builder threadGroup(ThreadGroup threadGroup) {
            if (threadGroup == null)
                throw new NullPointerException("threadGroup");
            this.threadGroup = threadGroup;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder target(OutputStream target) {
            if (target == null)
                throw new NullPointerException("target");
            this.target = target;
            return this;
        }

        public Builder autoClose() {
            this.autoClose = true;
            return this;
        }

        public Builder blockSize(int blockSize) {
            this.blockSize = blockSize;
            return this;
        }

        public Builder listener(IDataSupplyListener listener) {
            this.listener = listener;
            return this;
        }

        public SupplyDataThread build() {
            String threadName;
            if (name == null)
                threadName = threadGroup.getName() + threadGroup.activeCount();
            else
                threadName = threadGroup.getName() + "-" + name;
            SupplyDataThread o = new SupplyDataThread(threadGroup, threadName, target, autoClose, blockSize);
            if (listener != null)
                o.addListener(listener);
            return o;
        }

    }

}
