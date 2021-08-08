package net.bodz.bas.log;

public class LogLevel
        implements
            Comparable<LogLevel> {

    public static final int stdGroup = 0;
    public static final int logGroup = 1;
    public static final int statusGroup = 2;

    public static final LogLevel STDERR = new LogLevel("stderr", stdGroup, -2, null);
    public static final LogLevel STDOUT = new LogLevel("stdout", stdGroup, -1, null);

    public static final LogLevel OFF = new LogLevel("off", logGroup, Integer.MIN_VALUE, 0, null);
    public static final LogLevel FATAL = new LogLevel("fatal", logGroup, -3, 0, OFF);
    public static final LogLevel ERROR = new LogLevel("error", logGroup, -2, 3, FATAL);
    public static final LogLevel WARN = new LogLevel("warn", logGroup, -1, 4, ERROR);
    public static final LogLevel MESG = new LogLevel("mesg", logGroup, 0, 6, WARN);
    public static final LogLevel INFO = new LogLevel("info", logGroup, 1, 6, MESG);
    public static final LogLevel LOG = new LogLevel("log", logGroup, 2, 6, INFO);
    public static final LogLevel DEBUG = new LogLevel("debug", logGroup, 3, 7, LOG);
    public static final LogLevel TRACE = new LogLevel("trace", logGroup, 4, 7, DEBUG);
    public static final LogLevel ALL = new LogLevel("all", logGroup, Integer.MAX_VALUE, 0, TRACE);

    public static final LogLevel STATUS = new LogLevel("status", statusGroup, 1, null);
    public static final LogLevel PROGRESS = new LogLevel("progress", statusGroup, 3, null);

    private final String intern;
    private final String label;
    private final int group;
    private final int priority;
    private final int syslogEquiv;

    private LogLevel quiet;
    private LogLevel verbose;

    public LogLevel(String name, int group, int priority, LogLevel quiet) {
        this(name, group, priority, -1, quiet);
    }

    public LogLevel(String name, int group, int priority, int syslogEquiv, LogLevel quiet) {
        if (name == null)
            throw new NullPointerException("name");
        this.intern = name.intern();
        this.label = name;
        this.group = group;
        this.priority = priority;
        this.syslogEquiv = syslogEquiv;
        this.quiet = quiet;
        if (quiet != null)
            if (quiet.verbose == null)
                quiet.verbose = this;
    }

    public String getInternName() {
        return intern;
    }

    public String getLabel() {
        return label;
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

    public LogLevel getQuiet() {
        return quiet;
    }

    public LogLevel getVerbose() {
        return verbose;
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
        if (label != null)
            return label;
        else
            return intern;
    }

    @Override
    public int compareTo(LogLevel o) {
        if (o == null)
            return 1;
        if (o == this)
            return 0;

        int cmp;

        cmp = group - o.group;
        if (cmp != 0)
            return cmp;

        cmp = priority - o.priority;
        if (cmp != 0)
            return cmp;

        return -1;
    }

}
