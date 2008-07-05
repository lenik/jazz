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
        defaultTree = new Object[] {
            "vroot",
            "user",
            new Object[] { "mesg", new Object[] { "warn", "error" },
            new Object[] { "info", "detail" } },
            new Object[] { "debug", "debug-2" } };
        leveldef.put("user", USER);
        leveldef.put("error", ERROR);
        leveldef.put("warn", WARN);
        leveldef.put("mesg", MESG);
        leveldef.put("info", INFO);
        leveldef.put("detail", DETAIL);
        leveldef.put("debug", DEBUG);
        leveldef.put("debug-2", DEBUG_2);
    }

    @Override
    public void reset() {
        setGraph(defaultTree);
        u = g("user");
        e = g("error");
        w = g("warn");
        m = g("mesg");
        i = g("info");
        d = g("detail");
        x = g("debug");
        xx = g("debug-2");
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
