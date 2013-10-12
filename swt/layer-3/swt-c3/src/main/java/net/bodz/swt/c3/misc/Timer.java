package net.bodz.swt.c3.misc;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;

public abstract class Timer
        implements Runnable {

    class Delayed
            implements Runnable {

        private boolean canceled;

        public void cancel() {
            canceled = true;
        }

        @Override
        public void run() {
            if (canceled)
                return;
            arrival();
        }

    }

    private int interval;
    private boolean enabled;
    private Display display;
    private Widget parent;

    private Delayed pending;
    private boolean disposed;

    /**
     * @param interval
     *            in milliseconds
     */
    public Timer(int interval, boolean enabled, Widget parent) {
        assert display != null;
        this.enabled = enabled;
        this.parent = parent;
        this.display = parent.getDisplay();
        setInterval(interval);
    }

    public Timer(int interval, Widget parent) {
        this(interval, true, parent);
    }

    void schedule(int interval) {
        Delayed delayed = new Delayed();
        pending = delayed;
        display.timerExec(interval, delayed);
    }

    public boolean isDisposed() {
        if (disposed)
            return true;
        if (display.isDisposed())
            return true;
        if (parent != null && parent.isDisposed())
            return true;
        return false;
    }

    public void dispose() {
        enabled = false;
        disposed = true;
        if (pending != null)
            pending.cancel();
    }

    final void arrival() {
        if (isDisposed())
            return;
        if (enabled)
            run();
        if (isDisposed())
            return;
        schedule(interval);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        if (pending != null)
            pending.cancel();
        this.interval = interval;
        schedule(interval);
    }

    @Override
    public abstract void run();

}
