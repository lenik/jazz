package net.bodz.bas.util;

public class DurationFormat {

    public static String formatNanoDuration(long _nanos) {
        StringBuilder sb = new StringBuilder();
        int x = (int) (_nanos % 1000000000);
        int nanos = x % 1000;
        x /= 1000;
        int millis = x % 1000;
        x /= 1000;
        int ms = x;

        x = (int) (_nanos / 1000000000);
        int secs = x % 60;
        x /= 60;
        int mins = x % 60;
        x /= 60;
        int hours = x;

        boolean yes = false;
        if (hours > 0) {
            sb.append(hours);
            sb.append(":");
            yes = true;
        }
        if (yes || mins > 0) {
            sb.append(mins);
            sb.append(":");
            yes = true;
        }
        if (yes || secs > 0) {
            sb.append(secs);
            sb.append(":");
            yes = true;
        }

        boolean showMs = false;

        if (yes || ms > 0) {
            sb.append(ms);
            if (! yes)
                // sb.append("ms");
                showMs = true;
            // sb.append(".");
            yes = true;
        }

        if (millis != 0 || nanos != 0) {
            sb.append(".");
            if (millis < 100) {
                sb.append("0");
                if (millis < 10)
                    sb.append("0");
            }
            sb.append(millis);
            if (nanos != 0) {
                // sb.append("_");
                if (nanos < 100) {
                    sb.append("0");
                    if (nanos < 10)
                        sb.append("0");
                }
                sb.append(nanos);
            }
        } // if millis || nanos

        if (showMs)
            sb.append("ms");

        return sb.toString();
    }

}
