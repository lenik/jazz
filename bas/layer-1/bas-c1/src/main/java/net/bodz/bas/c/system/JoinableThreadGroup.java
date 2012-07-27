package net.bodz.bas.c.system;

public class JoinableThreadGroup
        extends ThreadGroup {

    public JoinableThreadGroup(String name) {
        super(name);
    }

    public JoinableThreadGroup(ThreadGroup parent, String name) {
        super(parent, name);
    }

    public void joinIgnoreInterrupt() {
        Thread[] active = new Thread[1];
        while (enumerate(active) != 0)
            try {
                active[0].join();
            } catch (InterruptedException e) {
            }
    }

    public void join(long ms, boolean recurse)
            throws InterruptedException {
        synchronized (this) {
            // deprecated:
            // while (activeCount() > 0) wait();
            if (!isDestroyed() && enumerate(new Thread[1], recurse) > 0)
                wait(ms);
        }
    }

    public void join(long ms)
            throws InterruptedException {
        join(ms, true);
    }

    public void join(boolean recurse)
            throws InterruptedException {
        join(0, recurse);
    }

    public void join()
            throws InterruptedException {
        join(0, true);
    }

}
