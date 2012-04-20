package net.bodz.bas.usnap.pos.util;

import java.util.Calendar;

public class PosId {

    public static long getTime10() {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR) % 100;
        int d = calendar.get(Calendar.DAY_OF_YEAR);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        d += y * 1000;
        s += ((h * 60) + m) * 60;
        return s + d * 100000l;
    }

    public static long getTime13() {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR) % 100;
        int d = calendar.get(Calendar.DAY_OF_YEAR);
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        int m = calendar.get(Calendar.MINUTE);
        int s = calendar.get(Calendar.SECOND);
        int ms = calendar.get(Calendar.MILLISECOND);
        d += y * 1000;
        s += ((h * 60) + m) * 60;
        ms += s * 1000;
        return ms + d * 100000000l;
    }

}
