package net.bodz.bas.ui;

public abstract class TryBlock
        implements Runnable {

    public static final int INFINITE = -1;

    protected static final int RETRY = 0;
    protected static final int IGNORE = 1;
    protected static final int CANCEL = 2;

    private final int maxRetry;

    public TryBlock() {
        this(INFINITE, true);
    }

    public TryBlock(int maxRetry) {
        this(maxRetry, true);
    }

    public TryBlock(int maxRetry, boolean tryImmediately) {
        this.maxRetry = maxRetry;
        if (tryImmediately)
            _run();
    }

    @Override
    public final void run() {
        _run();
    }

    public int _run() {
        int tried = 0;
        while (true) {
            init();
            try {
                body();
                // } catch (T t) { // see below _check()
            } catch (Exception e) {
                _check(e);
                if (maxRetry != INFINITE && ++tried > maxRetry)
                    return _exit(MAXRETRIED);
                int answer = ask(e);
                switch (answer) {
                case RETRY:
                    retry();
                    continue;
                case IGNORE:
                    return _exit(IGNORED);
                case CANCEL:
                    return _exit(CANCELED);
                }
            }
            break;
        }
        return _exit(DONE);
    }

    protected void _check(Exception e) {
    }

    protected abstract int ask(Exception e);

    /**
     * called at beginning, and before next try.
     */
    protected void init() {
    }

    protected abstract void body()
            throws Exception;

    /**
     * called after this try.
     */
    protected void retry() {
    }

    public static final int DONE = 0;
    public static final int IGNORED = 1;
    public static final int MAXRETRIED = 2;
    public static final int CANCELED = 3;

    int _exit(int exitType) {
        exit(exitType);
        return exitType;
    }

    protected void exit(int exitType) {
    }

}
