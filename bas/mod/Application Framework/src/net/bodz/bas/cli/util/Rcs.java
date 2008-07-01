package net.bodz.bas.cli.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RcsKeywords(id = "$Id: Rcs.java 784 2008-01-15 10:53:24Z lenik $")
public class Rcs {

    static DateFormat DATETIME;
    static DateFormat DATE;
    static DateFormat TIME;
    static {
        DATE = new SimpleDateFormat("yyyy-MM-dd");
        TIME = new SimpleDateFormat("hh:mm:sss");
        DATETIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:sss");
    }

    public static VersionInfo parseId(String id) {
        id = id.substring(0, id.length() - 1);
        String[] parts = id.split("\\s+");
        VersionInfo info = new VersionInfo();
        switch (parts.length) {
        case 7: // state
            info.state = parts[6];
        case 6: // lenik
            info.author = parts[5];
        case 5: // 10:53:242
            long _time = 0;
            try {
                _time = TIME.parse(parts[4]).getTime();
            } catch (ParseException e) {
            }
            info.time += _time;
        case 4: // 2008-01-15
            long _date = 0;
            try {
                _date = DATE.parse(parts[3]).getTime();
            } catch (ParseException e) {
            }
            info.time += _date;
        case 3: // 784
            String[] revs = parts[2].split("\\.");
            info.revision = new int[revs.length];
            for (int i = 0; i < revs.length; i++)
                info.revision[i] = Integer.parseInt(revs[i]);
        case 2: // Rcs.java
            info.name = parts[1];
        case 1: // $Id:
        }
        return info;
    }

    public static VersionInfo parseId(RcsKeywords keywords) {
        return parseId(keywords.id());
    }

    public static VersionInfo parseId(Class<?> clazz) {
        return parseId(clazz.getAnnotation(RcsKeywords.class));
    }

}
