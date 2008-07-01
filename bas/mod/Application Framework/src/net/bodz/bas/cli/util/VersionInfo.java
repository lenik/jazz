package net.bodz.bas.cli.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class VersionInfo implements Comparable<VersionInfo> {

    public String  name;
    public long    time;
    public int     revision[];
    public String  author;
    public String  state;

    private String revString;

    public static String getVersion(int[] revs) {
        if (revs == null)
            return null;
        StringBuffer buf = null;
        for (int rev : revs) {
            if (buf == null)
                buf = new StringBuffer(revs.length * 4);
            else
                buf.append('.');
            buf.append(rev);
        }
        return buf.toString();
    }

    public String getVersion() {
        if (revString == null)
            revString = getVersion(revision);
        return revString;
    }

    private static DateFormat DATE;
    private static DateFormat TIME;
    static {
        DATE = new SimpleDateFormat("yyyy-MM-dd");
        TIME = new SimpleDateFormat("hh:mm:sss");
    }

    public String getDate() {
        return DATE.format(time);
    }

    public String getTime() {
        return TIME.format(time);
    }

    @Override
    public String toString() {
        return name + "-" + getVersion();
    }

    @Override
    public int compareTo(VersionInfo o) {
        if (o == null)
            return 1;
        int cmp = name.compareTo(o.name);
        if (cmp != 0)
            return cmp;
        for (int i = 0; i < revision.length; i++) {
            if (i >= o.revision.length)
                return 1;
            cmp = revision[i] - o.revision[i];
            if (cmp != 0)
                return cmp;
        }
        return 0;
    }

}
