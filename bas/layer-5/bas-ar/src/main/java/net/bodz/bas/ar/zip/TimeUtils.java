package net.bodz.bas.ar.zip;

import java.util.Calendar;

public class TimeUtils {

    public static long fromDos(int msdosTime) {
        int time = msdosTime & 0xffff;
        int date = (msdosTime >> 16) & 0xffff;

        int sec = (time & 0x1f) * 2;
        time >>= 5;
        int min = time & 0x3f;
        time >>= 6;
        int hour = time;

        int day = date & 0x1f;
        date >>= 5;
        int month = date & 0x0f;
        date >>= 4;
        int year = date + 1980;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, min, sec);
        return cal.getTimeInMillis();
    }

    public static int toDos(long javaTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(javaTime);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        int date = year - 1980;
        date <<= 4;
        date |= month;
        date <<= 5;
        date |= day;

        int time = hour;
        time <<= 6;
        time |= min;
        time <<= 5;
        time |= sec / 2;

        int msdosTime = (date << 16) | time;
        return msdosTime;
    }

}
