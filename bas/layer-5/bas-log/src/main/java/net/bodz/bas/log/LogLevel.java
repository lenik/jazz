package net.bodz.bas.log;

public class LogLevel {

    public static final LogLevel STDERR = new LogLevel(null, "stderr", 0, 0);
    public static final LogLevel STDOUT = new LogLevel(STDERR, "stdout", 0, 1);

    public static final LogLevel FATAL = new LogLevel(null, "fatal", 1, -3, 0);
    public static final LogLevel ERROR = new LogLevel(FATAL, "error", 1, -2, 3);
    public static final LogLevel WARN = new LogLevel(ERROR, "warn", 1, -1, 4);
    public static final LogLevel NOTICE = new LogLevel(WARN, "notice", 1, 0, 6); // syslog==5?
    public static final LogLevel INFO = new LogLevel(NOTICE, "info", 1, 1, 6);
    public static final LogLevel DETAIL = new LogLevel(INFO, "detail", 1, 2, 6);
    public static final LogLevel DEBUG = new LogLevel(DETAIL, "debug", 1, 3, 7);
    public static final LogLevel TRACE = new LogLevel(DEBUG, "trace", 1, 4, 7);

    public static final LogLevel STATUS = new LogLevel(null, "status", 2, 1);
    public static final LogLevel PRORESS = new LogLevel(STATUS, "progress", 2, 2);

    private final transient LogLevel preceding;
    private final transient String name;
    private final transient String displayName;
    private final transient int group;
    private final transient int priority;
    private final transient int syslogEquiv;

    public LogLevel(LogLevel prev, String name, int group, int priority) {
        this(prev, name, group, priority, -1);
    }

    public LogLevel(LogLevel prev, String name, int group, int priority, int syslogEquiv) {
        if (name == null)
            throw new NullPointerException("name");
        this.preceding = prev;
        this.name = name;
        this.displayName = name;
        this.group = group;
        this.priority = priority;
        this.syslogEquiv = syslogEquiv;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public LogLevel getPreceding() {
        return preceding;
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
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
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
            return name;
    }

}
