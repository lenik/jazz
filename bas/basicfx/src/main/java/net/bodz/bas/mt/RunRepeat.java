package net.bodz.bas.mt;

public class RunRepeat implements Runnable {

    private final Runnable target;
    private final int delay;
    private final int repeatCount;
    private int count;

    public RunRepeat(Runnable target, int delay, int repeatCount) {
        if (target == null)
            throw new NullPointerException("target"); //$NON-NLS-1$
        this.target = target;
        this.delay = delay;
        this.repeatCount = repeatCount;
    }

    public RunRepeat(Runnable target, int delay) {
        this(target, delay, Integer.MAX_VALUE);
    }

    public RunRepeat(Runnable target) {
        this(target, 0);
    }

    @Override
    public void run() {
        while (nextRun()) {
            try {
                target.run();
            } catch (Exception e) {
                if (!recover(e))
                    return;
            }
            int delay = getDelay();
            if (delay >= 0)
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    if (!recoverInterrupted(e))
                        return;
                }
        }
    }

    protected boolean recover(Exception ex) {
        ex.printStackTrace();
        return true;
    }

    protected boolean recoverInterrupted(InterruptedException ex) {
        ex.printStackTrace();
        return false;
    }

    protected boolean nextRun() {
        return count++ < repeatCount;
    }

    protected int getDelay() {
        return delay;
    }

}
