package net.bodz.bas.log;

public class LogLevel {

    public static final int stdGroup = 0;
    public static final int logGroup = 1;
    public static final int statusGroup = 2;

    public static final LogLevel STDERR = new LogLevel("stderr", stdGroup, -2);
    public static final LogLevel STDOUT = new LogLevel("stdout", stdGroup, -1);

    public static final LogLevel FATAL = new LogLevel("fatal", logGroup, -3, 0);
    public static final LogLevel ERROR = new LogLevel("error", logGroup, -2, 3);
    public static final LogLevel WARN = new LogLevel("warn", logGroup, -1, 4);
    public static final LogLevel LOG = new LogLevel("log", logGroup, 0, 6);
    public static final LogLevel INFO = new LogLevel("info", logGroup, 1, 6);
    public static final LogLevel DEBUG = new LogLevel("debug", logGroup, 3, 7);
    public static final LogLevel TRACE = new LogLevel("trace", logGroup, 4, 7);

    public static final LogLevel STATUS = new LogLevel("status", statusGroup, 1);
    public static final LogLevel PROGRESS = new LogLevel("progress", statusGroup, 3);

    private final transient String intern;
    private final transient String displayName;
    private final transient int group;
    private final transient int priority;
    private final transient int syslogEquiv;

    public LogLevel(String name, int group, int priority) {
        this(name, group, priority, -1);
    }

    public LogLevel(String name, int group, int priority, int syslogEquiv) {
        if (name == null)
            throw new NullPointerException("name");
        this.intern = name.intern();
        this.displayName = name;
        this.group = group;
        this.priority = priority;
        this.syslogEquiv = syslogEquiv;
    }

    public String getInternName() {
        return intern;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getGroup() {
        return group;
    }

    public int getPriority() {
        return priority;
    }

    public int getSyslogEquiv() {
        return syslogEquiv;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((intern == null) ? 0 : intern.hashCode());
        result = prime * result + group;
        result = prime * result + priority;
        result = prime * result + syslogEquiv;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LogLevel other = (LogLevel) obj;
        if (intern == null) {
            if (other.intern != null)
                return false;
        } else if (!intern.equals(other.intern))
            return false;
        if (group != other.group)
            return false;
        if (priority != other.priority)
            return false;
        if (syslogEquiv != other.syslogEquiv)
            return false;
        return true;
    }

    @Override
    public String toString() {
        if (displayName != null)
            return displayName;
        else
            return intern;
    }

}
