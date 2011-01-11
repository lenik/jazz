package net.bodz.bas.jvm.stack;

/**
 * ASMifier this class to get a initial code
 */
public class _FrameTemplate implements Runnable {

    private final String description;
    private final Runnable target;

    public _FrameTemplate(String description, Runnable target) {
        this.description = description;
        this.target = target;
    }

    public void run() {
        target.run();
    }

    @Override
    public String toString() {
        return description;
    }

}
