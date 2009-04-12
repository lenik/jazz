package net.bodz.bas.log;

/**
 * Application Logger
 */
public class ALog extends LogStore {

    /** user interaction */
    public LogOut u;
    /** error */
    public LogOut e;
    /** warning, notice */
    public LogOut w;
    /** message */
    public LogOut m;
    /** information */
    public LogOut i;
    /** detail */
    public LogOut d;
    /** debug */
    public LogOut x;
    /** debug-2 */
    public LogOut xx;

    public ALog(int level) {
        super(level);
    }

    public ALog(String level) {
        super(level);
    }

    public ALog() {
        this(INFO);
    }

    public static final int     USER    = 0;
    public static final int     ERROR   = 0;
    public static final int     WARN    = 1;
    public static final int     MESG    = 2;
    public static final int     INFO    = 3;
    public static final int     DETAIL  = 4;
    public static final int     DEBUG   = 5;
    public static final int     DEBUG_2 = 6;

    private static final Object defaultTree;
    static {
        defaultTree = new Object[] { "vroot", //$NON-NLS-1$
            "user", //$NON-NLS-1$
            new Object[] { "mesg", new Object[] { "warn", "error" }, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            new Object[] { "info", "detail" } }, //$NON-NLS-1$ //$NON-NLS-2$
            new Object[] { "debug", "debug-2" } }; //$NON-NLS-1$ //$NON-NLS-2$
        leveldef.put("user", USER); //$NON-NLS-1$
        leveldef.put("error", ERROR); //$NON-NLS-1$
        leveldef.put("warn", WARN); //$NON-NLS-1$
        leveldef.put("mesg", MESG); //$NON-NLS-1$
        leveldef.put("info", INFO); //$NON-NLS-1$
        leveldef.put("detail", DETAIL); //$NON-NLS-1$
        leveldef.put("debug", DEBUG); //$NON-NLS-1$
        leveldef.put("debug-2", DEBUG_2); //$NON-NLS-1$
    }

    @Override
    public void reset() {
        setGraph(defaultTree);
        u = g("user"); //$NON-NLS-1$
        e = g("error"); //$NON-NLS-1$
        w = g("warn"); //$NON-NLS-1$
        m = g("mesg"); //$NON-NLS-1$
        i = g("info"); //$NON-NLS-1$
        d = g("detail"); //$NON-NLS-1$
        x = g("debug"); //$NON-NLS-1$
        xx = g("debug-2"); //$NON-NLS-1$
    }

    public LogOut get(int level) {
        switch (level) {
        // case USER:
        // return u;
        case ERROR:
            return e;
        case WARN:
            return w;
        case MESG:
            return m;
        case INFO:
            return i;
        case DETAIL:
            return d;
        case DEBUG:
            return x;
        case DEBUG_2:
            return xx;
        }
        throw new IllegalStateException();
    }

    public boolean showUser() {
        return show(USER);
    }

    public boolean showError() {
        return show(ERROR);
    }

    public boolean showWarn() {
        return show(WARN);
    }

    public boolean showMessage() {
        return show(MESG);
    }

    public boolean showInfo() {
        return show(INFO);
    }

    public boolean showDetail() {
        return show(DETAIL);
    }

    public boolean showDebug() {
        return show(DEBUG);
    }

    public boolean showDebug2() {
        return show(DEBUG_2);
    }

}
