package net.bodz.bas.log;

import net.bodz.bas.t.preorder.AbstractPreorder;

public class LogLevelPreorder
        extends AbstractPreorder<LogLevel> {

    @Override
    public int compare2(LogLevel o1, LogLevel o2) {
        int group1 = o1.getGroup();
        int group2 = o2.getGroup();
        int cmp = group1 - group2;
        if (cmp != 0)
            return cmp;

        return compareIdentity(o1, o2);
    }

    @Override
    public LogLevel getPreceding(LogLevel o) {
        return new LogLevel("", o.getGroup(), o.getPriority() - 1, o.getSyslogEquiv());
    }

    @Override
    public int precompare(LogLevel o1, LogLevel o2) {
        int group1 = o1.getGroup();
        int group2 = o2.getGroup();
        int cmp = group1 - group2;
        if (cmp != 0)
            return UNKNOWN;

        cmp = o1.getPriority() - o2.getPriority();
        if (cmp < 0)
            return LESS_THAN;
        else if (cmp > 0)
            return GREATER_THAN;

        return compareIdentity(o1, o2);
    }

    private static final LogLevelPreorder instance = new LogLevelPreorder();

    public static LogLevelPreorder getInstance() {
        return instance;
    }

}
