package net.bodz.swt.widgets;

import net.bodz.swt.widgets.TimerTest;

import org.eclipse.swt.widgets.Display;

/**
 * @test {@link TimerTest}
 */
public abstract class Timer implements Runnable {

    class Delayed implements Runnable {

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

    private int     interval;
    private boolean enabled;
    private Display display;

    private Delayed pending;
    private boolean disposed;

    /**
     * @param interval
     *            in milliseconds
     */
    public Timer(int interval, boolean enabled, Display display) {
        assert display != null;
        this.enabled = enabled;
        this.display = display;
        setInterval(interval);
    }

    public Timer(int interval, boolean enabled) {
        this(interval, enabled, Display.getCurrent());
    }

    public Timer(int interval, Display display) {
        this(interval, true, display);
    }

    public Timer(int interval) {
        this(interval, true);
    }

    void schedule(int interval) {
        Delayed delayed = new Delayed();
        pending = delayed;
        display.timerExec(interval, delayed);
    }

    public boolean isDisposed() {
        return disposed || display.isDisposed();
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
