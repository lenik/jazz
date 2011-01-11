package net.bodz.bas.log;

public class LogCategory
        implements Comparable<LogCategory> {

    public static final int STDOUT_ID = 0;
    public static final int STDERR_ID = 1;
    public static final int ERROR_ID = 2;
    public static final int INFO_ID = 3;
    public static final int DEBUG_ID = 4;
    public static final int STATUS_ID = 5;

    public static final LogCategory STATUS = new LogCategory("status", STATUS_ID);
    public static final LogCategory STDOUT = new LogCategory("stdout", STDOUT_ID);
    public static final LogCategory STDERR = new LogCategory("stderr", STDERR_ID);

    public static final LogCategory ERROR = new LogCategory("error", ERROR_ID);
    public static final LogCategory INFO = new LogCategory("info", INFO_ID);
    public static final LogCategory DEBUG = new LogCategory("debug", DEBUG_ID);

    private final int id;
    private final String name;
    private final String displayName;

    public LogCategory(String name, int id) {
        if (name == null)
            throw new NullPointerException("name");
        this.id = id;
        this.name = name;
        this.displayName = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public int hashCode() {
        int hash = name.hashCode();
        hash ^= id * 71;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LogCategory))
            return false;
        LogCategory o = (LogCategory) obj;
        if (!name.equals(o.name))
            return false;
        if (id != o.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        if (displayName != null)
            return displayName;
        else
            return name;
    }

    @Override
    public int compareTo(LogCategory o) {
        int c = id - o.id;
        if (c != 0)
            return c;
        return name.compareTo(o.name);
    }

}
